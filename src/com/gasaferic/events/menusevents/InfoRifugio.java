package com.gasaferic.events.menusevents;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InfoRifugio implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory() != null)
			;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		String invName = event.getInventory().getName().replaceAll(String.valueOf(ChatColor.COLOR_CHAR), "");
		if (invName.contains("Casa")) {
			if (clicked.getType() == Material.EMPTY_MAP) { // The item that the player clicked it dirt
				event.setCancelled(true);
			}
		} else if (invName.contains("Shelter")) {
			if (clicked.getType() == Material.EMPTY_MAP) { // The item that the player clicked it dirt
				event.setCancelled(true);
			}
		}
	}
}