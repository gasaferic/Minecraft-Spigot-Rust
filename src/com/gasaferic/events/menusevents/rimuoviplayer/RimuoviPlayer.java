package com.gasaferic.events.menusevents.rimuoviplayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;

public class RimuoviPlayer implements Listener {

	private static Main plugin = Main.getInstance();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		String invName = event.getInventory().getName().replaceAll(String.valueOf(ChatColor.COLOR_CHAR), "");
		if (invName.contains("Casa")) {
			if (clicked.getType() == Material.NETHER_BRICK_ITEM) { // The item that the player clicked it dirt
				event.setCancelled(true);
				player.closeInventory();
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						RimuoviPlayerInventory.openRimuoviPlayerMenu(player);
					}
				}, 1L);
			}
		} else if (invName.contains("Shelter")) {
			if (clicked.getType() == Material.NETHER_BRICK_ITEM) { // The item that the player clicked it dirt
				event.setCancelled(true);
				player.closeInventory();
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						RimuoviPlayerInventoryEng.openRimuoviPlayerMenu(player);
					}
				}, 1L);
			}
		}
	}
}