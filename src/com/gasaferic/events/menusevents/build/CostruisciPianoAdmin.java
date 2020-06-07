package com.gasaferic.events.menusevents.build;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Floor;
import com.gasaferic.model.Survivor;

public class CostruisciPianoAdmin implements Listener {

	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		if (event.getInventory() == null || event.getCurrentItem() == null) {
			return;
		}

		ItemStack clicked = event.getCurrentItem();

		Player player = (Player) event.getWhoClicked();
		Survivor survivor = survivorManager.getSurvivorByPlayer(player);

		if (clicked.getType() == Material.INK_SACK && event.getInventory().getName().contains("§7§lMenu Casa")) {
			if (clicked.getItemMeta().getLore().contains("§6Secondo Piano")) {

				OfflinePlayer shelterOwner = Bukkit.getServer()
						.getOfflinePlayer(event.getInventory().getTitle().substring(14));
				Survivor offlineSurvivor = survivorManager.getSurvivorByOfflinePlayer(shelterOwner);

				FloorMethod.buildFloor(shelterManager.getShelter(offlineSurvivor), survivor, Floor.SECOND, false);

				player.closeInventory();

			} else if (clicked.getItemMeta().getLore().contains("§6Terzo Piano")) {

				OfflinePlayer shelterOwner = Bukkit.getServer()
						.getOfflinePlayer(event.getInventory().getTitle().substring(14));
				Survivor offlineSurvivor = survivorManager.getSurvivorByOfflinePlayer(shelterOwner);

				FloorMethod.buildFloor(shelterManager.getShelter(offlineSurvivor), survivor, Floor.THIRD, false);

				player.closeInventory();

			} else if (clicked.getItemMeta().getLore().contains("§6Quarto Piano")) {

				OfflinePlayer shelterOwner = Bukkit.getServer()
						.getOfflinePlayer(event.getInventory().getTitle().substring(14));
				Survivor offlineSurvivor = survivorManager.getSurvivorByOfflinePlayer(shelterOwner);

				FloorMethod.buildFloor(shelterManager.getShelter(offlineSurvivor), survivor, Floor.FOURTH, false);

				player.closeInventory();

			} else if (clicked.getItemMeta().getLore().contains("§6Quinto Piano")) {

				OfflinePlayer shelterOwner = Bukkit.getServer()
						.getOfflinePlayer(event.getInventory().getTitle().substring(14));
				Survivor offlineSurvivor = survivorManager.getSurvivorByOfflinePlayer(shelterOwner);

				FloorMethod.buildFloor(shelterManager.getShelter(offlineSurvivor), survivor, Floor.FIFTH, false);

				player.closeInventory();

			} else if (clicked.getItemMeta().getLore().contains("§4§lPiano massimo")) {
				player.sendMessage(Main.getInstance().getPrefixString("prefix") + "§7Il player ha raggiunto l'ultimo piano costruibile");
			}
			
			event.setCancelled(true);
		} else if (clicked.getType() == Material.RAILS && event.getInventory().getName().contains("§7§lMenu Casa")) {
			if (clicked.getItemMeta().getLore().contains("§6Staff Bunker")) {

				OfflinePlayer shelterOwner = Bukkit.getServer()
						.getOfflinePlayer(event.getInventory().getTitle().substring(14));
				Survivor offlineSurvivor = survivorManager.getSurvivorByOfflinePlayer(shelterOwner);

				FloorMethod.buildBunker(shelterManager.getShelter(offlineSurvivor), survivor, Floor.BUNKER, false);

				player.closeInventory();
			}
		}
	}
}