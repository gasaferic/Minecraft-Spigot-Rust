package com.gasaferic.events.buildevents;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.gasaferic.main.Main;

public class AntiBreakDoors implements Listener {

	private static Main plugin = Main.getInstance();

	@EventHandler
	public void onBuildOutside(BlockBreakEvent event) {

		Block block = event.getBlock();

		if (BlockUtils.isSecurity(block)) {
			if (!event.getPlayer().hasPermission("rust.admin")) {
				if (AntiBuildDoors.isRegionOwner(block.getLocation(), plugin, event.getPlayer())) {
					event.setCancelled(false);
				} else if (!AntiBuildDoors.isRegionOwner(block.getLocation(), plugin, event.getPlayer())) {
					event.setCancelled(true);
				}
			} else if (!event.getPlayer().hasPermission("rust.admin")) {
				event.setCancelled(false);
			}
		}
	}
}