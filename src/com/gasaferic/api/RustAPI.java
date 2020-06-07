package com.gasaferic.api;

import org.bukkit.entity.Player;

import com.gasaferic.main.Main;
import com.gasaferic.model.Language;
import com.gasaferic.model.Survivor;

public class RustAPI {

	public Language getLanguage(Player player) {
		return Main.getSurvivorManager().getSurvivorByPlayer(player).getLanguage();
	}
	
	public Survivor getSurvivorByPlayer(Player player) {
		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);
		if (survivor != null) {
			return survivor;
		} else {
			return null;
		}
	}
	
	public boolean isPluginEnabled() {
		return Main.mysqlConnected;
	}
	
}