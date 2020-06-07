package com.gasaferic.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gasaferic.main.Main;

public class SetBedSpawn implements Listener {
	
	private Main plugin = Main.getInstance();
	
	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onPlayerRightClickBed(PlayerInteractEvent event) {
		if (event.getClickedBlock() != null) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (event.getClickedBlock().getType() == Material.BED_BLOCK) {
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onPlaceBed(BlockPlaceEvent event) {
		if (event.getBlock().getType() == Material.BED_BLOCK) {
			event.getPlayer().setBedSpawnLocation(event.getBlock().getLocation());
			event.getPlayer().sendMessage(prefix + "§6Hai settato il tuo punto di respawn.");
		}
	}
}