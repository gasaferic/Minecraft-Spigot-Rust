package com.gasaferic.events.menusevents.inventari;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class menuAdminConferma {

	public static void openMenuRifugioConferma(Player player, OfflinePlayer playercasa) {
		
		Inventory menucasa = Bukkit.createInventory(player, 27, "§7§lConferma " + playercasa.getName());

		addItem(Material.INK_SACK, 11, 10, menucasa, "§a§lConferma");
		addItem(Material.INK_SACK, 15, 1, menucasa, "§4§lAnnulla");
		
		player.openInventory(menucasa);

	}

	public static void addItem(Material material, int slot, int tipo, Inventory inventory, String name) {
		ItemStack item = new ItemStack(material, 1, (byte) tipo);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}
	
}