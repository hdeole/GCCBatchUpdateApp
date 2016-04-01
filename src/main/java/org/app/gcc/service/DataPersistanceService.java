package org.app.gcc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.app.gcc.dao.entities.PlayerBasicProfile;
import org.app.gcc.dao.entities.PlayerBattingRecord;
import org.app.gcc.dao.entities.PlayerBowlingRecord;
import org.app.gcc.dao.entities.TeamStatisticsRecord;
import org.app.gcc.model.PlayerBattingStats;
import org.app.gcc.model.PlayerBowlingStats;
import org.app.gcc.model.PlayerListItem;
import org.app.gcc.model.PlayerProfile;
import org.app.gcc.model.TeamStatistics;
import org.postgresql.util.PGobject;

import com.google.gson.Gson;

public class DataPersistanceService {

	private static final Logger LOGGER = Logger.getLogger(DataPersistanceService.class.getName());
	
	private static String SELECT_PLAYER_BATTING_RECORD_QRY = "select player_id from gcc.player_batting_record where player_id = ? and tournament_id = ?";
	private static String UPDATE_PLAYER_BATTING_RECORD_QRY = "update gcc.player_batting_record set data = ? where player_id=? and tournament_id = ?";
	private static String CREATE_PLAYER_BATTING_RECORD_QRY = "insert into gcc.player_batting_record values(?,?,?)";
	
	private static String SELECT_PLAYER_BOWLING_RECORD_QRY = "select player_id from gcc.player_bowling_record where player_id = ? and tournament_id = ?";
	private static String UPDATE_PLAYER_BOWLING_RECORD_QRY = "update gcc.player_bowling_record set data = ? where player_id=? and tournament_id = ?";
	private static String CREATE_PLAYER_BOWLING_RECORD_QRY = "insert into gcc.player_bowling_record values(?,?,?)";

	private static String SELECT_TEAM_RECORD_QRY = "select team_id from gcc.team_statistics where team_id = ? and tournament_id = ?";
	private static String UPDATE_TEAM_RECORD_QRY = "update gcc.team_statistics set data = ? where team_id=? and tournament_id = ?";
	private static String CREATE_TEAM_RECORD_QRY = "insert into gcc.team_statistics values(?,?,?)";

	private static String SELECT_PLAYER_PROFILE_QRY = "select player_id from gcc.player_profile where player_id = ? ";
	private static String UPDATE_PLAYER_PROFILE_QRY = "update gcc.player_profile set data = ? where player_id = ? ";
	private static String CREATE_PLAYER_PROFILE_QRY = "insert into gcc.player_profile values(?,?)";

	private static String SELECT_PLAYER_ROSTER_QRY = "select player_id from gcc.gcc_team_roster where player_id = ? ";
	private static String UPDATE_PLAYER_ROSTER_QRY = "update gcc.gcc_team_roster set player_pic_url = ? ,player_name=?,player_batting_style=?,player_bowling_style=?,player_other_style=? where player_id = ? ";
	private static String CREATE_PLAYER_ROSTER_QRY = "insert into gcc.gcc_team_roster values(?,?,?,?,?,?)";

	
	private PGobject dataObject ;	
	private PreparedStatement statement ;
	Connection connection = null;
	
	private Gson gson = new Gson();

	public void savePlayerBattingStatistics(PlayerBattingStats battingStats) throws SQLException {

		dataObject = new PGobject();
		dataObject.setType("json");
		dataObject.setValue(gson.toJson(battingStats));  
		
		PlayerBattingRecord battingRecord = new PlayerBattingRecord();
		battingRecord.setPlayerId(Long.parseLong(battingStats.getPlayerId()));
		battingRecord.setTournamentId(Long.parseLong(battingStats.getTournamentId()));
		battingRecord.setData(dataObject);
		
		try {
			LOGGER.info("saving player Batting record to DB..");
			
			
			dataObject = new PGobject();
			dataObject.setType("json");
			dataObject.setValue(gson.toJson(battingStats)); // no need to use record object anymore.
			
			connection = ConnectorService.getConnection();
			
			statement = connection.prepareStatement(SELECT_PLAYER_BATTING_RECORD_QRY);
			statement.setLong(1, Long.parseLong(battingStats.getPlayerId()));
			statement.setLong(2, Long.parseLong(battingStats.getTournamentId()));
			ResultSet result = statement.executeQuery();
			if(result.next()){
				LOGGER.info("Found existing record..updating the data.");
				statement = connection.prepareStatement(UPDATE_PLAYER_BATTING_RECORD_QRY);
				statement.setObject(1, dataObject);
				statement.setLong(2, Long.parseLong(battingStats.getPlayerId()));
				statement.setLong(3, Long.parseLong(battingStats.getTournamentId()));
			}else{
				LOGGER.info("No record exist. creating the record");

				statement = connection.prepareStatement(CREATE_PLAYER_BATTING_RECORD_QRY);
				statement.setLong(1, Long.parseLong(battingStats.getPlayerId()));
				statement.setLong(2, Long.parseLong(battingStats.getTournamentId()));
				statement.setObject(3, dataObject); 
			}			
			statement.executeUpdate();
			
			LOGGER.info("record saved..");			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			statement.close();
			connection.close();
		}
	}

