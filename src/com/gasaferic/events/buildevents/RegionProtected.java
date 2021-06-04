package com.gasaferic.events.buildevents;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.gasaferic.areaprotection.managers.AreaManager;
import com.gasaferic.main.Main;

public class RegionProtected {
	
	static AreaManager areaManager = Main.getAreaManager();

	public static boolean isProtected(Block block, Main plugin, Player player) {
		boolean cannotBuild = false;
		boolean done = false;
		int min = -27;
		int max = 27;
		for (int minX = min; minX < max; minX++) {
			for (int maxZ = max; maxZ > min; maxZ--) {
				if (areaManager.isAreaProtected(block.getLocation().add(minX, 0, maxZ))) {
					// player.sendBlockChange(block.getLocation().clone().add(minX, -1, maxZ), Material.STAINED_GLASS, (byte) 14);
					cannotBuild = true;
					done = true;
				}
			}
			if (done) {
				break;
			}
		}
		return cannotBuild;
	}

}