package org.app.gcc.util;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class RestRequestBuilder {

	private static final String URL_FOR_PLAYER_TOURNAMENT_STATS = "http://chauka.in/index.php/site/display_player_tour_match_stats/";

	public MultivaluedMap<String, Object> buildBasicHeaders() {
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<String, Object>();
		headers.add("X-Requested-With", "XMLHttpRequest");
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		return headers;
	}

	public MultivaluedMap<String, Object> buildBasicFormDataToPost(String tournamentId) {
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<String, Object>();
		headers.add("input", "" + tournamentId);
		return headers;
	}

	public String buildPlayersTournamentURL(long playerId) {
		return URL_FOR_PLAYER_TOURNAMENT_STATS + playerId;
	}

}
