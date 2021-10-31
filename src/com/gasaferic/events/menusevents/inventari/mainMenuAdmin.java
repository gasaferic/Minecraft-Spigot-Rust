package com.gasaferic.events.menusevents.inventari;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gasaferic.main.Main;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Shelter;
import com.gasaferic.model.Survivor;

public class mainMenuAdmin {

	public static SurvivorManager survivorManager = Main.getSurvivorManager();
	public static ShelterManager shelterManager = Main.getShelterManager();

	public static Inventory openMenu(Player player, OfflinePlayer playercasa) {

		Inventory menucasa = Bukkit.createInventory(player, 27, "§7§lMenu Casa " + playercasa.getName());

		Survivor survivor = survivorManager.getSurvivorByUniqueId(playercasa.getUniqueId());
		Shelter playerShelter = shelterManager.getShelter(survivor);

		if (playerShelter != null) {

			addItem(30, 13, menucasa, "§4§lRimuovi Rifugio", "§6Rimuovi questo rifugio");

			switch (playerShelter.getBuiltFloors()) {
			case 1:
				addFloorItem(351, 11, menucasa, "§4§lCostruisci Piano " + playercasa.getName(), "§6Secondo Piano");
				break;
			case 2:
				addFloorItem(351, 11, menucasa, "§4§lCostruisci Piano " + playercasa.getName(), "§6Terzo Piano");
				break;
			case 3:
				addFloorItem(351, 11, menucasa, "§4§lCostruisci Piano " + playercasa.getName(), "§6Quarto Piano");
				break;
			case 4:
				addFloorItem(351, 11, menucasa, "§4§lCostruisci Piano " + playercasa.getName(), "§6Quinto Piano");
				break;
			case 5:
				addFloorItem(351, 11, menucasa, "§4§lPiano massimo " + playercasa.getName(),
						"§7Il player ha raggiunto l'ultimo piano costruibile");
				break;
			default:
				addFloorItem(351, 1, menucasa, "§4§lErrore", "§7Impossibile prendere l'informazioni del rifugio");
				break;
			}

			if (playerShelter.hasBuiltBunker()) {
				addBunkerItem(66, 15, menucasa, "§4§lBunker Costruito " + survivor.getName(),
						"§7Il player ha già costruito il bunker");
			} else if (!playerShelter.hasBuiltBunker()) {
				addBunkerItem(66, 15, menucasa, "§4§lCostruisci Bunker " + survivor.getName(), "§6Staff Bunker");
			}

		}
		return menucasa;

	}

	@SuppressWarnings("deprecation")
	public static void addItem(int id, int slot, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(id);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	@SuppressWarnings("deprecation")
	public static void addInfoItem(int id, int slot, Inventory inventory, String name, String lore, String lore1) {
		ItemStack item = new ItemStack(id);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemLore.add(lore1);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	@SuppressWarnings("deprecation")
	public static void addFloorItem(int id, int slot, Inventory inventory, String name, String lore, String lore1,
			String lore2) {
		ItemStack item = new ItemStack(id);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemLore.add(lore1);
		itemLore.add(lore2);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	@SuppressWarnings("deprecation")
	public static void addFloorItem(int id, int slot, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(id);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	@SuppressWarnings("deprecation")
	public static void addBunkerItem(int id, int slot, Inventory inventory, String name, String lore, String lore1) {
		ItemStack item = new ItemStack(id);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemLore.add(lore1);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	@SuppressWarnings("deprecation")
	public static void addBunkerItem(int id, int slot, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(id);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void openMenuCasa(Player player, OfflinePlayer playercasa) {

		Inventory menucasa = openMenu(player, playercasa);
		player.openInventory(menucasa);

	}
}
