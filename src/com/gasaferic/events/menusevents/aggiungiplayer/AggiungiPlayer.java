package com.gasaferic.events.menusevents.aggiungiplayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;
import com.gasaferic.model.Survivor;

public class AggiungiPlayer implements Listener {

	private static Main plugin = Main.getInstance();

	private AggiungiPlayerInventory aggiungiPlayerInventory = new AggiungiPlayerInventory();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (event.getInventory() == null) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}

		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);
		ItemStack clickedItem = event.getCurrentItem();
		String invName = event.getInventory().getName().replaceAll(String.valueOf(ChatColor.COLOR_CHAR), "");

		if (invName.contains("Casa")) {
			if (clickedItem.getType() == Material.CLAY_BRICK) {
				event.setCancelled(true);
				player.closeInventory();
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						aggiungiPlayerInventory.openAggiungiPlayer(player, survivor.getAddPlayerPage());
					}
				}, 1L);
			}
		} else if (invName.contains("Aggiungi Sopravvissuto")) {
			event.setCancelled(true);
			if (clickedItem.getType() == Material.STAINED_GLASS_PANE) {
				if (clickedItem.getDurability() == 5) {
					int newPageIndex = survivor.getAddPlayerPage() + 1;
					if (survivor.getAddPlayerPage() == aggiungiPlayerInventory.neededPages) {
						newPageIndex--;
					}
					survivor.setAddPlayerPage(newPageIndex);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							aggiungiPlayerInventory.openAggiungiPlayer(player, survivor.getAddPlayerPage());
						}
					}, 1L);
				} else if (clickedItem.getDurability() == 14) {
					survivor.setAddPlayerPage(survivor.getAddPlayerPage() - 1);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							aggiungiPlayerInventory.openAggiungiPlayer(player, survivor.getAddPlayerPage());
						}
					}, 1L);
				}
				survivor.setChangingPage(true);
				player.closeInventory();
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();

		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);
		String invName = event.getInventory().getName().replaceAll(String.valueOf(ChatColor.COLOR_CHAR), "");
		if (!survivor.isChangingPage()) {
			if (invName.contains("Aggiungi Sopravvissuto")) {
				survivor.setAddPlayerPage(1);
			}
		} else {
			survivor.setChangingPage(false);
		}
	}

}