package com.gasaferic.events.eventsmercanti.mercantesaccheggio.inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryMercanteSaccheggio {

	public static Inventory openMenu(Player player) {
		Inventory menucasa = Bukkit.createInventory(player, 54, "§7§lMercante Saccheggio");
		

			addItem(340, 1, 1, menucasa, "§fProgetto Elmo Kevlar");
			addItem(340, 10, 1, menucasa, "§fProgetto Corazza Kevlar");
			addItem(340, 19, 1, menucasa, "§fProgetto Gambali Kevlar");
			addItem(340, 28, 1, menucasa, "§fProgetto Stivali Kevlar");
			addItem(340, 37, 1, menucasa, "§fProgetto Sawed-Off");
			addItem(340, 46, 1, menucasa, "§fProgetto SV-98");
			addItem(340, 5, 1, menucasa, "§fProgetto Dinamite");
			addItem(340, 14, 1, menucasa, "§fProgetto TNT");
			addItem(369, 23, 3, menucasa, "§fDinamite");
			addItem(369, 32, 10, menucasa, "§fDinamite");
			addItem(84, 41, 1, menucasa, "§fCasa");
			addItem(46, 50, 1, menucasa, "§fTNT");
			addItem(351, 2, 10, menucasa, "§fScambia", "§f§lProgetto Elmo Kevlar » Dinamite");
			addItem(351, 11, 10, menucasa, "§fScambia", "§f§lProgetto Corazza Kevlar » Dinamite");
			addItem(351, 20, 10, menucasa, "§fScambia", "§f§lProgetto Gambali Kevlar » Dinamite");
			addItem(351, 29, 10, menucasa, "§fScambia", "§f§lProgetto Stivali Kevlar » Dinamite");
			addItem(351, 38, 10, menucasa, "§fScambia", "§f§lProgetto Sawed-Off » Dinamite");
			addItem(351, 47, 10, menucasa, "§fScambia", "§f§lProgetto SV-98 » Dinamite");
			addItem(351, 6, 10, menucasa, "§fScambia", "§f§lProgetto Dinamite » Dinamite");
			addItem(351, 15, 10, menucasa, "§fScambia", "§f§lProgetto TNT » Dinamite");
			addItem(351, 24, 10, menucasa, "§fScambia", "§f§lDinamite » Ascia in Ferro");
			addItem(351, 33, 10, menucasa, "§fScambia", "§f§lDinamite » Spranga");
			addItem(351, 42, 10, menucasa, "§fScambia", "§f§lCasa » Spranga");
			addItem(351, 51, 10, menucasa, "§fScambia", "§f§lTNT » Spranga");
			addItemQuantita(369, 3, 1, menucasa, "§fDinamite", "§aScambia per 1 Progetto Elmo Kevlar");
			addItemQuantita(369, 7, 1, menucasa, "§fDinamite", "§aScambia per 1 Progetto Dinamite");
			addItemQuantita(369, 12, 1, menucasa, "§fDinamite", "§aScambia per 1 Progetto Corazza Kevlar");
			addItemQuantita(369, 16, 5, menucasa, "§fDinamite", "§aScambia per 1 Progetto TNT");
			addItemQuantita(369, 21, 1, menucasa, "§fDinamite", "§aScambia per 1 Progetto Gambali Kevlar");
			addItemQuantita(369, 30, 1, menucasa, "§fDinamite", "§aScambia per 1 Progetto Stivali Kevlar");
			addItemQuantita(369, 48, 2, menucasa, "§fDinamite", "§aScambia per 1 Progetto SV-98");
			addItemQuantita(369, 39, 1, menucasa, "§fDinamite", "§aScambia per 1 Progetto Sawed-Off");
			addItemQuantita(258, 25, 1, menucasa, "§fAscia in Ferro", "§aScambia per 3 Dinamite");
			addItemQuantita(267, 34, 1, menucasa, "§fSpranga", "§aScambia per 10 Dinamite");
			addItemQuantita(267, 43, 1, menucasa, "§fSpranga", "§aScambia per 1 Rifugio");
			addItemQuantita(267, 52, 1, menucasa, "§fSpranga", "§aScambia per 1 TNT");
		return menucasa;
	}

	public static void addItem(String material, int slot, Inventory inventory, String name, String lore, String lore1,
			String lore2, String lore3) {
		ItemStack item = new ItemStack(Material.getMaterial(material));
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
