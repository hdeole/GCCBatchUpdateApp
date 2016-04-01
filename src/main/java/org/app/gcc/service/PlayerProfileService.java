package org.app.gcc.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;
import org.app.gcc.model.PlayerBattingStats;
import org.app.gcc.model.PlayerBowlingStats;
import org.app.gcc.model.PlayerProfile;
import org.app.gcc.util.RestRequestClient;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.google.gson.Gson;



public class PlayerProfileService {

	private static String chauka_uri = "resources/html-ref/view-player-profile.html";
	Gson gson = new Gson();
	private static final Logger LOGGER = Logger.getLogger(PlayerProfileService.class.getName());
	
	private DataPersistanceService dataService = new DataPersistanceService();

	public String fetchPlayerProfile(long playerId) throws IOException, SQLException {
		
		if(playerId <= 0){
			throw new IllegalStateException("playerId can not be negative or zero.");
		}
		File input = new File("resources/html-ref/view-player-profile.html");
		Document doc = Jsoup.parse(input, "UTF8");
		PlayerProfile profile = new PlayerProfile();
		List<PlayerBattingStats> battingStatsList = new ArrayList<PlayerBattingStats>();
		List<PlayerBowlingStats> bowlingStatsList = new ArrayList<PlayerBowlingStats>();
		RestRequestClient restClient = new RestRequestClient() ;
		
		profile.setPlayerId(playerId);
		Elements container = doc.select("select#select_tour_stats > option");

		MultivaluedMap<String, Object> tournamentIds = getTournamentIdsFromDocumentElement(container);

		for(Map.Entry entry : tournamentIds.entrySet()){
			String tournamentId = entry.getKey().toString();
			//LOGGER.info("fetching tournament data for player  :");
			Document statsDoc = restClient.getPlayersTounamentStatsToChauka(playerId, tournamentId);
			Elements tables = statsDoc.getElementsByTag("table");
			LOGGER.info("fetching batting data :");
			PlayerBattingStats battingStats = getPlayerBattingStats(tables.get(0));
			battingStats.setPlayerId(""+playerId);
			battingStats.setTournamentId(tournamentId);
			LOGGER.info("saving batting data :");
			dataService.savePlayerBattingStatistics(battingStats);			 
			
			battingStatsList.add(battingStats);
			LOGGER.info("fetching bowling data :");
			PlayerBowlingStats bowlingStats = getPlayerBowlingStats(tables.get(1));
			bowlingStats.setTournamentId(tournamentId);
			bowlingStats.setPlayerId(""+playerId);
			LOGGER.info("saving bowling data :");
			dataService.savePlayerBowlingStatistics(bowlingStats);
			
			bowlingStatsList.add(bowlingStats);
		}
		
		profile.setDateofBirth("10/10/1982");
		profile.setPlayerBattingStyle("Right Hand Bat");
		profile.setPlayerBowlingStyle("Right hand Off Spin");
		profile.setPlayerName("Harshal Deole");
		profile.setPictureURL("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSvxu6n2hW6wfPf6EkARxP0_n1_jNCG15cXaooOGIeaAV-GwGv9");
		profile.setFunFacts("He joined the team only a couple years ago but has become famous amongst the team members.");
		
		dataService.savePlayerProfile(profile);
		
		profile.setPlayerBattingStats(battingStatsList);
		profile.setPlayerBowlingStats(bowlingStatsList);
		LOGGER.info("Done..!!! EnJOY :");
		return gson.toJson(profile);		  
	}

 

	private MultivaluedMap<String, Object> getTournamentIdsFromDocumentElement(Elements container) {
		LOGGER.fine("container:"+container.outerHtml());
		MultivaluedMap<String, Object> tournamentData = new MultivaluedHashMap<String, Object>();
		for (Element option : container) {
			if ((!StringUtil.isBlank(option.attr("value"))) && 
					(!option.attr("value").equals("0"))) {
				LOGGER.fine("optiontag : " + option.attr("value"));
				tournamentData.add(option.attr("value"),option.text());
			}

		}

		return tournamentData;
	}

	private PlayerBowlingStats getPlayerBowlingStats(Element parentNode) {
		PlayerBowlingStats bowlingStats = new PlayerBowlingStats();
		List<String> statList = getTableDataFromNode(parentNode);

		try {
			bowlingStats.setMatches(statList.get(0));
			bowlingStats.setTotalWickets(statList.get(1));
			bowlingStats.setRunsConceded(statList.get(2));
			bowlingStats.setFiveWickets(statList.get(3));
			bowlingStats.setBowlingAverage(statList.get(4));
			bowlingStats.setEconomy(statList.get(5));

		} catch (Exception e) {
			LOGGER.severe("Could not get the Players Batting Statistics... since there was an error cause: "
					+ e.getMessage());

		}
		LOGGER.fine("Got the Team bowlingStats Statistics : " + bowlingStats.toString());

		return bowlingStats;
	}

	private PlayerBattingStats getPlayerBattingStats(Element parentNode) {

		PlayerBattingStats battingStats = new PlayerBattingStats();

		List<String> statList = getTableDataFromNode(parentNode);

		try {

			battingStats.setMatchesPlayed(statList.get(0));
			battingStats.setInnings(statList.get(1));
			battingStats.setNotOuts(statList.get(2));
			battingStats.setTotalRuns(statList.get(3));
			battingStats.setFifties(statList.get(4));
			battingStats.setHundreds(statList.get(5));
			battingStats.setFours(statList.get(6));
			battingStats.setSixes(statList.get(7));
			battingStats.setBattingAverage(statList.get(8));
			battingStats.setHighScore(statList.get(9));
		} catch (Exception e) {
			LOGGER.severe("Could not get the Players Batting Statistics... since there was an error cause: "
					+ e.getMessage());
		}
		LOGGER.fine("Got the Team Statistics : " + battingStats.toString());
		return battingStats;

	}

	private List<String> getTableDataFromNode(Element parentNode) {

		List<String> statList = new ArrayList<String>();
		 
		Elements statisticsNodes = parentNode.select("td");
		LOGGER.fine("statNode " + parentNode.select("td"));

		for (Node statNode : statisticsNodes) {
			if (!StringUtil.isBlank(statNode.outerHtml())) {
				LOGGER.fine("statNode " + statNode.outerHtml());
				String nodeValue = statNode.outerHtml();
				statList.add(nodeValue.substring(nodeValue.indexOf(">") + 1, nodeValue.lastIndexOf("<")));
			}
		}
		LOGGER.fine("" + statList.size());
		return statList;
	}

}
