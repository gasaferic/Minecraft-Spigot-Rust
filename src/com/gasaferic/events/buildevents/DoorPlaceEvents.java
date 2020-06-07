package com.gasaferic.events.buildevents;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gasaferic.main.Main;

public class DoorPlaceEvents implements Listener {

	private static Main plugin = Main.getInstance();

	@EventHandler
	public void onPlaceIronDoor(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (AntiBuildDoors.isRegionOwner(e.getBlock().getLocation(), plugin, player)) {
			if ((e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.GRASS
					|| e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.DIRT
					|| e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.STONE)) {
				if (e.getBlockPlaced().getType() == Material.IRON_DOOR_BLOCK) {
					Block b = e.getBlockPlaced().getRelative(BlockFace.UP);
					Main.getMetadataManager().saveDoor(e.getBlock(), e.getPlayer());
					Main.getMetadataManager().saveDoor(b, e.getPlayer());
				} else if (!AntiBuildDoors.isRegionOwner(e.getBlock().getLocation(), plugin, player)) {
					if ((e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS
							|| e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIRT
							|| e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.STONE)) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onBreakIronDoor(BlockBreakEvent e) {
		if (e.getBlock().getRelative(BlockFace.UP).getType() == Material.IRON_DOOR_BLOCK) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Block b = e.getBlock().getRelative(BlockFace.UP);
				Main.getMetadataManager().removeDoor(e.getBlock(), e.getPlayer());
				Main.getMetadataManager().removeDoor(b, e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		} else if (e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.IRON_DOOR_BLOCK) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Block b = e.getBlock().getRelative(BlockFace.DOWN);
				Main.getMetadataManager().removeDoor(e.getBlock(), e.getPlayer());
				Main.getMetadataManager().removeDoor(b, e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlaceWoodDoor(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (AntiBuildDoors.isRegionOwner(e.getBlock().getLocation(), plugin, player)) {
			if ((e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.GRASS
					|| e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.DIRT
					|| e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.STONE)) {
				if (e.getBlockPlaced().getType() == Material.WOODEN_DOOR) {
					Block b = e.getBlockPlaced().getRelative(BlockFace.UP);
					Main.getMetadataManager().saveDoor(e.getBlock(), e.getPlayer());
					Main.getMetadataManager().saveDoor(b, e.getPlayer());
				} else if (!AntiBuildDoors.isRegionOwner(e.getBlock().getLocation(), plugin, player)) {
					if ((e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS
							|| e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIRT
							|| e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.STONE)) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onBreakWoodDoor(BlockBreakEvent e) {
		if (e.getBlock().getRelative(BlockFace.UP).getType() == Material.WOODEN_DOOR) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Block b = e.getBlock().getRelative(BlockFace.UP);
				Main.getMetadataManager().removeDoor(e.getBlock(), e.getPlayer());
				Main.getMetadataManager().removeDoor(b, e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		} else if (e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOODEN_DOOR) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Block b = e.getBlock().getRelative(BlockFace.DOWN);
				Main.getMetadataManager().removeDoor(e.getBlock(), e.getPlayer());
				Main.getMetadataManager().removeDoor(b, e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlaceIronTrapDoor(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (AntiBuildDoors.isRegionOwner(e.getBlock().getLocation(), plugin, player)) {
			if (e.getBlockPlaced().getType() == Material.IRON_TRAPDOOR) {
				Main.getMetadataManager().saveTrapdoor(e.getBlock(), e.getPlayer());
			} else if (!AntiBuildDoors.isRegionOwner(e.getBlock().getLocation(), plugin, player)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBreakIronTrapDoor(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.IRON_TRAPDOOR) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Main.getMetadataManager().removeTrapdoor(e.getBlock(), e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlaceWoodTrapDoor(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (AntiBuildDoors.isRegionOwner(e.getBlock().getLocation(), plugin, player)) {
			if (e.getBlockPlaced().getType() == Material.TRAP_DOOR) {
				Main.getMetadataManager().saveTrapdoor(e.getBlock(), e.getPlayer());
			} else if (!AntiBuildDoors.isRegionOwner(e.getBlock().getLocation(), plugin, player)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBreakWoodTrapDoor(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.TRAP_DOOR) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Main.getMetadataManager().removeTrapdoor(e.getBlock(), e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		}
	}
}
