package com.gasaferic.events.menusevents.rimuovi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;
import com.gasaferic.main.Schematic;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Shelter;
import com.gasaferic.model.Survivor;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;

public class RimuoviRifugio implements Listener {
	
	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();

	private Main plugin = Main.getInstance();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		if (event.getInventory() == null || event.getCurrentItem() == null) {
			return;
		}
		
		ItemStack clicked = event.getCurrentItem();

		Player player = (Player) event.getWhoClicked();
		
		if ((clicked.getType() == Material.INK_SACK || clicked.getDurability() == 10)) {
			if (!(event.getInventory().getName().equals("§7§lConferma Abbandono"))) {
				if (event.getInventory().getName().contains("§7§lConferma")) {
					if (clicked.getItemMeta().getDisplayName().equals("§a§lConferma")) {
						
						OfflinePlayer offlinePlayer = Bukkit.getServer()
								.getOfflinePlayer(event.getInventory().getTitle().substring(13));

						Survivor survivor = survivorManager.getSurvivorByOfflinePlayer(offlinePlayer);
						Shelter playerShelter = shelterManager.getShelter(survivor);
						
						WorldGuardPlugin wg = plugin.getWorldGuard();
						RegionManager rm = wg.getRegionManager(player.getWorld());

						rm.removeRegion("Casa" + player.getName());
						
						Location pastingLocation = playerShelter.getConsoleBlock().getLocation();

						shelterManager.deleteShelter(survivor);

						Schematic.paste("casaabbandonata.schematic", pastingLocation, false);

						event.setCancelled(true);
						player.closeInventory();

						event.getWhoClicked().closeInventory();
						event.getWhoClicked()
								.sendMessage("§4[Rust] - §6Hai rimosso il rifugio di " + offlinePlayer.getName() + ".");
						
					}
				}
			}
		}
	}
}