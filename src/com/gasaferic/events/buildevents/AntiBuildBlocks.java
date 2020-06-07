package com.gasaferic.events.buildevents;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class AntiBuildBlocks implements Listener {

	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {
		Block block = e.getBlock();
		Player player = e.getPlayer();
		if (!allowedToBypass(player)) {
			if (isAllowed(block)) {
				e.setCancelled(false);
			} else {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent e) {
		Block block = e.getBlock();
		Player player = e.getPlayer();
		if (!allowedToBypass(player)) {
			if (isAllowed(block)) {
				e.setCancelled(false);
			} else if (!isAllowed(block)) {
				e.setCancelled(true);
			}
		}

	}

	public boolean isAllowed(Block block) {
		if (BlockUtils.isFurniture(block) || BlockUtils.isSecurity(block)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean allowedToBypass(Player player) {
		if (player.hasPermission("rust.buildbypass")) {
			return true;
		} else {
			return false;
		}
	}

}