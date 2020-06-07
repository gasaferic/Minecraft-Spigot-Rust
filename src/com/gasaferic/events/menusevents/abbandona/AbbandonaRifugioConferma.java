package com.gasaferic.events.menusevents.abbandona;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.events.menusevents.inventari.menuAbbandonoConferma;
import com.gasaferic.events.menusevents.inventari.menuAbbandonoConfirm;
import com.gasaferic.main.Main;

public class AbbandonaRifugioConferma implements Listener {

	private Main plugin = Main.getInstance();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory() != null)
			;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem();
		if (clicked.getType() == Material.WEB) {
			String invName = event.getInventory().getName().replaceAll(String.valueOf(ChatColor.COLOR_CHAR), "");
			if (invName.contains("Casa")) {
				if ((clicked.getItemMeta().getDisplayName().equals("§4§lAbbandona Rifugio")
						|| clicked.getItemMeta().getLore().contains("§6Abbandona il tuo rifugio"))) {
					Player whoclicked = (Player) event.getWhoClicked();
					whoclicked.closeInventory();
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							menuAbbandonoConferma.openMenuRifugioConferma(whoclicked);
						}
					}, 1L);
				}
			} else if (invName.contains("Shelter")) {
				if ((clicked.getItemMeta().getDisplayName().equals("§4§lLeave your Shelter")
						|| clicked.getItemMeta().getLore().contains("§6Leave your Shelter"))) {
					Player whoclicked = (Player) event.getWhoClicked();
					whoclicked.closeInventory();
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

						public void run() {
							menuAbbandonoConfirm.openMenuRifugioConferma(whoclicked);
						}
					}, 1L);
				}
			}
		}
	}
}