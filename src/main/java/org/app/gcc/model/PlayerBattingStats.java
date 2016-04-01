package org.app.gcc.model;

public class PlayerBattingStats {

	private String playerId;

	private String tournamentId ;
	
	private String matchesPlayed;
	
	private String innings;
	
	private String notOuts;
	
	private String totalRuns;
	
	private String fifties;
	
	private String hundreds;
	
	private String fours;
	
	private String sixes;
	
	private String battingAverage;
	 
	private String highScore;

	public String getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(String matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}

	public String getNotOuts() {
		return notOuts;
	}

	public void setNotOuts(String notOuts) {
		this.notOuts = notOuts;
	}

	public String getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(String totalRuns) {
		this.totalRuns = totalRuns;
	}

	public String getFifties() {
		return fifties;
	}

	public void setFifties(String fifties) {
		this.fifties = fifties;
	}

	public String getHundreds() {
		return hundreds;
	}

	public void setHundreds(String hundreds) {
		this.hundreds = hundreds;
	}

	public String getFours() {
		return fours;
	}

	public void setFours(String fours) {
		this.fours = fours;
	}

	public String getSixes() {
		return sixes;
	}

	public void setSixes(String sixes) {
		this.sixes = sixes;
	}

	public String getBattingAverage() {
		return battingAverage;
	}

	public void setBattingAverage(String battingAverage) {
		this.battingAverage = battingAverage;
	}

	public String getHighScore() {
		return highScore;
	}

	public void setHighScore(String highScore) {
		this.highScore = highScore;
	}
	 
	@Override
	public String toString() {
		return "matchesPlayed:"+this.matchesPlayed+",notOuts:"+this.notOuts+",totalRuns:"+totalRuns+",fifties:"
				+fifties+",hundreds:"+hundreds+",fours:"+fours+",sixes:"+sixes+",battingAverage:"+battingAverage
				+",highScore:"+highScore;		
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

	public String getInnings() {
		return innings;
	}

	public void setInnings(String innings) {
		this.innings = innings;
	}

}
