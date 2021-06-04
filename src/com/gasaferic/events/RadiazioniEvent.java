package com.gasaferic.events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.gasaferic.areaprotection.events.AreaEnterEvent;
import com.gasaferic.areaprotection.events.AreaLeaveEvent;
import com.gasaferic.areaprotection.model.Area;
import com.gasaferic.main.Main;

public class RadiazioniEvent implements Listener {

	public static Map<Player, Integer> radiazioni = new HashMap<Player, Integer>();

	private Main plugin = Main.getInstance();

	@EventHandler
	public void onRegionEnter(AreaEnterEvent e) {
		Player player = e.getPlayer();
		if (isRadzoneArea(e.getArea(), plugin)) {
			if (!hasAntiRad(player)) {
				if (!(radiazioni.containsKey(player))) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100000, 2));
					radzoneScheduler(player);
				}
			}
		}
	}

	@EventHandler
	public void onRegionLeave(AreaLeaveEvent e) {
		Player player = e.getPlayer();
		if (isRadzoneArea(e.getArea(), plugin)) {
			if (radiazioni.containsKey(player)) {
				Bukkit.getScheduler().cancelTask((radiazioni.get(player)));
				radiazioni.remove(player);
				player.removePotionEffect(PotionEffectType.HUNGER);
			}
		}
	}

	@EventHandler
	public void onEquipArmor(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (e.getSlotType().equals(SlotType.ARMOR)) {
			ItemStack armorPiece = e.getCurrentItem();
			if (armorPiece.getType().equals(Material.GOLD_HELMET)
					|| armorPiece.getType().equals(Material.GOLD_CHESTPLATE)
					|| armorPiece.getType().equals(Material.GOLD_LEGGINGS)
					|| armorPiece.getType().equals(Material.GOLD_BOOTS)) {
				if (isRadzoneArea(Main.getAreaProtectionAPI().getAreaByLocation(player.getLocation()), plugin)) {
					if (hasAntiRad(player)) {
						if (radiazioni.containsKey(player)) {
							Bukkit.getScheduler().cancelTask((radiazioni.get(player)));
							radiazioni.remove(player);
							player.removePotionEffect(PotionEffectType.HUNGER);
						}
					} else if (!hasAntiRad(player)) {
						if (!(radiazioni.containsKey(player))) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100000, 2));
							radzoneScheduler(player);
						}
					}
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

					@Override
					public void run() {
						if (isRadzoneArea(Main.getAreaProtectionAPI().getAreaByLocation(player.getLocation()), plugin)) {
							if (!hasAntiRad(player)) {
								if (!(radiazioni.containsKey(player))) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100000, 2));
									radzoneScheduler(player);
								}
							}
						}
					}
				}, 5L);
			}
		}
	}

	public boolean hasAntiRad(Player player) {
		if (player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null
				&& player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null
				&& player.getInventory().getHelmet().getType() == Material.GOLD_HELMET
				&& player.getInventory().getChestplate().getType() == Material.GOLD_CHESTPLATE
				&& player.getInventory().getLeggings().getType() == Material.GOLD_LEGGINGS
				&& player.getInventory().getBoots().getType() == Material.GOLD_BOOTS) {
			return true;
		} else if (player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null
				&& player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null
				&& player.getInventory().getHelmet().getType() != Material.GOLD_HELMET
				&& player.getInventory().getChestplate().getType() != Material.GOLD_CHESTPLATE
				&& player.getInventory().getLeggings().getType() != Material.GOLD_LEGGINGS
				&& player.getInventory().getBoots().getType() != Material.GOLD_BOOTS
				|| player.getInventory().getHelmet() == null && player.getInventory().getChestplate() == null
						&& player.getInventory().getLeggings() == null && player.getInventory().getBoots() == null) {
			return false;
		}
		return false;
	}

	public void radzoneScheduler(Player player) {
		new BukkitRunnable() {
			public void run() {
				if (!hasAntiRad(player)) {
					player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
					player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 1, 1);
					radiazioni.put(player, this.getTaskId());
				} else if (hasAntiRad(player)) {
					if (radiazioni.containsKey(player)) {
						Bukkit.getScheduler().cancelTask(radiazioni.get(player));
						player.removePotionEffect(PotionEffectType.HUNGER);
						radiazioni.remove(player);
					}
				}
			}
		}.runTaskTimerAsynchronously(plugin, 0L, 20L);

	}

	public static boolean isRadzoneArea(Area area, Main plugin) {
		if (area == null) {
			return false;
		}

		for (String radzoneAreaString : plugin.getConfig().getStringList("RegionsRad")) {
			if (area.getAreaName().equalsIgnoreCase(radzoneAreaString)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}