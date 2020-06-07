package com.gasaferic.events.buildevents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gasaferic.main.Main;
import com.sk89q.worldedit.BlockVector;

public class CraftingFurnaceTemp implements Listener {

	private static Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onTempCrafting(BlockPlaceEvent e) {
		Block block = e.getBlock();
		Location location = block.getLocation();
		if (block.getType() == Material.WORKBENCH) {
			if (!(Main.getPlayersRegionManager().isRegionProtected(location, plugin))) {
				e.getPlayer().sendMessage(prefix + "§6Questa crafting verrà rimossa tra 5 minuti.");
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						if (location.getBlock() != null) {
							location.getBlock().breakNaturally();
						}
					}
				}, 6000L);
			}
		}
	}

	@EventHandler
	public void onTempFurnace(BlockPlaceEvent e) {
		Block block = e.getBlock();
		Location location = block.getLocation();
		if (block.getType() == Material.FURNACE) {
			if (!(Main.getPlayersRegionManager().isRegionProtected(location, plugin))) {
				e.getPlayer().sendMessage(prefix + "§6Questa fornace verrà rimossa tra 5 minuti.");
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						if (location.getBlock() != null) {
							location.getBlock().breakNaturally();
						}
					}
				}, 6000L);
			}
		}
	}

	@EventHandler
	public void onTempTorch(BlockPlaceEvent e) {
		Block block = e.getBlock();
		Location location = block.getLocation();
		if (block.getType() == Material.TORCH) {
			if (!(Main.getPlayersRegionManager().isRegionProtected(location, plugin))) {
				if (!e.getPlayer().isOp()) {
					e.getPlayer().sendMessage(prefix + "§6Questa torcia verrà rimossa tra 5 minuti.");
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							if (location.getBlock() != null) {
								location.getBlock().breakNaturally();
							}
						}
					}, 6000L);
				}
			}
		}
	}

	@EventHandler
	public void onTempBed(BlockPlaceEvent e) {
		Block block = e.getBlock();
		Location location = block.getLocation();
		if (block.getType() == Material.BED_BLOCK) {
			if (!(Main.getPlayersRegionManager().isRegionProtected(location, plugin))) {
				e.getPlayer().sendMessage(prefix + "§6Questo letto verrà rimosso tra 5 minuti.");
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						if (location.getBlock() != null) {
							location.getBlock().breakNaturally();
						}
					}
				}, 6000L);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlaceGrass(BlockPlaceEvent e) {
		Block block = e.getBlock();
		if (block.getType() == Material.LONG_GRASS && block.getData() == 1) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlaceLog(BlockPlaceEvent e) {
		if (!(e.getPlayer().isOp())) {
			Block block = e.getBlock();
			if (block.getType() == Material.LOG) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlaceTNT(BlockPlaceEvent e) {
		Block block = e.getBlock();
		if (block.getType() == Material.TNT) {
			e.setCancelled(true);
		}
	}

	public static BlockVector convertToSk89qBV(Location location) {
		return (new BlockVector(location.getX(), location.getY(), location.getZ()));
	}
	
}