package com.gasaferic.events.buildevents;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.gasaferic.main.Main;

public class MethodBuild {

	public static boolean enoughSpace(Block block, Main plugin, Player player, String prefix) {
		boolean enoughSpace = true;
		boolean done = false;
		Location min = block.getLocation().clone().add(-3, 0, 1);
		Location max = block.getLocation().clone().add(4, 6, -6);
		for (int currentY = min.getBlockY(); currentY < max.getBlockY(); currentY++) {
			for (int minX = min.getBlockX(); minX < max.getBlockX(); minX++) {
				for (int maxZ = max.getBlockZ(); maxZ < min.getBlockZ(); maxZ++) {
					Block selectedBlock = block.getWorld().getBlockAt(minX, currentY, maxZ);
					if (!selectedBlock.getType().equals(Material.AIR)) {
						if (!selectedBlock.equals(block)) {
							// player.sendBlockChange(selectedBlock.getLocation(), Material.STAINED_GLASS, (byte) 14);
							enoughSpace = false;
							done = true;
						}
					}
				}
			}
			if (done) {
				break;
			}
		}
		return enoughSpace;

	}

	public static boolean noDoorSpace(Block block, Main plugin, Player player, String prefix) {
		if (block.getLocation().add(0, 0, 1).getBlock().getType().equals(Material.AIR)
				&& block.getLocation().add(0, 1, 1).getBlock().getType().equals(Material.AIR)) {
			return true;
		}
		return false;
	}

	public static boolean baseGrass(Block block, Main plugin, Player player, String prefix) {
		boolean grassBase = true;
		boolean done = false;
		Location min = block.getLocation().clone().add(-3, 0, 1);
		Location max = block.getLocation().clone().add(4, 0, -6);
		for (int minX = min.getBlockX(); minX < max.getBlockX(); minX++) {
			for (int maxZ = max.getBlockZ(); maxZ < min.getBlockZ(); maxZ++) {
				Block selectedBlock = block.getWorld().getBlockAt(minX, min.getBlockY() - 1, maxZ);
				if (!selectedBlock.getType().equals(Material.GRASS)) {
					// player.sendBlockChange(selectedBlock.getLocation(), Material.STAINED_GLASS, (byte) 14);
					grassBase = false;
					done = true;
				}
			}
			if (done) {
				break;
			}
		}
		
		return grassBase;
	}
	
}
