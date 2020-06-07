package com.gasaferic.main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum LootsPercentuage {
// Well maybe not, because it is a enum. For enums I always make a seperate enum package, but thsatti'sl just my personal preferance well still data
	AIR(Material.AIR, 1, 75.0, false),
	// Saccheggio
	CHIAVE(Material.TRIPWIRE_HOOK, 1, 0.001, false), TNT(Material.TNT, 1, 0.1, false), ASCIA(Material.IRON_AXE, 1, 1, false),
	DINAMITE(Material.BLAZE_ROD, 1, 1, false),
	// Armatura
	ELMO_KEVLAR(Material.DIAMOND_HELMET, 1, 20, false), CORAZZA_KEVLAR(Material.DIAMOND_CHESTPLATE, 1, 10, false),
	GAMBALI_KEVLAR(Material.DIAMOND_LEGGINGS, 1, 15, false), STIVALI_KEVLAR(Material.DIAMOND_BOOTS, 1, 20, false),
	ELMO_FERRO(Material.IRON_HELMET, 1, 45, false), CORAZZA_FERRO(Material.IRON_CHESTPLATE, 1, 40, false),
	GAMBALI_FERRO(Material.IRON_LEGGINGS, 1, 40, false), STIVALI_FERRO(Material.IRON_BOOTS, 1, 45, false),
	ELMO_ANTI_RADIAZIONI(Material.GOLD_HELMET, 1, 35, false),
	CORAZZA_ANTI_RADIAZIONI(Material.GOLD_CHESTPLATE, 1, 25, false),
	GAMBALI_ANTI_RADIAZIONI(Material.GOLD_LEGGINGS, 1, 25, false),
	STIVALI_ANTI_RADIAZIONI(Material.GOLD_BOOTS, 1, 35, false), ELMO_MIMETICA(Material.CHAINMAIL_HELMET, 1, 35, false),
	CORAZZA_MIMETICA(Material.CHAINMAIL_CHESTPLATE, 1, 30, false),
	GAMBALI_MIMETICA(Material.CHAINMAIL_LEGGINGS, 1, 30, false),
	STIVALI_MIMETICA(Material.CHAINMAIL_BOOTS, 1, 35, false), ELMO_PELLE(Material.LEATHER_HELMET, 1, 60, false),
	CORAZZA_PELLE(Material.LEATHER_CHESTPLATE, 1, 55, false), GAMBALI_PELLE(Material.LEATHER_LEGGINGS, 1, 55, false),
	STIVALI_PELLE(Material.LEATHER_BOOTS, 1, 60, false),
	// Armi
	SV98(Material.DIAMOND_SPADE, 1, 30, true), AK47(Material.IRON_SPADE, 1, 55, true),
	M16(Material.STONE_SPADE, 1, 55, true), MP5(Material.STONE_HOE, 1, 60, true),
	SAWEDOFF(Material.GOLD_SPADE, 1, 40, true), REMINGTON(Material.WOOD_SPADE, 1, 40, true),
	DESERTEAGLE(Material.WOOD_HOE, 1, 55, true), TEC9(Material.GOLD_HOE, 1, 60, true),
	PISTOL(Material.IRON_HOE, 1, 60, true), ARCO(Material.BOW, 1, 60, true),
	// Munizioni
	ASSAULTAMMO(Material.PUMPKIN_SEEDS, 45, false, true), SHOTGUNAMMO(Material.MELON_SEEDS, 50, false, true),
	PISTOLAMMO(Material.SEEDS, 55, false, true), FRECCE(Material.ARROW, 55, false, true),
	// Rifugio
	CHEST(Material.CHEST, 1, 65, false), WORKBENCH(Material.WORKBENCH, 1, 70, false),
	FURNACE(Material.FURNACE, 1, 65, false), TORCH(Material.TORCH, 65, false, true),
	IRONDOOR(Material.IRON_DOOR, 1, 55, false), WOODENDOOR(Material.WOODEN_DOOR, 1, 60, false),
	IRONTRAPDOOR(Material.IRON_TRAPDOOR, 1, 60, false), WOODENTRAPDOOR(Material.TRAP_DOOR, 1, 65, false),
	SPIKES(Material.WEB, 1, 65, false),
	// Progetti
	SCHEMATIC_TNT(Material.BOOK, "§fTNT", 1, 5, true, false),
	SCHEMATIC_IRON_AXE(Material.BOOK, "§fAscia in Ferro", 1, 5, true, false),
	SCHEMATIC_DINAMITE(Material.BOOK, "§fDinamite", 1, 10, true, false),
	SCHEMATIC_ELMO_KEVLAR(Material.BOOK, "§fElmo Kevlar", 1, 30, true, false),
	SCHEMATIC_CORAZZA_KEVLAR(Material.BOOK, "§fCorazza Kevlar", 1, 25, true, false),
	SCHEMATIC_GAMBALI_KEVLAR(Material.BOOK, "§fGambali Kevlar", 1, 25, true, false),
	SCHEMATIC_STIVALI_KEVLAR(Material.BOOK, "§fStivali Kevlar", 1, 30, true, false),
	SCHEMATIC_ELMO_FERRO(Material.BOOK, "§fElmo Anti Radiazioni", 1, 35, true, false),
	SCHEMATIC_CORAZZA_FERRO(Material.BOOK, "§fCorazza Anti Radiazioni", 1, 30, true, false),
	SCHEMATIC_GAMBALI_FERRO(Material.BOOK, "§fGambali Anti Radiazioni", 1, 30, true, false),
	SCHEMATIC_STIVALI_FERRO(Material.BOOK, "§fStivali Anti Radiazioni", 1, 35, true, false),
	SCHEMATIC_SV98(Material.BOOK, "§fSV-98", 1, 35, true, false),
	SCHEMATIC_AK47(Material.BOOK, "§fAK-47", 1, 55, true, false),
	SCHEMATIC_M16(Material.BOOK, "§fM16", 1, 55, true, false),
	SCHEMATIC_MP5(Material.BOOK, "§fMP5", 1, 60, true, false),
	SCHEMATIC_SAWEDOFF(Material.BOOK, "§fSawed-Off", 1, 50, true, false),
	SCHEMATIC_REMINGTON(Material.BOOK, "§fRemington", 1, 50, true, false),
	SCHEMATIC_DESERTEAGLE(Material.BOOK, "§fDesert Eagle", 1, 55, true, false),
	SCHEMATIC_TEC9(Material.BOOK, "§fTec-9", 1, 65, true, false),
	SCHEMATIC_PISTOL(Material.BOOK, "§fColt 1911", 1, 65, true, false);

	private Material material;
	private String name;
	private int ammount;
	private double percentuage;
	private boolean schematic;
	private boolean unbreakable;
	private boolean ammo;
	
	public static ProbabilityUntilities prob = new ProbabilityUntilities();

	LootsPercentuage(Material material, String name, int ammount, double percentuage, boolean schematic,
			boolean unbreakable) {
		this.material = material;
		this.name = name;
		this.ammount = ammount;
		this.percentuage = percentuage;
		this.schematic = schematic;
		this.unbreakable = unbreakable;
	}

	LootsPercentuage(Material material, int ammount, double percentuage, boolean unbreakable) {
		this.material = material;
		this.ammount = ammount;
		this.percentuage = percentuage;
		this.unbreakable = unbreakable;
	}

	LootsPercentuage(Material material, double percentuage, boolean unbreakable, boolean ammo) {
		this.material = material;
		this.percentuage = percentuage;
		this.unbreakable = unbreakable;
		this.ammo = ammo;
	}

	public Material getMaterial() {
		return material;
	}

	public String getName() {
		return name;
	}

	public Integer getAmmount() {
		return ammount;
	}

	public double getPercentuage() {
		return percentuage;
	}

	public static ItemStack getRandomLoot() {
		return prob.getRandomElement();
	}

	static void fillLootsList() {
		for (LootsPercentuage loot : values()) {
			Material material = loot.material;
			int ammount = loot.ammount;
			double percentuage = loot.percentuage * 1000;
			boolean schematic = loot.schematic;
			boolean unbreakable = loot.unbreakable;
			if (loot.ammo == false) {
				if (loot.name != null) {
					String name = loot.name;
					addLoot(material, name, ammount, percentuage, schematic, unbreakable, prob);
				} else {
					addLoot(material, ammount, percentuage, unbreakable, prob);
				}
			} else if (loot.ammo == true) {
				addLoot(material, percentuage, unbreakable, prob);
			}
			/*
			 * Bukkit.getConsoleSender().sendMessage("Enum " + loot + ", M " + material +
			 * ", N " + name + "§r, A " + ammount + ", % " + (percentuage * 100) + ", List "
			 * + lootsList);
			 */
		}
	}

	public static void addLoot(Material material, String name, int ammount, double percentuage, boolean schematic,
			boolean unbreakable, ProbabilityUntilities prob) {
		ItemStack item = new ItemStack(material, ammount);
		if (item != null) {
			ItemMeta itemMeta = item.getItemMeta();
			if (itemMeta != null) {
				itemMeta.setDisplayName(name);
				itemMeta.spigot().setUnbreakable(unbreakable);
				itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
				item.setItemMeta(itemMeta);
				prob.addChance(item, (int) percentuage);
			}
		}
	}

	public static void addLoot(Material material, int ammount, double percentuage, boolean unbreakable, ProbabilityUntilities prob) {
		ItemStack item = new ItemStack(material, ammount);
		if (item != null) {
			ItemMeta itemMeta = item.getItemMeta();
			if (itemMeta != null) {
				itemMeta.spigot().setUnbreakable(unbreakable);
				item.setItemMeta(itemMeta);
				prob.addChance(item, (int) percentuage);
			}
		}
	}

	public static void addLoot(Material material, double percentuage, boolean unbreakable, ProbabilityUntilities prob) {

		ItemStack item = new ItemStack(material, 3);
		if (item != null) {
			ItemMeta itemMeta = item.getItemMeta();
			if (itemMeta != null) {
				itemMeta.spigot().setUnbreakable(unbreakable);
				itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
				item.setItemMeta(itemMeta);
				prob.addChance(item, (int) percentuage);
			}
		}
	}

}