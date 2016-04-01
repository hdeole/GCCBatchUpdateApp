package org.app.gcc.model;

import java.util.List;
import java.io.Serializable;

public class PlayerListItems implements Serializable {
	
	private List<PlayerListItem> playerItemList;

	public List<PlayerListItem> getPlayerItemList() {
		return playerItemList;
	}

	public void setPlayerItemList(List<PlayerListItem> playerItemList) {
		this.playerItemList = playerItemList;
	}
	
	

}
