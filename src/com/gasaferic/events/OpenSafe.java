package com.gasaferic.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gasaferic.main.Main;

public class OpenSafe implements Listener {

	List<Player> cooldown = new ArrayList<Player>();

	public Main plugin = Main.getInstance();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			if (player.getItemInHand().getType() == Material.TRIPWIRE_HOOK) {
				if (e.getClickedBlock().getType() == Material.SMOOTH_BRICK) {
					if (e.getClickedBlock().getData() == 3) {
						e.setCancelled(true);
						if (!cooldown.contains(player)) {
							for (String locstring : plugin.getConfig().getStringList("CancelloSafe")) {
								Location location = fromString(locstring);
								Block blocco = location.getBlock();
								blocco.setType(Material.AIR);
								blocco.getWorld().playSound(blocco.getLocation(), Sound.PISTON_RETRACT, 1, 1);
								cooldown.add(player);
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									public void run() {
										Location location = fromString(locstring);
										Block blocco = location.getBlock();
										blocco.setType(Material.IRON_FENCE);
										blocco.getWorld().playSound(blocco.getLocation(), Sound.PISTON_RETRACT, 1, 1);
									}
								}, 20L);
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									public void run() {
										if (cooldown.contains(player)) {
											cooldown.remove(player);
										}
									}
								}, 40L);
							}
						}
					}
				}
			}
		}
	}

	/*@EventHandler
	public void onPlayerVipPass(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (!player.getItemInHand().getType().equals(Material.TRIPWIRE_HOOK)) {
			if (player.hasPermission("rust.safezonepass")) {
				for (String locstring : plugin.getConfig().getStringList("CancelloSafe")) {
					Location location = fromString(locstring);
					double distance = player.getLocation().getX() - location.getBlockX();
					if (distance >= -0.75 && distance <= 1.25) {
						Block block = location.getBlock();
						block.setType(Material.AIR);

						for (Entity entities : player.getNearbyEntities(30.0, 30.0, 30.0)) {
							if (entities instanceof Player) {
								Player players = (Player) entities;
								if (!players.equals(player)) {
									((CraftPlayer) players).sendBlockChange(location, 101, (byte) 0);
								}
							}
						}
					} else {
						((CraftPlayer) player).sendBlockChange(location, 101, (byte) 0);
						for (String locString : plugin.getConfig().getStringList("CancelloSafe")) {
							Location locBlock = fromString(locString);
							Block block = locBlock.getBlock();
							block.setType(Material.IRON_FENCE);
						}
						for (Entity entities : player.getNearbyEntities(30.0, 30.0, 30.0)) {
							if (entities instanceof Player) {
								Player players = (Player) entities;
								if (!players.equals(player)) {
									((CraftPlayer) players).sendBlockChange(location, 101, (byte) 0);
								}
							}
						}
					}
				}
			} else if (!player.hasPermission("rust.safezonebypass")) {
				for (String locstring : plugin.getConfig().getStringList("CancelloSafe")) {
					Location location = fromString(locstring);
					double distance = player.getLocation().getBlockX() - location.getBlockX();
					if (distance >= -1.0 && distance <= 1.0 || distance == 0.0) {
						((CraftPlayer) player).sendBlockChange(location, 101, (byte) 0);
					} else if (player.getLocation().getBlockX() != location.getBlockX() - 1
							|| player.getLocation().getBlockX() != location.getBlockX()
							|| player.getLocation().getBlockX() != location.getBlockX() + 1
									&& player.getLocation().getBlockZ() != location.getBlockZ()) {
						((CraftPlayer) player).sendBlockChange(location, 101, (byte) 0);
						for (Player players : Bukkit.getOnlinePlayers()) {
							((CraftPlayer) players).sendBlockChange(location, 101, (byte) 0);
						}
					}
				}
			}
		}
	}*/

	@EventHandler
	public void onPlayerPlaceKey(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.TRIPWIRE_HOOK) {
			e.setCancelled(true);
		}
	}

	public Location fromString(String loc) {
		loc = loc.substring(loc.indexOf("{") + 1);
		loc = loc.substring(loc.indexOf("{") + 1);
		String worldName = loc.substring(loc.indexOf("=") + 1, loc.indexOf("}"));
		loc = loc.substring(loc.indexOf(",") + 1);
		String xCoord = loc.substring(loc.indexOf("=") + 1, loc.indexOf(","));
		loc = loc.substring(loc.indexOf(",") + 1);
		String yCoord = loc.substring(loc.indexOf("=") + 1, loc.indexOf(","));
		loc = loc.substring(loc.indexOf(",") + 1);
		String zCoord = loc.substring(loc.indexOf("=") + 1, loc.indexOf(","));
		loc = loc.substring(loc.indexOf(",") + 1);
		String pitch = loc.substring(loc.indexOf("=") + 1, loc.indexOf(","));
		loc = loc.substring(loc.indexOf(",") + 1);
		String yaw = loc.substring(loc.indexOf("=") + 1, loc.indexOf("}"));
		return new Location(Bukkit.getWorld(worldName), Double.parseDouble(xCoord), Double.parseDouble(yCoord),
				Double.parseDouble(zCoord), Float.parseFloat(yaw), Float.parseFloat(pitch));
	}
}