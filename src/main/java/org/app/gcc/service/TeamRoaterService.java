package org.app.gcc.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.app.gcc.model.PlayerListItem;
import org.app.gcc.model.PlayerListItems;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TeamRoaterService {

	private static String chauka_uri = "http://chauka.in/index.php/site/load_team_profile/6724";

	private static final Logger LOGGER = Logger.getLogger(TeamRoaterService.class.getName());

	public PlayerListItems fetchTeamRosterData() throws Exception {

		try {
			File input = new File("resources/html-ref/view-team-stats.html");
			Document doc = Jsoup.connect(chauka_uri).get();
			Element playersDisplayBox = doc.getElementById("display_players_box");
			PlayerListItems players = new PlayerListItems();
			List<PlayerListItem> playerList = new ArrayList<PlayerListItem>();
			PlayerListItem player = null;

			Elements listElements = playersDisplayBox.getElementsByTag("tbody");
			
			for(Element listElement : listElements.select("a[href]"))
			{
				
				String href = listElement.attr("href");
				player = new PlayerListItem();
				player.setPlayerProfileURL(href);
				player.setPlayerId(Long.parseLong(href.substring(href.lastIndexOf("/")+1, href.length())));
				player.setPlayerName(listElement.ownText());
				playerList.add(player);
				new DataPersistanceService().savePlayerTOTeamRoster(player);
				LOGGER.info(listElement.ownText()+","+player.getPlayerId()+","+player.getPlayerProfileURL());
			}
			players.setPlayerItemList(playerList);
			LOGGER.info("size of the playerList :"+players.getPlayerItemList().size());
			return players;
		} catch (IOException e) {
			LOGGER.severe("Error while data from the Chauka site");
			throw new Exception(e);
			// e.printStackTrace();
		}

	}

}
