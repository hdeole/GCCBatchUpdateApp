package org.app.gcc.dao.entities;

import org.postgresql.util.PGobject;

//@DatabaseTable(tableName="gcc.team_statistics")
public class TeamStatisticsRecord {

	//@DatabaseField(id=true,columnName="TEAM_ID")
	private Long teamId;
	//@DatabaseField(columnName="TOURNAMENT_ID")
	private long tournamentId;
	//@DatabaseField
	private PGobject data;
	
	public TeamStatisticsRecord(){}
	
	public TeamStatisticsRecord(long teamId, long tournamentId, PGobject data) {
		super();
		this.teamId = teamId;
		this.tournamentId = tournamentId;
		this.data = data;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
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
