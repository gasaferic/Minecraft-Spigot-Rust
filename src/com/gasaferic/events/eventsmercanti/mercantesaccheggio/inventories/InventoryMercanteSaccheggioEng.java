package com.gasaferic.events.eventsmercanti.mercantesaccheggio.inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryMercanteSaccheggioEng {

	public static Inventory openMenu(Player player) {
		Inventory menucasa = Bukkit.createInventory(player, 54, "§7§lLooting Trader");
		

			addItem(340, 1, 1, menucasa, "§fSchematic Helmet Kevlar");
			addItem(340, 10, 1, menucasa, "§fSchematic Chestplate Kevlar");
			addItem(340, 19, 1, menucasa, "§fSchematic Leggings Kevlar");
			addItem(340, 28, 1, menucasa, "§fSchematic Boots Kevlar");
			addItem(340, 37, 1, menucasa, "§fSchematic Sawed-Off");
			addItem(340, 46, 1, menucasa, "§fSchematic SV-98");
			addItem(340, 5, 1, menucasa, "§fSchematic Dynamite");
			addItem(340, 14, 1, menucasa, "§fSchematic TNT");
			addItem(369, 23, 3, menucasa, "§fDynamite");
			addItem(369, 32, 10, menucasa, "§fDynamite");
			addItem(84, 41, 1, menucasa, "§fShelter");
			addItem(46, 50, 1, menucasa, "§fTNT");
			addItem(351, 2, 10, menucasa, "§fTrade", "§f§lSchematic Helmet Kevlar » Dynamite");
			addItem(351, 11, 10, menucasa, "§fTrade", "§f§lSchematic Chestplate Kevlar » Dynamite");
			addItem(351, 20, 10, menucasa, "§fTrade", "§f§lSchematic Leggings Kevlar » Dynamite");
			addItem(351, 29, 10, menucasa, "§fTrade", "§f§lSchematic Boots Kevlar » Dynamite");
			addItem(351, 38, 10, menucasa, "§fTrade", "§f§lSchematic Sawed-Off » Dynamite");
			addItem(351, 47, 10, menucasa, "§fTrade", "§f§lSchematic SV-98 » Dynamite");
			addItem(351, 6, 10, menucasa, "§fTrade", "§f§lSchematic Dinamite » Dynamite");
			addItem(351, 15, 10, menucasa, "§fTrade", "§f§lSchematic TNT » Dynamite");
			addItem(351, 24, 10, menucasa, "§fTrade", "§f§lDynamite » Iron Axe");
			addItem(351, 33, 10, menucasa, "§fTrade", "§f§lDynamite » Crowbar");
			addItem(351, 42, 10, menucasa, "§fTrade", "§f§lShelter » Crowbar");
			addItem(351, 51, 10, menucasa, "§fTrade", "§f§lTNT » Crowbar");
			addItemQuantita(369, 3, 1, menucasa, "§fDynamite", "§aTrade for 1 Schematic Helmet Kevlar");
			addItemQuantita(369, 7, 1, menucasa, "§fDynamite", "§aTrade for 1 Schematic Dinamite");
			addItemQuantita(369, 12, 1, menucasa, "§fDynamite", "§aTrade for 1 Schematic Chestplate Kevlar");
			addItemQuantita(369, 16, 5, menucasa, "§fDynamite", "§aTrade for 1 Schematic TNT");
			addItemQuantita(369, 21, 1, menucasa, "§fDynamite", "§aTrade for 1 Schematic Leggings Kevlar");
			addItemQuantita(369, 30, 1, menucasa, "§fDynamite", "§aTrade for 1 Schematic Boots Kevlar");
			addItemQuantita(369, 48, 2, menucasa, "§fDynamite", "§aTrade for 1 Schematic SV-98");
			addItemQuantita(369, 39, 1, menucasa, "§fDynamite", "§aTrade for 1 Schematic Sawed-Off");
			addItemQuantita(258, 25, 1, menucasa, "§fAscia in Ferro", "§aTrade for 3 Dynamite");
			addItemQuantita(267, 34, 1, menucasa, "§fCrowbar", "§aTrade for 10 Dynamite");
			addItemQuantita(267, 43, 1, menucasa, "§fCrowbar", "§aTrade for 1 Shelter");
			addItemQuantita(267, 52, 1, menucasa, "§fCrowbar", "§aTrade for 1 TNT");
		return menucasa;
	}

	@SuppressWarnings("deprecation")
	public static void addItem(int id, int slot, Inventory inventory, String name, String lore, String lore1,
			String lore2, String lore3) {
		ItemStack item = new ItemStack(id);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add("§8§m--------------------------------");
		itemLore.add(lore);
		itemLore.add(lore1);
		itemLore.add(lore2);
		itemLore.add(lore3);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	@SuppressWarnings("deprecation")
	public static void addItem(int id, int slot, int tipo, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(id, 1, (short) tipo);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	@SuppressWarnings("deprecation")
	public static void addItem(int id, int slot, int quantità, Inventory inventory, String name) {
		ItemStack item = new ItemStack(id, quantità);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	@SuppressWarnings("deprecation")
	public static void addItemQuantita(int id, int slot, int quantità, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(id, quantità);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void openMenuSaccheggio(Player player) {

		Inventory menucasa = openMenu(player);
		player.openInventory(menucasa);

	}
}
