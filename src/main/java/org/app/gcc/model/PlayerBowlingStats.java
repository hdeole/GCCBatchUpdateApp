package org.app.gcc.model;

public class PlayerBowlingStats {
	
	private String playerId;
	
	private String tournamentId;

	private String Matches;
	
	private String totalWickets;
	
	private String RunsConceded;
	
	private String fiveWickets;
	
	private String bowlingAverage;
	
	private String economy;

	public String getMatches() {
		return Matches;
	}

	public void setMatches(String matches) {
		Matches = matches;
	}

	public String getRunsConceded() {
		return RunsConceded;
	}

	public void setRunsConceded(String runsConceded) {
		RunsConceded = runsConceded;
	}

	public String getFiveWickets() {
		return fiveWickets;
	}

	public void setFiveWickets(String fiveWickets) {
		this.fiveWickets = fiveWickets;
	}

	public String getBowlingAverage() {
		return bowlingAverage;
	}

	public void setBowlingAverage(String bowlingAverage) {
		this.bowlingAverage = bowlingAverage;
	}

	public String getEconomy() {
		return economy;
	}

	public void setEconomy(String economy) {
		this.economy = economy;
	}

	public String getTotalWickets() {
		return totalWickets;
	}

	public void setTotalWickets(String totalWickets) {
		this.totalWickets = totalWickets;
	}

	@Override
	public String toString() {
		return "Matches:"+this.Matches+",totalWickets:"+this.totalWickets+",RunsConceded:"+RunsConceded+",fiveWickets:"
				+fiveWickets+",bowlingAverage:"+bowlingAverage+",economy:"+economy;
		
	}

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	 

	
}
