package com.gasaferic.resourcespawner;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.gasaferic.main.Main;

public class ResourceSpawner implements Listener {

	private BukkitTask cobblestoneReplacing;
	private BukkitTask oreReplacing;

	private Location resSpawnerLoc;

	private ArrayList<ResourceBlock> resBlockLocations;
	private ArrayList<ResourceBlock> cobbleToReplace;
	private ArrayList<ResourceBlock> oreToReplace;

	private final int COBBLESTONE_REPLACE_DELAY = 1;
	private final int COBBLESTONE_REPLACE_TIME = 15;
	private final int ORE_REPLACE_DELAY = 5;
	private final int ORE_REPLACE_TIME = 30;

	public ResourceSpawner(Location resSpawnerLoc) {

		this.resSpawnerLoc = resSpawnerLoc;
		this.resBlockLocations = new ArrayList<ResourceBlock>();
		this.cobbleToReplace = new ArrayList<ResourceBlock>();
		this.oreToReplace = new ArrayList<ResourceBlock>();

		build();
	}

	public void build() {

		ResourceSpawnerShape resSpawnerShape = new ResourceSpawnerShape(resSpawnerLoc);

		for (Location outLoc : resSpawnerShape.getOutsideShape()) {
			outLoc.getBlock().setType(Material.COBBLESTONE);
			resBlockLocations.add(new ResourceBlock(outLoc, Material.COBBLESTONE));
		}

		for (Location inLoc : resSpawnerShape.getInsideShape()) {
			Material missingMaterial = getMissingOre();
			inLoc.getBlock().setType(missingMaterial);
			ResourceBlock resourceBlock = new ResourceBlock(inLoc, missingMaterial);
			if (missingMaterial.equals(Material.COAL_ORE)) {
				resourceBlock.addDropItem(new ItemStack(Material.COAL, 1));
			}
			resBlockLocations.add(resourceBlock);
		}

		cobblestoneReplacing = new BukkitRunnable() {
			ResourceBlock currentResBlock = null;

			@Override
			public void run() {
				if (cobbleToReplace.size() > 0) {
					currentResBlock = cobbleToReplace.get(0);
					currentResBlock.setCurrentType(currentResBlock.getType());
					currentResBlock.getBlockLocation().getWorld().playEffect(currentResBlock.getBlockLocation(),
							Effect.TILE_BREAK, 1);
					currentResBlock.getBlockLocation().getWorld().playSound(currentResBlock.getBlockLocation(),
							Sound.DIG_STONE, 1, 1);
					cobbleToReplace.remove(currentResBlock);
				}
			}
		}.runTaskTimer(Main.getInstance(), 0L, COBBLESTONE_REPLACE_DELAY * 20);

		oreReplacing = new BukkitRunnable() {
			ResourceBlock currentResBlock = null;

			@Override
			public void run() {
				if (oreToReplace.size() > 0) {
					currentResBlock = oreToReplace.get(0);
					currentResBlock.setCurrentType(currentResBlock.getType());
					currentResBlock.getBlockLocation().getWorld().playEffect(currentResBlock.getBlockLocation(),
							Effect.TILE_BREAK, 1);
					currentResBlock.getBlockLocation().getWorld().playSound(currentResBlock.getBlockLocation(),
							Sound.DIG_STONE, 1, 1);
					oreToReplace.remove(currentResBlock);
				}
			}
		}.runTaskTimer(Main.getInstance(), 0L, ORE_REPLACE_DELAY * 20);

	}

	public ArrayList<ResourceBlock> getResSpawnerBlockLocs() {
		return resBlockLocations;
	}

	public void addOreToReplace(ResourceBlock resourceBlock, String oreType) {

		int replaceTime = oreType == "cobble" ? COBBLESTONE_REPLACE_TIME : ORE_REPLACE_TIME;

		new BukkitRunnable() {
			@Override
			public void run() {
				if (oreType == "cobble") {
					cobbleToReplace.add(resourceBlock);
				} else {
					oreToReplace.add(resourceBlock);
				}
			}
		}.runTaskLater(Main.getInstance(), (replaceTime * 20L));
	}

	public void stop() {
		cobblestoneReplacing.cancel();
		cobblestoneReplacing = null;
		oreReplacing.cancel();
		oreReplacing = null;
	}

	public Material getMissingOre() {

		int coalCount = 0;
		int ironCount = 0;
		int sulphurCount = 0;

		for (ResourceBlock resBlock : resBlockLocations) {
			if (resBlock.getType().equals(Material.COAL_ORE)) {
				coalCount++;
			} else if (resBlock.getType().equals(Material.IRON_ORE)) {
				ironCount++;
			} else if (resBlock.getType().equals(Material.GOLD_ORE)) {
				sulphurCount++;
			}
		}

		if (coalCount == 0) {
			return Material.COAL_ORE;
		} else if (coalCount < 2) {
			if (ironCount == 0) {
				return Material.IRON_ORE;
			} else if (sulphurCount == 0) {
				return Material.GOLD_ORE;
			} else {
				return Material.COAL_ORE;
			}
		} else {
			return null;
		}

	}

	public Location getResSpawnerLoc() {
		return resSpawnerLoc;
	}

	@EventHandler
	public void onResBlockBreak(BlockBreakEvent e) {

		Player player = e.getPlayer();
		Block block = e.getBlock();

		if (ResourceSpawnerUtils.isResSpawnerBlock(block)) {
			for (ResourceBlock resBlock : resBlockLocations) {
				if (resBlock.getBlockLocation().equals(block.getLocation())
						&& resBlock.getType().equals(block.getType())) {
					if (Main.getSurvivorManager().getSurvivorByPlayer(player).setupModeEnabled()) {
						ResourceSpawnerUtils.removeResourceSpawner(this);
						Main.resourceSpawners.remove(this);
						Main.getMySQL().deleteResSpawner(this);
						e.getPlayer().sendMessage(Main.getInstance().getPrefixString("prefixStaff")
								+ "§6Hai rimosso un generatore di risorse");
						return;
					}
					Material blockType = resBlock.getType();
					resBlock.setCurrentType(Material.AIR);
					if (blockType.equals(Material.COBBLESTONE)) {
						resBlock.dropItems();
						addOreToReplace(resBlock, "cobble");
					} else {
						if (ResourceSpawnerUtils.canMineResource(player.getItemInHand())) {
							resBlock.dropItems();
						}
						addOreToReplace(resBlock, "ore");
					}
				}

				e.setCancelled(true);
			}
		}
	}

}
