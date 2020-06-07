package com.gasaferic.events.menusevents.inventari;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gasaferic.main.Main;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Shelter;
import com.gasaferic.model.Survivor;

public class mainMenu {

	public static SurvivorManager survivorManager = Main.getSurvivorManager();
	public static ShelterManager shelterManager = Main.getShelterManager();

	public static Inventory openMenu(Player player, Player playercasa) {

		String title = "Casa";

		StringBuilder builder = new StringBuilder();

		for (char c : title.toCharArray()) {
			builder.append(ChatColor.COLOR_CHAR).append(c);
		}

		String hiddenTitle = builder.toString();

		Inventory menucasa = Bukkit.createInventory(player, 9, "§r" + playercasa.getName() + " " + hiddenTitle);

		Survivor survivor = survivorManager.getSurvivorByPlayer(playercasa);
		Shelter playerShelter = shelterManager.getShelter(survivor);

		if (playerShelter != null) {

			switch (playerShelter.getBuiltFloors()) {
			case 1:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lCostruisci Piano", "§8Costruisci il secondo piano",
						"§6350 Legno", "§612 Ferro");
				break;
			case 2:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lCostruisci Piano", "§8Costruisci il terzo piano",
						"§6400 Legno", "§615 Ferro");
				break;
			case 3:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lCostruisci Piano", "§8Costruisci il quarto piano",
						"§6450 Legno", "§618 Ferro");
				break;
			case 4:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lCostruisci Piano", "§8Costruisci il quinto piano",
						"§6500 Legno", "§621 Ferro");
				break;
			case 5:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lPiano massimo",
						"§7Hai raggiunto l'ultimo piano costruibile");
				break;
			default:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lErrore",
						"§7Impossibile prendere l'informazioni del rifugio");
				break;
			}

			addInfoItem(Material.EMPTY_MAP, 0, menucasa, "§fInfo sul Rifugio",
					"§8Piano §6" + playerShelter.getBuiltFloors(), "§8Bunker §6" + playerShelter.hasBuiltBunker());

			addItem(Material.CLAY_BRICK, 3, menucasa, "§4§lAggiungi un Player", "§6Aggiungi un player");
			addItem(Material.NETHER_BRICK_ITEM, 4, menucasa, "§4§lRimuovi un Player", "§6Rimuovi un player");
			addItem(Material.WEB, 8, menucasa, "§4§lAbbandona Rifugio", "§6Abbandona il tuo rifugio");

			if (playerShelter.hasBuiltBunker()) {
				addBunkerItem(Material.RAILS, 2, menucasa, "§4§lBunker Costruito", "§7Hai già costruito il bunker");
			} else if (!playerShelter.hasBuiltBunker()) {
				addBunkerItem(Material.RAILS, 2, menucasa, "§4§lCostruisci Bunker", "§6300 Pietra", "§69 Legno");
			}
		}
		return menucasa;
	}

	public static void addItem(Material material, int slot, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void addInfoItem(Material material, int slot, Inventory inventory, String name, String lore,
			String lore1) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemLore.add(lore1);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void addFloorItem(Material material, int slot, Inventory inventory, String name, String lore,
			String lore1, String lore2) {
		ItemStack item = new ItemStack(material);
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

	public static void addFloorItem(Material material, int slot, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void addBunkerItem(Material material, int slot, Inventory inventory, String name, String lore,
			String lore1) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemLore.add(lore1);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void addBunkerItem(Material material, int slot, Inventory inventory, String name, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> itemLore = new ArrayList<String>();
		itemLore.add(lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		inventory.setItem(slot, item);
	}

	public static void openMenuCasa(Player player, Player playercasa) {

		Inventory menucasa = openMenu(player, playercasa);
		player.openInventory(menucasa);

	}
}
