package com.gasaferic.events.menusevents.rimuovi;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class RimuoviRifugioAnnulla implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory() != null)
			;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		if ((clicked.getType() == Material.INK_SACK || clicked.getDurability() == 1)) {
			if (event.getInventory().getName().contains("§7§lConferma ")) {
				if (clicked.getItemMeta().getDisplayName().equals("§4§lAnnulla")) {
					if (event.getInventory().getName()
							.contains("§7§lConferma " + event.getInventory().getTitle().substring(13))) {
						Player whoclicked = (Player) event.getWhoClicked();
						whoclicked.closeInventory();
					}
				}
			}
		}
	}
}