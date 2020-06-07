package com.gasaferic.resourcespawner;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.gasaferic.main.Main;

public class ResourceTreeSpawner implements Listener {

	private BukkitTask logReplacing;

	private Location treeSpawnerLoc;

	private ArrayList<ResourceBlock> logLocations;
	private ArrayList<ResourceBlock> logToReplace;

	private final int LOG_REPLACE_DELAY = 1;
	private final int LOG_REPLACE_TIME = 15;

	public ResourceTreeSpawner(Location treeSpawnerLoc) {

		this.treeSpawnerLoc = treeSpawnerLoc;
		this.logLocations = new ArrayList<ResourceBlock>();
		this.logToReplace = new ArrayList<ResourceBlock>();

		build();
	}

	public void build() {

		ResourceTreeSpawnerShape treeSpawnerShape = new ResourceTreeSpawnerShape(treeSpawnerLoc);

		for (Location logLoc : treeSpawnerShape.getLogLocations()) {
			logLoc.getBlock().setType(Material.LOG);
			logLocations.add(new ResourceBlock(logLoc, Material.LOG, new ItemStack(Material.LOG, 2),
					new ItemStack(Material.STICK, 3)));
		}

		logReplacing = new BukkitRunnable() {
			ResourceBlock currentResBlock = null;

			@Override
			public void run() {
				if (logToReplace.size() > 0) {
					currentResBlock = logToReplace.get(0);
					currentResBlock.setCurrentType(currentResBlock.getType());
					currentResBlock.getBlockLocation().getWorld().playEffect(currentResBlock.getBlockLocation(),
							Effect.TILE_BREAK, 1);
					currentResBlock.getBlockLocation().getWorld().playSound(currentResBlock.getBlockLocation(),
							Sound.DIG_STONE, 1, 1);
					logToReplace.remove(currentResBlock);
				}
			}
		}.runTaskTimer(Main.getInstance(), 0L, LOG_REPLACE_DELAY * 20);

	}

	public void stop() {
		logReplacing.cancel();
		logReplacing = null;
	}

	public ArrayList<ResourceBlock> getTreeSpawnerBlockLocs() {
		return logLocations;
	}

	public void addLogToReplace(ResourceBlock resourceBlock) {
		new BukkitRunnable() {
			@Override
			public void run() {
				logToReplace.add(resourceBlock);
			}
		}.runTaskLater(Main.getInstance(), (LOG_REPLACE_TIME * 20L));
	}

	public Location getTreeSpawnerLoc() {
		return treeSpawnerLoc;
	}

	@EventHandler
	public void onResBlockBreak(BlockBreakEvent e) {

		Block block = e.getBlock();

		if (block.getType().equals(Material.LOG)) {
			for (ResourceBlock resBlock : logLocations) {
				if (resBlock.getBlockLocation().equals(block.getLocation())
						&& resBlock.getType().equals(block.getType())) {
					if (Main.getSurvivorManager().getSurvivorByPlayer(e.getPlayer()).setupModeEnabled()) {
						ResourceSpawnerUtils.removeResourceTreeSpawner(this);
						Main.resourceTreeSpawners.remove(this);
						Main.getMySQL().deleteResTreeSpawner(this);
						e.getPlayer().sendMessage(Main.getInstance().getPrefixString("prefixStaff")
								+ "§6Hai rimosso un albero per risorse");
						return;
					}
					resBlock.setCurrentType(Material.AIR);
					resBlock.dropItems();
					addLogToReplace(resBlock);
				}

				e.setCancelled(true);
			}
		}
	}

}
