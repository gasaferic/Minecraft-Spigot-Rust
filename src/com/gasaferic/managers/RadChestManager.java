package com.gasaferic.managers;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;

import com.gasaferic.database.MySQL;
import com.gasaferic.main.Main;
import com.gasaferic.main.RandomLoots;

public class RadChestManager {

	private MySQL mySQL = Main.getMySQL();

	private ArrayList<Location> radchestLocations;
	private final int REGEN_TIME;

	public RadChestManager(ArrayList<Location> radchestLocations, int regenTime) {
		
		this.radchestLocations = radchestLocations;
		this.REGEN_TIME = regenTime;
		
	}
	
	public void start() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			public void run() {
				for (Location location : radchestLocations) {
						if (!location.getBlock().getType().equals(Material.CHEST)) {
							location.getBlock().setType(Material.CHEST);
						}
							Chest chest = (Chest) location.getBlock().getState();

							Random rand = new Random();

							int rangeMax = 26;
							int rangeMin = 0;

							RandomLoots loots = new RandomLoots();

							chest.getBlockInventory().clear();
							chest.getBlockInventory().setItem(rand.nextInt((rangeMax - rangeMin) + 1), loots.getRandomLoot());
							chest.getBlockInventory().setItem(rand.nextInt((rangeMax - rangeMin) + 1), loots.getRandomLoot());
							chest.getBlockInventory().setItem(rand.nextInt((rangeMax - rangeMin) + 1), loots.getRandomLoot());
				}
				Bukkit.getServer().getConsoleSender().sendMessage("§c§lRust §6Chest Refillate.");
			}
		}, 0L, REGEN_TIME * 20L);
		
	}
	
	public boolean radchestAlreadyExists(Location location) {
		if (radchestLocations.contains(location)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void registerRadChest(Location location) {
		if (!radchestLocations.contains(location)) {
			radchestLocations.add(location);
			mySQL.saveLootResource(location, "radchest");
		}
	}
	
	public void unregisterRadChest(Location location) {
		if (radchestLocations.contains(location)) {
			radchestLocations.remove(location);
			mySQL.deleteLootResource(location, "radchest");
		}
	}
}