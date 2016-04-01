package org.app.gcc.model;

import java.io.Serializable;

public class PlayerListItem implements Serializable {

	private String playerName ;
	
	private long playerId;
	
	private String playerProfileURL;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerProfileURL() {
		return playerProfileURL;
	}

	public void setPlayerProfileURL(String playerProfileURL) {
		this.playerProfileURL = playerProfileURL;
	}
	
	
	
	
}
