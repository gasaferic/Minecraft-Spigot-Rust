package com.gasaferic.events.menusevents.rimuoviplayer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;
import com.gasaferic.model.Team;

public class RimuoviPlayerEvent implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();

	@EventHandler
	public void onPlayerInteractEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Survivor survivor = survivorManager.getSurvivorByPlayer(player);

		ItemStack clicked = event.getCurrentItem();
		Inventory inventory = event.getInventory();
		if (inventory.getTitle().contains("§7§lRimuovi Player")) {
			if ((clicked.getType() == Material.SKULL) || (clicked.getDurability() == 3)) {
				if (clicked.getAmount() != 0) {
					String targetname = clicked.getItemMeta().getDisplayName().substring(2);

					Survivor clickedSurvivor = survivorManager
							.getSurvivorByOfflinePlayer(Bukkit.getPlayerExact(targetname));

					Team team = shelterManager.getShelter(survivor).getTeam();

					team.removeTeamMember(clickedSurvivor);

					player.closeInventory();
					player.sendMessage(prefix + "§6" + targetname + " rimosso dal rifugio");
					event.setCancelled(true);

				} else {
					event.setCancelled(true);
				}
			}
		} else if (inventory.getTitle().contains("§7§lRemove Player")) {
			if ((clicked.getType() == Material.SKULL) || (clicked.getDurability() == 3)) {
				if (clicked.getAmount() != 0) {
					String targetname = clicked.getItemMeta().getDisplayName().substring(2);

					Survivor clickedSurvivor = survivorManager.getSurvivorByPlayer(Bukkit.getPlayerExact(targetname));

					Team team = shelterManager.getShelter(survivor).getTeam();

					team.addTeamMember(clickedSurvivor);

					player.closeInventory();
					player.sendMessage(prefix + "§6" + targetname + " removed from the shelter");
					event.setCancelled(true);

				} else {
					event.setCancelled(true);
				}
			}
		}
	}
}