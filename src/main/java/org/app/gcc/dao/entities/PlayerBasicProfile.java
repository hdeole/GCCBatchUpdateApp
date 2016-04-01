package org.app.gcc.dao.entities;

import org.postgresql.util.PGobject;

 
public class PlayerBasicProfile {

	 
	private Long playerId;
	
	
	private PGobject data;


	public PlayerBasicProfile() {	 
	}
	
	public PlayerBasicProfile(long playerId, PGobject data) {
		super();
		this.playerId = playerId;
		this.data = data;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public PGobject getData() {
		return data;
	}

	public void setData(PGobject data) {
		this.data = data;
	}
	
	
}
