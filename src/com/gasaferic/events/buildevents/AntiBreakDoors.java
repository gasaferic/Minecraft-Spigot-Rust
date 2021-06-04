package com.gasaferic.events.buildevents;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.gasaferic.areaprotection.managers.AreaManager;
import com.gasaferic.areaprotection.model.Area;
import com.gasaferic.main.Main;

public class AntiBreakDoors implements Listener {

	private AreaManager areaManager = Main.getAreaManager();

	@EventHandler
	public void onBuildOutside(BlockBreakEvent event) {

		Block block = event.getBlock();

		if (BlockUtils.isSecurity(block)) {
			if (!event.getPlayer().hasPermission("rust.admin")) {
				Area area;
				if ((area = areaManager.getAreaByLocation(block.getLocation())) != null) {
					if (area.isAreaOwner(event.getPlayer())) {
						event.setCancelled(false);
					} else if (!area.isAreaOwner(event.getPlayer())) {
						event.setCancelled(true);
					}
				}
			} else if (!event.getPlayer().hasPermission("rust.admin")) {
				event.setCancelled(false);
			}
		}
	}
}