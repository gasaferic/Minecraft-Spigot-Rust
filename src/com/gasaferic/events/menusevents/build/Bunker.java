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

public class Bunker implements Listener {
	
	private String prefix = Main.getInstance().getPrefixString("prefix");

	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Survivor survivor = survivorManager.getSurvivorByPlayer(player);

		if (event.getInventory() == null || event.getCurrentItem() == null) {
			return;
		}
		
		ItemStack clicked = event.getCurrentItem();
		if (clicked.getType() == Material.RAILS) {
			String invName = event.getInventory().getName().replaceAll(String.valueOf(ChatColor.COLOR_CHAR), "");
			if (invName.contains("Casa")) {
				if (clicked.getItemMeta().getLore().contains("§6300 Pietra")) {
					FloorMethod.buildBunker(shelterManager.getShelter(survivor), survivor, Floor.BUNKER);
				} else if (clicked.getItemMeta().getLore().contains("§7Hai già costruito il bunker")) {
					player.closeInventory();
					player.sendMessage(prefix + "§6Hai gia costruito il bunker!");
				}
				event.setCancelled(true);
			} else if (invName.contains("Shelter")) {
				if (clicked.getItemMeta().getLore().contains("§6300 Cobblestone")) {
					FloorMethod.buildBunker(shelterManager.getShelter(survivor), survivor, Floor.BUNKER);
				} else if (clicked.getItemMeta().getLore().contains("§7You already built a bunker")) {
					player.closeInventory();
					player.sendMessage(prefix + "§6You already built a bunker!");
				}
				event.setCancelled(true);
			}
		}
	}
}