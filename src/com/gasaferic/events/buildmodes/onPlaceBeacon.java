package com.gasaferic.events.buildmodes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gasaferic.main.Main;
import com.gasaferic.managers.AirDropManager;
import com.gasaferic.model.Survivor;

public class onPlaceBeacon implements Listener {

	private static Main plugin = Main.getInstance();
	
	private AirDropManager airdropManager = Main.getAirDropManager();

	private String prefix = plugin.getPrefixString("prefixStaff");

	@EventHandler
	public void onBeaconPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);

		if (survivor.setupModeEnabled()) {
			if (event.getBlock().getType() == Material.BEACON) {
				airdropManager.registerAirdrop(event.getBlock().getLocation());
				player.sendMessage(prefix + "§6Airdrop salvato nel database.");
			}
		}
	}

	@EventHandler
	public void onBeaconBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);

		if (survivor.setupModeEnabled()) {
			if (event.getBlock().getType() == Material.BEACON) {
				if (airdropManager.airdropAlreadyExists(event.getBlock().getLocation())) {
					airdropManager.unregisterAirdrop(event.getBlock().getLocation());
					player.sendMessage(prefix + "§6Airdrop rimosso dal database.");
				}
			}
		}
	}

}