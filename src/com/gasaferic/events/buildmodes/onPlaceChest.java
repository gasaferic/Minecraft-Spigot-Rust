package com.gasaferic.events.buildmodes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gasaferic.main.Main;
import com.gasaferic.managers.RadChestManager;
import com.gasaferic.model.Survivor;

public class onPlaceChest implements Listener {

	private static Main plugin = Main.getInstance();
	
	private RadChestManager radChestManager = Main.getRadChestManager();

	private String prefix = plugin.getPrefixString("prefixStaff");

	@EventHandler
	public void onChestPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);

		if (survivor.setupModeEnabled()) {
			if (event.getBlock().getType() == Material.CHEST) {
				radChestManager.registerRadChest(event.getBlock().getLocation());
				player.sendMessage(prefix + "§6Loot Chest salvata nel database.");
			}
		}
	}

	@EventHandler
	public void onChestBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);

		if (survivor.setupModeEnabled()) {
			if (event.getBlock().getType() == Material.CHEST) {
				if (radChestManager.radchestAlreadyExists(event.getBlock().getLocation())) {
					radChestManager.unregisterRadChest(event.getBlock().getLocation());
					player.sendMessage(prefix + "§6Loot Chest rimossa dal database.");
				}
			}
		}
	}

}