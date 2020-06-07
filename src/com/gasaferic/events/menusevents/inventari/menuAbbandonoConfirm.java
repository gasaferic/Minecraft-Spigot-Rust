package com.gasaferic.events.menusevents.inventari;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class menuAbbandonoConfirm {

	public static Inventory openMenuConferma(Player player) {
		Inventory menucasa = Bukkit.createInventory(player, 27, "§7§lConfirm");
			addItem(351, 11, 10, menucasa, "§a§lConfirm");
			addItem(351, 15, 1, menucasa, "§4§lCancel");
			
		return menucasa;
	}

	@SuppressWarnings("deprecation")
	public static void addItem(int id, int slot, int tipo, Inventory inventory, String name) {
		ItemStack item = new ItemStack(id, 1, (byte) tipo);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void openMenuRifugioConferma(Player player) {

		Inventory menucasa = openMenuConferma(player);
		player.openInventory(menucasa);

	}
}
