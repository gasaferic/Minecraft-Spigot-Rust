package com.gasaferic.events;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gasaferic.main.Main;

public class EquipParachute implements Listener {

	private Main plugin = Main.getInstance();

	@EventHandler
	public void ParachuteDrop(PlayerInteractEvent ev) {
		Player player = (Player) ev.getPlayer();

		if (ev.getAction().equals(Action.PHYSICAL)) {
			if (ev.getClickedBlock().getType() == Material.STONE_PLATE) {
				List<String> list = plugin.getConfig().getStringList("Parachute");
				Random random = new Random();
				String string = list.get(random.nextInt(list.size()));
				Location loc = fromString(string);
				loc.setYaw(0);
				loc.setPitch(0);
				player.teleport(loc);
				player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 2), false);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
						() -> equipParachute(player, player.getLocation(), plugin), 15L);
			}
		}
	}

	@EventHandler
	public void InteractBeacon(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getType() == Material.BEACON) {
				event.setCancelled(true);
			}
		}
	}

	public void equipParachute(Player player, Location loc, Main plugin) {
		ArmorStand parachute = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		ItemStack skull = new ItemStack(Material.CARPET, 1, (short) 8);
		parachute.setHelmet(skull);
		parachute.setGravity(true);
		parachute.setVisible(false);
		parachute.setMaxHealth(20);
		parachute.setHealth(20);
		parachute.setBasePlate(false);
		parachute.setPassenger(player);
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
