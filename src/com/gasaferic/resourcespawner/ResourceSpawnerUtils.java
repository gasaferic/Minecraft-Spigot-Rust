package com.gasaferic.resourcespawner;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Schematic;

public class ResourceSpawnerUtils implements Listener {

	public static boolean isResSpawnerBlock(Block block) {
		if (block.getType().equals(Material.COBBLESTONE) || block.getType().equals(Material.COAL_ORE)
				|| block.getType().equals(Material.IRON_ORE) || block.getType().equals(Material.GOLD_ORE)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean canMineResource(ItemStack item) {
		if (item.getType().equals(Material.STONE_PICKAXE)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void removeResourceSpawner(ResourceSpawner resourceSpawner) {
		for (ResourceBlock resourceBlock : resourceSpawner.getResSpawnerBlockLocs()) {
			resourceBlock.setCurrentType(Material.AIR);
		}
	}
	
	public static void removeResourceTreeSpawner(ResourceTreeSpawner resourceTreeSpawner) {
		for (ResourceBlock resourceBlock : resourceTreeSpawner.getTreeSpawnerBlockLocs()) {
			resourceBlock.setCurrentType(Material.AIR);
		}
		Schematic.paste("resourcetreeclear.schematic", resourceTreeSpawner.getTreeSpawnerLoc(), false);
	}

}
