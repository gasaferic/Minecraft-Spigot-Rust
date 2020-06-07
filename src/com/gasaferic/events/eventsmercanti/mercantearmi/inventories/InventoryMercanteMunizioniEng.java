package com.gasaferic.events.eventsmercanti.mercantearmi.inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryMercanteMunizioniEng {

	public static Inventory openMenu(Player player) {
		Inventory menucasa = Bukkit.createInventory(player, 18, "§7§lAmmo Trader");
		
		addItem(Material.IRON_INGOT, 0, 1, 1, menucasa, "§fIron Ingot");
		addItem(Material.IRON_INGOT, 0, 2, 5, menucasa, "§fIron Ingot");
		addItem(Material.IRON_INGOT, 0, 4, 10, menucasa, "§fIron Ingot");
		addItem(Material.IRON_INGOT, 0, 6, 14, menucasa, "§fIron Ingot");
		addItem(Material.INK_SACK, 2, 10, menucasa, "§fTrade", "§f§lIron Ingot » Arrows");
		addItem(Material.INK_SACK, 6, 10, menucasa, "§fTrade", "§f§lIron Ingot » Pistol Ammo");
		addItem(Material.INK_SACK, 11, 10, menucasa, "§fTrade", "§f§lIron Ingot » Shotgun Ammo");
		addItem(Material.INK_SACK, 15, 10, menucasa, "§fTrade", "§f§lIron Ingot » Assault Ammo");
		addItemMunizioni(Material.ARROW, 3, 2, menucasa, "§aTrade for 1 Iron Ingot");
		addItemMunizioni(Material.SEEDS, 7, 4, menucasa, "§aTrade for 2 Iron Ingot");
		addItemMunizioni(Material.MELON_SEEDS, 12, 4, menucasa, "§aTrade for 4 Iron Ingot");
		addItemMunizioni(Material.PUMPKIN_SEEDS, 16, 4, menucasa, "§aTrade for 6 Iron Ingot");
		
		return menucasa;
	}

	public static void addItem(Material material, int tipo, int quantita, int slot, Inventory inventory, String name) {
		ItemStack item = new ItemStack(material, quantita, (short) tipo);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void addItem(Material material, int slot, int tipo, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(material, 1, (short) tipo);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void addItemMunizioni(Material material, int slot, int quantità, Inventory inventory, String lore) {
		ItemStack item = new ItemStack(material, quantità);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void openMenuArmi(Player player) {

		Inventory menucasa = openMenu(player);
		player.openInventory(menucasa);

	}
}
