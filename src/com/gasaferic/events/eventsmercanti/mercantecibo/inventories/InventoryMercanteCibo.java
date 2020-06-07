package com.gasaferic.events.eventsmercanti.mercantecibo.inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryMercanteCibo {

	public static Inventory openMenu(Player player) {
		Inventory menucasa = Bukkit.createInventory(player, 18, "§7§lMercante Cibo");
		
		addItem(Material.LOG, 0, 2, 1, menucasa, "§fLegno");
		addItem(Material.IRON_ORE, 0, 2, 5, menucasa, "§fFerro grezzo");
		addItem(Material.LOG, 0, 4, 10, menucasa, "§fLegno");
		addItem(Material.IRON_ORE, 0, 1, 14, menucasa, "§fFerro grezzo");
		addItem(Material.INK_SACK, 2, 10, menucasa, "§fScambia", "§f§lLegno » Mela");
		addItem(Material.INK_SACK, 6, 10, menucasa, "§fScambia", "§f§lFerro grezzo » Pane");
		addItem(Material.INK_SACK, 11, 10, menucasa, "§fScambia", "§f§lLegno » Tonno in scatola");
		addItem(Material.INK_SACK, 15, 10, menucasa, "§fScambia", "§f§lFerro grezzo » Borraccia d'Acqua");
		addItemQuantita(Material.APPLE, 3, 1, menucasa, "§fMela", "§aScambia per 2 di Legno");
		addItemQuantita(Material.BREAD, 7, 1, menucasa, "§fPane", "§aScambia per 2 di Ferro grezzo");
		addItemQuantita(Material.MUSHROOM_SOUP, 12, 1, menucasa, "§fTonno in scatola", "§aScambia per 4 di Legno");
		addItemQuantita(Material.POTION, 16, 1, menucasa, "§fBorraccia d'Acqua", "§aScambia per 1 Ferro grezzo");
		
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

	public static void addItemQuantita(Material material, int slot, int quantità, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(material, quantità);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void openMenuCibo(Player player) {

		Inventory menucasa = openMenu(player);
		player.openInventory(menucasa);

	}
	
}