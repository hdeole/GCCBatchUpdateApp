package org.app.gcc.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.app.gcc.model.TeamStatistics;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class TeamStatisticsService {
	
	private static String chauka_uri = "http://chauka.in/index.php/site/load_team_profile/6724";

	private static final Logger LOGGER = Logger.getLogger(TeamStatisticsService.class.getName());
	
	private DataPersistanceService dataService = new DataPersistanceService();


	public void fetchTeamStatistics(Long teamId) throws IOException, SQLException{
		TeamStatistics statistics = new TeamStatistics();
		List<String> statList =new ArrayList<String>();
		Document doc = Jsoup.connect(chauka_uri).get();
		Element container = doc.getElementById("stats");		
		Node parentNode = container.getElementsContainingOwnText("Tied").parents().first().parent().parent().getElementsByTag("tbody").first();
	 
		List<Node> statisticsNodes = parentNode.childNodes().get(1).childNodes();	
		
		for(Node statNode : statisticsNodes){			 
			if (!StringUtil.isBlank(statNode.outerHtml()) ) {
				String nodeValue = statNode.outerHtml();	
				statList.add(nodeValue.substring(nodeValue.indexOf(">")+1, nodeValue.lastIndexOf("<")));
			}
		}
		LOGGER.info(""+statList.size());
		try{
			statistics.setTeamId(teamId);
			statistics.setTournamentId(new Long("0"));
			statistics.setMatchesPlayed(statList.get(0));
			statistics.setMatchesWon(statList.get(1));
			statistics.setMatchesLost(statList.get(2));
			statistics.setMatchesTied(statList.get(3));
			statistics.setWinPercentage(statList.get(4));

		}catch(Exception e){
			LOGGER.severe("Could not get the Team Statistics... since there was an error cause: "+e.getMessage());
		}
		
		dataService.saveTeamStatistics(statistics);
		
		LOGGER.info("Got the Team Statistics : "+statistics.toString());
	}
	

}
