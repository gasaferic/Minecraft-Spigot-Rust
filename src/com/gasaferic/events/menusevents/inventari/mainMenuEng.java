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

public class mainMenuEng {

	public static SurvivorManager survivorManager = Main.getSurvivorManager();
	public static ShelterManager shelterManager = Main.getShelterManager();

	public static Inventory openMenu(Player player, Player playercasa) {
		
		String title = "Shelter";
		
		StringBuilder builder = new StringBuilder();

		for (char c : title.toCharArray()) {
			builder.append(ChatColor.COLOR_CHAR).append(c);
		}

		String hiddenTitle = builder.toString();

		Inventory menucasa = Bukkit.createInventory(player, 9, "§r" + player.getName() + " " + hiddenTitle);

		Survivor survivor = survivorManager.getSurvivorByPlayer(playercasa);
		Shelter playerShelter = shelterManager.getShelter(survivor);

		if (playerShelter != null) {

			switch (playerShelter.getBuiltFloors()) {
			case 1:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lBuild Floor", "§8Build the second floor",
						"§6350 Wood", "§612 Iron");
			case 2:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lBuild Floor", "§8Build the third floor",
						"§6400 Wood", "§615 Iron");
			case 3:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lBuild Floor", "§8Build the fourth floor",
						"§6450 Wood", "§618 Iron");
			case 4:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lBuild Floor", "§8Build the fifth floor",
						"§6500 Wood", "§621 Iron");
			case 5:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lMax Floor",
						"§7You reached the max floor");
			default:
				addFloorItem(Material.INK_SACK, 1, menucasa, "§4§lError",
						"§7Error retrieving shelter info's");
			}

			addInfoItem(Material.EMPTY_MAP, 0, menucasa, "§fShelter Info",
					"§8Floor §6" + playerShelter.getBuiltFloors(), "§8Bunker §6" + playerShelter.hasBuiltBunker());

			addItem(Material.CLAY_BRICK, 3, menucasa, "§4§lAdd a Player", "§6Add a Player");
			addItem(Material.NETHER_BRICK_ITEM, 4, menucasa, "§4§lRemove a Player", "§6Remove a Player");
			addItem(Material.WEB, 8, menucasa, "§4§lLeave your Shelter", "§6Leave your Shelter");

			if (playerShelter.hasBuiltBunker()) {
				addBunkerItem(Material.RAILS, 2, menucasa, "§4§lBunker Built", "§7You built the bunker");
			} else if (!playerShelter.hasBuiltBunker()) {
				addBunkerItem(Material.RAILS, 2, menucasa, "§4§lBuild Bunker", "§6300 Stone", "§69 Wood");
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

	public static void addInfoItem(Material material, int slot, Inventory inventory, String name, String lore, String lore1) {
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

	public static void addFloorItem(Material material, int slot, Inventory inventory, String name, String lore, String lore1,
			String lore2) {
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

	public static void addBunkerItem(Material material, int slot, Inventory inventory, String name, String lore, String lore1) {
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
