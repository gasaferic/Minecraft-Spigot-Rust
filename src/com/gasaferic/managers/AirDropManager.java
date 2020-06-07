package com.gasaferic.managers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftChest;
import org.bukkit.entity.Player;

import com.gasaferic.database.MySQL;
import com.gasaferic.main.Main;
import com.gasaferic.main.RandomLoots;

import net.minecraft.server.v1_8_R3.TileEntityChest;

public class AirDropManager {

	private Main plugin = Main.getInstance();

	private MySQL mySQL = Main.getMySQL();

	private String prefix = plugin.getPrefixString("prefix");

	private ArrayList<Location> airdropLocations;
	private final int REGEN_TIME;

	public AirDropManager(ArrayList<Location> airdropLocations, int regenTime) {

		this.airdropLocations = airdropLocations;
		this.REGEN_TIME = regenTime;

	}

	public void start() {

		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				if (airdropLocations != null) {
					for (Location location : airdropLocations) {
						if (!location.getBlock().getType().equals(Material.BEACON)) {
							location.getBlock().setType(Material.BEACON);
						}

						Location chestLoc = location.clone().add(0, 1, 0);
						Block block = chestLoc.getBlock();
						block.setType(Material.CHEST);
						Chest chest = (Chest) block.getState();

						setChestName(chest, "Airdrop");

						RandomLoots loots = new RandomLoots();

						Random rand = new Random();

						int rangeMax = 26;
						int rangeMin = 0;

						chest.getBlockInventory().clear();
						chest.getBlockInventory().setItem(rand.nextInt((rangeMax - rangeMin) + 1),
								loots.getRandomLoot());
						chest.getBlockInventory().setItem(rand.nextInt((rangeMax - rangeMin) + 1),
								loots.getRandomLoot());
						chest.getBlockInventory().setItem(rand.nextInt((rangeMax - rangeMin) + 1),
								loots.getRandomLoot());
						chest.update();
					}

					if (Bukkit.getOnlinePlayers().size() > 0) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							player.sendMessage(prefix
									+ "§6I soccorsi hanno sganciato delle §2Provviste Aeree §6tramite dei §2Paracadute §6nei pressi delle §2Montagne Rocciose");
						}
					}
				}
			}
		}, 0L, REGEN_TIME * 20L);

	}

	public boolean airdropAlreadyExists(Location location) {
		if (airdropLocations.contains(location)) {
			return true;
		} else {
			return false;
		}
	}

	public void registerAirdrop(Location location) {
		if (!airdropLocations.contains(location)) {
			airdropLocations.add(location);
			mySQL.saveLootResource(location, "airdrop");
		}
	}

	public void unregisterAirdrop(Location location) {
		if (airdropLocations.contains(location)) {
			airdropLocations.remove(location);
			mySQL.deleteLootResource(location, "airdrop");
		}
	}

	public void setChestName(Chest chest, String name) {
		Field inventoryField = null;
		try {
			inventoryField = CraftChest.class.getDeclaredField("chest");
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		inventoryField.setAccessible(true);
		TileEntityChest teChest = null;
		try {
			teChest = ((TileEntityChest) inventoryField.get((CraftChest) chest));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		teChest.a(name);
	}
}