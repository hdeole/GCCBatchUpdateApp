package org.app.gcc.util;

import java.io.IOException;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class RestRequestClient extends RestRequestBuilder {

	private static final Logger LOGGER = Logger.getLogger(RestRequestClient.class.getName());

	public Document getPlayersTounamentStatsToChauka(long playerId, String tournamentId) throws IOException {
		return Jsoup.connect(buildPlayersTournamentURL(playerId)).data("input", tournamentId)
				.header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8").ignoreContentType(false)
				.post();
	}

	public static void main(String[] args) {
		RestRequestClient client = new RestRequestClient();
		try {
			client.getPlayersTounamentStatsToChauka(107433, "708");
		} catch (IOException e) {
			LOGGER.severe("ERROR getting Players Tounament Stats From Chauka");
			e.printStackTrace();
		}
	}

}