	public void savePlayerBowlingStatistics(PlayerBowlingStats bowlingStats) throws SQLException {

		dataObject = new PGobject();
		dataObject.setType("json");
		dataObject.setValue(gson.toJson(bowlingStats)); // no need to use record object anymore.
		
		PlayerBowlingRecord bowlingRecord = new PlayerBowlingRecord();
		bowlingRecord.setPlayerId(Long.parseLong(bowlingStats.getPlayerId()));
		bowlingRecord.setTournamentId(Long.parseLong(bowlingStats.getTournamentId()));
		bowlingRecord.setData(dataObject);
		try {
			LOGGER.info("saving player Bowling record to DB..");		
			connection = ConnectorService.getConnection();
			
			statement = connection.prepareStatement(SELECT_PLAYER_BOWLING_RECORD_QRY);
			statement.setLong(1, Long.parseLong(bowlingStats.getPlayerId()));
			statement.setLong(2, Long.parseLong(bowlingStats.getTournamentId()));
			ResultSet result = statement.executeQuery();
			if(result.next()){
				LOGGER.info("Found existing record..updating the data.");

				statement = connection.prepareStatement(UPDATE_PLAYER_BOWLING_RECORD_QRY);
				statement.setObject(1, dataObject);
				statement.setLong(2, Long.parseLong(bowlingStats.getPlayerId()));
				statement.setLong(3, Long.parseLong(bowlingStats.getTournamentId()));
			}else{
				LOGGER.info("No record exist. creating the record");

				statement = connection.prepareStatement(CREATE_PLAYER_BOWLING_RECORD_QRY);
				statement.setLong(1, Long.parseLong(bowlingStats.getPlayerId()));
				statement.setLong(2, Long.parseLong(bowlingStats.getTournamentId()));
				statement.setObject(3, dataObject); 
			}				 
			statement.executeUpdate();
			LOGGER.info("record saved..");
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			statement.close();
			connection.close();
		}
	}
	
	public void saveTeamStatistics(TeamStatistics teamStats) throws SQLException {

		dataObject = new PGobject();
		dataObject.setType("json");
		dataObject.setValue(gson.toJson(teamStats)); // no need to use record object anymore.

		TeamStatisticsRecord teamStatRecord = new TeamStatisticsRecord();
		teamStatRecord.setTeamId(teamStats.getTeamId());
		teamStatRecord.setTournamentId(teamStats.getTournamentId());
		teamStatRecord.setData(dataObject);
		try {
			LOGGER.info("saving player Bowling record to DB..");
			connection = ConnectorService.getConnection();

			statement = connection.prepareStatement(SELECT_TEAM_RECORD_QRY);
			statement.setLong(1, teamStats.getTeamId());
			statement.setLong(2, teamStats.getTournamentId());
			ResultSet result = statement.executeQuery();
			if(result.next()){
				LOGGER.info("Found existing record..updating the data.");

				statement = connection.prepareStatement(UPDATE_TEAM_RECORD_QRY);
				statement.setObject(1, dataObject);
				statement.setLong(2, teamStats.getTeamId());
				statement.setLong(3, teamStats.getTournamentId());
			}else{
				LOGGER.info("No record exist. creating the record");

				statement = connection.prepareStatement(CREATE_TEAM_RECORD_QRY);
				statement.setLong(1, teamStats.getTeamId());
				statement.setLong(2, teamStats.getTournamentId());
				statement.setObject(3, dataObject); 
			}	
			
			statement.executeUpdate();
			LOGGER.info("record saved..");
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			statement.close();
			connection.close();
		}
	}

	public void savePlayerProfile(PlayerProfile profile) throws SQLException {
		 
		
		dataObject = new PGobject();
		dataObject.setType("json");
		dataObject.setValue(gson.toJson(profile)); // no need to use record object anymore.
		try {
			
			PlayerBasicProfile profileRecord = new PlayerBasicProfile();
			profileRecord.setPlayerId(profile.getPlayerId());
			profileRecord.setData(dataObject);
			
			LOGGER.info("saving player profile profile to DB..");
			connection = ConnectorService.getConnection();

			statement = connection.prepareStatement(SELECT_PLAYER_PROFILE_QRY);
			statement.setLong(1, profile.getPlayerId());			 
			ResultSet result = statement.executeQuery();
			if(result.next()){
				LOGGER.info("Found existing record..updating the data.");

				statement = connection.prepareStatement(UPDATE_PLAYER_PROFILE_QRY);
				statement.setObject(1, dataObject);
				statement.setLong(2, profile.getPlayerId());
			}else{
				LOGGER.info("No record exist. creating the record");

				statement = connection.prepareStatement(CREATE_PLAYER_PROFILE_QRY);
				statement.setLong(1, profile.getPlayerId());
				statement.setObject(2, dataObject); 
			}	

			statement.executeUpdate();

			LOGGER.info("record saved..");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			statement.close();
			connection.close();
		}
	}

	public void savePlayerTOTeamRoster(PlayerListItem player) throws SQLException {
		
		LOGGER.info("saving player Bowling record to DB..");
		connection = ConnectorService.getConnection();
		try {
			statement = connection.prepareStatement(SELECT_PLAYER_ROSTER_QRY);
			statement.setLong(1, player.getPlayerId());	
			ResultSet result = statement.executeQuery();
			if(result.next()){
				LOGGER.info("Found existing record..updating the data.");
				statement = connection.prepareStatement(UPDATE_PLAYER_ROSTER_QRY);
				statement.setString(1, player.getPlayerProfileURL());
				statement.setString(2, player.getPlayerName());
				statement.setString(3, "Right Hand Bat");
				statement.setString(4, "Right Hand Bowl");
				statement.setString(5, "");
				statement.setLong(6, player.getPlayerId());
			}else{
				LOGGER.info("No record exist. creating the record");

				statement = connection.prepareStatement(CREATE_PLAYER_ROSTER_QRY);
				statement.setLong(1, player.getPlayerId());
				statement.setString(2, player.getPlayerProfileURL()); 
				statement.setString(3, player.getPlayerName());
				statement.setString(4, "Right Hand Bat");
				statement.setString(5, "Right Hand Bowl");
				statement.setString(6, "");
			}	
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			statement.close();
			connection.close();
		}
		
	}
}
