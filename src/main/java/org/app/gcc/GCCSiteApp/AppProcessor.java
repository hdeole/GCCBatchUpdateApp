package org.app.gcc.GCCSiteApp;

import java.util.logging.Logger;

import org.app.gcc.model.PlayerListItem;
import org.app.gcc.model.PlayerListItems;
import org.app.gcc.service.PlayerProfileService;
import org.app.gcc.service.TeamRoaterService;

public class AppProcessor {

	private static final Logger LOGGER = Logger.getLogger(AppProcessor.class.getName());

	private static final long TEAM_ID = 6724;

	public static void processGCCData() throws Exception {

		String jsonProfileData;
		try {
			PlayerListItems players = new TeamRoaterService().fetchTeamRosterData();
//			new TeamStatisticsService().fetchTeamStatistics(TEAM_ID);
//			LOGGER.info("start of Fetch profile data :");
			for (PlayerListItem player : players.getPlayerItemList()){
				jsonProfileData = new PlayerProfileService().fetchPlayerProfile(player.getPlayerId());
			}
			
//			jsonProfileData = new PlayerProfileService().fetchPlayerProfile("107433");
//			LOGGER.info("received the profile data as :" + jsonProfileData);
		} catch (Exception e) {
			LOGGER.severe("failsd to get the profile data  :" + e.getMessage());
			e.printStackTrace();
		}

	}

}
