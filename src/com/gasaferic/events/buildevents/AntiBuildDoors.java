package com.gasaferic.events.buildevents;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gasaferic.main.Main;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class AntiBuildDoors implements Listener {

	private static Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onBuildOutside(BlockPlaceEvent event) {

		Block block = event.getBlock();
		Player player = event.getPlayer();

		if (!player.hasPermission("rust.buildbypass")) {
			if ((block.getType().equals(Material.WOODEN_DOOR) || block.getType().equals(Material.IRON_DOOR_BLOCK)
					|| block.getType().equals(Material.TRAP_DOOR) || block.getType().equals(Material.IRON_TRAPDOOR))) {

				if (getRegionsName(block.getLocation(), plugin) != null) {
					List<String> regionsIDList = getRegionsName(block.getLocation(), plugin);
					if (!regionsIDList.isEmpty()) {
						if (isRegionOwner(block.getLocation(), plugin, player)) {
							event.setCancelled(false);
						} else if (!isRegionOwner(block.getLocation(), plugin, player)) {
							event.setCancelled(true);
							player.sendMessage(prefix + "§6Non puoi piazzare porte/botole in questa casa!");
						}
					} else {
						event.setCancelled(true);
						player.sendMessage(prefix + "§6Puoi piazzare le porte/botole solo in una casa!");
					}
				} else {
					event.setCancelled(true);
					player.sendMessage(prefix + "§6Puoi piazzare le porte/botole solo in una casa!");
				}
			}
		}
	}

	public static BlockVector convertToSk89qBV(Location location) {
		return (new BlockVector(location.getX(), location.getY(), location.getZ()));
	}

	public static boolean isRegionOwner(Location location, Main plugin, Player player) {

		List<String> regionsIDList = getRegionsName(location, plugin);

		if (regionsIDList != null) {
			for (String regionID : regionsIDList) {
				if (regionID.equalsIgnoreCase("casa" + player.getName().toLowerCase())) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}

		return false;
	}

	public static List<String> getRegionsName(Location location, Main plugin) {

		List<String> regionsIDList = new ArrayList<String>();

		WorldGuardPlugin worldGuard = plugin.getWorldGuard();
		RegionManager regionManager = worldGuard.getRegionManager(location.getWorld());
		ApplicableRegionSet regions = regionManager.getApplicableRegions(location);

		if (regions.size() == 0) {
			return null;
		} else {
			for (ProtectedRegion region : regions) {
				if (region.getId().toLowerCase().contains("casa")) {
					regionsIDList.add(region.getId());
				}
			}
			return regionsIDList;
		}

	}

}