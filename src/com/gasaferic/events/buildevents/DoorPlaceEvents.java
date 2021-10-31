package com.gasaferic.events.buildevents;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gasaferic.areaprotection.managers.AreaManager;
import com.gasaferic.areaprotection.model.Area;
import com.gasaferic.main.Main;

public class DoorPlaceEvents implements Listener {

	private AreaManager areaManager = Main.getAreaManager();

	@EventHandler
	public void onPlaceIronDoor(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		Area area;

		if ((area = areaManager.getAreaByLocation(e.getBlock().getLocation())) != null) {
			if (area.isAreaOwner(player)) {
				if ((e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.GRASS
						|| e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.DIRT
						|| e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.STONE)) {
					if (e.getBlockPlaced().getType() == Material.IRON_DOOR_BLOCK) {
						Main.getMetadataManager().saveDoor(e.getBlock(), e.getPlayer());
					}
				}
			} else if (!area.isAreaOwner(player)) {
				if ((e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS
						|| e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIRT
						|| e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.STONE)) {
					e.setCancelled(true);
				}
			}
		}

	}

	@EventHandler
	public void onBreakIronDoor(BlockBreakEvent e) {
		if (e.getBlock().getRelative(BlockFace.UP).getType() == Material.IRON_DOOR_BLOCK) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Main.getMetadataManager().removeDoor(e.getBlock(), e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		} else if (e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.IRON_DOOR_BLOCK) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Block b = e.getBlock().getRelative(BlockFace.DOWN);
				Main.getMetadataManager().removeDoor(b, e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlaceWoodDoor(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		Area area;

		if ((area = areaManager.getAreaByLocation(e.getBlock().getLocation())) != null) {
			if (area.isAreaOwner(player)) {
				if ((e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.GRASS
						|| e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.DIRT
						|| e.getBlock().getRelative(BlockFace.DOWN).getType() != Material.STONE)) {
					if (e.getBlockPlaced().getType() == Material.WOODEN_DOOR) {
						Main.getMetadataManager().saveDoor(e.getBlock(), e.getPlayer());
					}
				}
			} else if (!area.isAreaOwner(player)) {
				if ((e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS
						|| e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIRT
						|| e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.STONE)) {
					e.setCancelled(true);
				}
			}
		}

	}

	@EventHandler
	public void onBreakWoodDoor(BlockBreakEvent e) {
		if (e.getBlock().getRelative(BlockFace.UP).getType() == Material.WOODEN_DOOR) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Main.getMetadataManager().removeDoor(e.getBlock(), e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		} else if (e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOODEN_DOOR) {
			if (e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock()))
					|| e.getPlayer().hasPermission("rust.doorbreakbypass")) {
				Block b = e.getBlock().getRelative(BlockFace.DOWN);
				Main.getMetadataManager().removeDoor(b, e.getPlayer());
			} else if (!(e.getPlayer().getUniqueId().equals(Main.getMetadataManager().getBlockOwner(e.getBlock())))) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlaceIronTrapDoor(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		Area area;

		if ((area = areaManager.getAreaByLocation(e.getBlock().getLocation())) != null) {
			if (area.isAreaOwner(player)) {
				if (e.getBlockPlaced().getType() == Material.IRON_TRAPDOOR) {
					Main.getMetadataManager().saveTrapdoor(e.getBlock(), e.getPlayer());
				}
			} else if (!area.isAreaOwner(player)) {
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

		Area area;

		if ((area = areaManager.getAreaByLocation(e.getBlock().getLocation())) != null) {
			if (area.isAreaOwner(player)) {
				if (e.getBlockPlaced().getType() == Material.TRAP_DOOR) {
					Main.getMetadataManager().saveTrapdoor(e.getBlock(), e.getPlayer());
				}
			} else if (!area.isAreaOwner(player)) {
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
