package org.app.gcc.dao.entities;

import org.postgresql.util.PGobject;

//@DatabaseTable(tableName="gcc.player_bowling_record")
public class PlayerBowlingRecord {

	//@DatabaseField(id=true,columnName="player_id")
	private Long playerId;
	//@DatabaseField(columnName="tournament_id")
	private long tournamentId;
	//@DatabaseField
	private PGobject data;
	
	public PlayerBowlingRecord(){
	}
	public PlayerBowlingRecord(long playerId, long tournamentId, PGobject data) {
		super();
		this.playerId = playerId;
		this.tournamentId = tournamentId;
		this.data = data;
	}

	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public long getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(long tournamentId) {
		this.tournamentId = tournamentId;
	}
	public PGobject getData() {
		return data;
	}
	public void setData(PGobject data) {
		this.data = data;
	}
}
