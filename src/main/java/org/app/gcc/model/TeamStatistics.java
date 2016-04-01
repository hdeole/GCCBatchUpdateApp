package org.app.gcc.model;

import java.io.Serializable;

public class TeamStatistics implements Serializable {
	
	private Long teamId ;
	
	private Long tournamentId ;
	
	private String matchesPlayed;
	
	private String matchesWon;
	
	private String matchesLost;
	
	private String matchesTied;
	
	private String winPercentage;

	public String getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(String matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}

	public String getMatchesWon() {
		return matchesWon;
	}

	public void setMatchesWon(String matchesWon) {
		this.matchesWon = matchesWon;
	}

	public String getMatchesLost() {
		return matchesLost;
	}

	public void setMatchesLost(String matchesLost) {
		this.matchesLost = matchesLost;
	}

	public String getWinPercentage() {
		return winPercentage;
	}

	public void setWinPercentage(String winPercentage) {
		this.winPercentage = winPercentage;
	}

	public String getMatchesTied() {
		return matchesTied;
	}

	public void setMatchesTied(String matchesTied) {
		this.matchesTied = matchesTied;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.matchesPlayed+","+this.matchesWon+","+this.matchesLost+","+this.matchesTied+","+this.winPercentage;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}
	
	

}
