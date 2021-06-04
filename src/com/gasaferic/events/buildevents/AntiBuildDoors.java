package com.gasaferic.events.buildevents;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gasaferic.areaprotection.managers.AreaManager;
import com.gasaferic.areaprotection.model.Area;
import com.gasaferic.main.Main;

public class AntiBuildDoors implements Listener {

	private AreaManager areaManager = Main.getAreaManager();

	private static Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onBuildOutside(BlockPlaceEvent event) {

		Block block = event.getBlock();
		Player player = event.getPlayer();

		if (!player.hasPermission("rust.buildbypass")) {
			if ((block.getType().equals(Material.WOODEN_DOOR) || block.getType().equals(Material.IRON_DOOR_BLOCK)
					|| block.getType().equals(Material.TRAP_DOOR) || block.getType().equals(Material.IRON_TRAPDOOR))) {

				Area area;

				if ((area = areaManager.getAreaByLocation(block.getLocation())) != null) {
					if (area.isAreaOwner(player)) {
						event.setCancelled(false);
					} else if (!area.isAreaOwner(player)) {
						event.setCancelled(true);
						player.sendMessage(prefix + "§6Non puoi piazzare porte/botole in questa casa!");
					}
				} else {
					event.setCancelled(true);
					player.sendMessage(prefix + "§6Puoi piazzare le porte/botole solo in una casa!");
				}
			}
		}
	}

}