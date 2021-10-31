package com.gasaferic.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gasaferic.database.MySQL;
import com.gasaferic.main.Main;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;

public class PlayerSurvivorRegistering implements Listener {

	SurvivorManager survivorManager = Main.getSurvivorManager();
	MySQL mySQL = Main.getMySQL();

	@EventHandler
	public void onJoinRegister(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (survivorManager.survivorAlreadyExists(player.getUniqueId())) {
			Survivor survivor = survivorManager.getSurvivorByUniqueId(player.getUniqueId());
			survivor.setPlayer(player);
		} else {
			Survivor survivor = new Survivor(player);
			mySQL.addSurvivor(survivor);
		}
	}

	@EventHandler
	public void onLeftDisable(PlayerQuitEvent e) {
		Player player = e.getPlayer();

		Survivor survivor = survivorManager.getSurvivorByPlayer(player);

		if (survivor.setupModeEnabled()) {
			survivor.toggleSetupMode();
		}
	}

}