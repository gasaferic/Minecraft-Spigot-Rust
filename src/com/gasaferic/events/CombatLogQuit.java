package com.gasaferic.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gasaferic.events.teams.TeamMethod;
import com.gasaferic.main.Main;

public class CombatLogQuit implements Listener {
	
	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onPlayerJoinEvent(PlayerQuitEvent event) {
		Player player = (Player) event.getPlayer();

		if (TeamMethod.combatLog.contains(player)) {
			TeamMethod.combatLog.remove(player);
			player.damage(100.0D);
			Bukkit.broadcastMessage(prefix + "§2" + player.getName() + " §6è uscito dal server in §2PvP§6.");
		}
	}
}