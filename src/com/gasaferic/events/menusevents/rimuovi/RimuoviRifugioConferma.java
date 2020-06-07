package com.gasaferic.events.menusevents.rimuovi;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.events.menusevents.inventari.menuAdminConferma;
import com.gasaferic.main.Main;

public class RimuoviRifugioConferma implements Listener {

	private Main plugin = Main.getInstance();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory() != null)
			;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		if (clicked.getType() == Material.WEB) { // The item that the player clicked its web
			if (event.getInventory().getName().contains("§7§lMenu Casa")) {
				if ((clicked.getItemMeta().getDisplayName().equals("§4§lRimuovi Rifugio")
						|| clicked.getItemMeta().getLore().contains("§6Rimuovi questo rifugio"))) {
					if (event.getInventory().getName()
							.contains("§7§lMenu Casa " + event.getInventory().getTitle().substring(14))) {
						OfflinePlayer playeroffline = Bukkit.getServer().getOfflinePlayer(event.getInventory().getTitle().substring(14));
						Player whoclicked = (Player) event.getWhoClicked();
						whoclicked.closeInventory();
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							public void run() {
								menuAdminConferma.openMenuRifugioConferma(whoclicked, playeroffline);
							}
						}, 1L);
					}
				}
			}
		}
	}
}