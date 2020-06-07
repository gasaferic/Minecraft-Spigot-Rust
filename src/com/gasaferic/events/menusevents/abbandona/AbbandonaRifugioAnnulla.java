package com.gasaferic.events.menusevents.abbandona;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class AbbandonaRifugioAnnulla implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory() != null)
			;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem();
		if ((clicked.getType() == Material.INK_SACK || clicked.getDurability() == 1)) {
			if (event.getInventory().getName().contains("§7§lConferma Abbandono")) {
				if (clicked.getItemMeta().getDisplayName().equals("§4§lAnnulla")) {
					Player whoclicked = (Player) event.getWhoClicked();
					whoclicked.closeInventory();
				}
			} else if (event.getInventory().getName().contains("§7§lConfirm")) {
				if (clicked.getItemMeta().getDisplayName().equals("§4§lCancel")) {
					Player whoclicked = (Player) event.getWhoClicked();
					whoclicked.closeInventory();
				}
			}
		}
	}
}