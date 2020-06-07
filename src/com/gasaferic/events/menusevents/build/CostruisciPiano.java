package com.gasaferic.events.menusevents.build;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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

public class CostruisciPiano implements Listener {

	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Survivor survivor = survivorManager.getSurvivorByPlayer(player);
		
		if (event.getInventory() != null)
			;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem();
		if (clicked.getType() == Material.INK_SACK) {
			
			String invName = event.getInventory().getName().replaceAll(String.valueOf(ChatColor.COLOR_CHAR), "");
			if (invName.contains("Casa")) {
				switch (clicked.getItemMeta().getLore().get(0)) {
				case "§8Costruisci il secondo piano":
					FloorMethod.buildFloor(shelterManager.getShelter(survivor), survivor, Floor.SECOND);
					break;
				case "§8Costruisci il terzo piano":
					FloorMethod.buildFloor(shelterManager.getShelter(survivor), survivor, Floor.THIRD);
					break;
				case "§8Costruisci il quarto piano":
					FloorMethod.buildFloor(shelterManager.getShelter(survivor), survivor, Floor.FOURTH);
					break;
				case "§8Costruisci il quinto piano":
					FloorMethod.buildFloor(shelterManager.getShelter(survivor), survivor, Floor.FIFTH);
					break;
				case "§7Hai raggiunto l'ultimo piano costruibile":
					player.sendMessage(Main.getInstance().getPrefixString("prefix") + "§6Hai raggiunto l'ultimo piano costruibile!");
					break;
				}
				event.setCancelled(true);
			} else if (invName.contains("Shelter")) {
				switch (clicked.getItemMeta().getLore().get(0)) {
				case "§8Build the second floor":
					FloorMethod.buildFloor(shelterManager.getShelter(survivor), survivor, Floor.SECOND);
					break;
				case "§8Build the third floor":
					FloorMethod.buildFloor(shelterManager.getShelter(survivor), survivor, Floor.THIRD);
					break;
				case "§8Build the fourth floor":
					FloorMethod.buildFloor(shelterManager.getShelter(survivor), survivor, Floor.FOURTH);
					break;
				case "§8Build the fifth floor":
					FloorMethod.buildFloor(shelterManager.getShelter(survivor), survivor, Floor.FIFTH);
					break;
				case "§7You reached the max floor":
					player.sendMessage(Main.getInstance().getPrefixString("prefix") + "§6You reached the max floor!");
					break;
				}
				event.setCancelled(true);
			}
		}
	}
}