package com.gasaferic.events.buildevents;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockUtils {

	public static boolean isFurniture(Block block) {
		if (block.getType().equals(Material.WORKBENCH) || block.getType().equals(Material.FURNACE)
				|| block.getType().equals(Material.CHEST) || block.getType().equals(Material.TORCH)
				|| block.getType().equals(Material.BED_BLOCK) || block.getType().equals(Material.WEB)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSecurity(Block block) {
		if (block.getType().equals(Material.IRON_DOOR_BLOCK) || block.getType().equals(Material.WOODEN_DOOR)
				|| block.getType().equals(Material.TRAP_DOOR) || block.getType().equals(Material.IRON_TRAPDOOR)) {
			return true;
		} else {
			return false;
		}
	}

}
