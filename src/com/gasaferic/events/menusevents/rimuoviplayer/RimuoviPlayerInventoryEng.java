package com.gasaferic.events.menusevents.rimuoviplayer;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.gasaferic.main.Main;

public class RimuoviPlayerInventoryEng {

	public static Inventory openRimuoviPlayer(Player player) {
		Inventory reportinv = Bukkit.createInventory(player, 54, "§7§lRemove Player");

		addedList(reportinv, player);

		return reportinv;
	}

	public static Inventory addedList(Inventory reportinv, Player player) {

		File f = new File(Main.getInstance().getDataFolder() + "/players/", player.getUniqueId() + ".yml");
		FileConfiguration useFile = YamlConfiguration.loadConfiguration(f);
		for (String string : useFile.getStringList("Team" + player.getName())) {
			ItemStack reportedPlayer2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta reportedPlayer2MetaSkin = (SkullMeta) reportedPlayer2.getItemMeta();
			reportedPlayer2MetaSkin.setOwner(string);
			reportedPlayer2MetaSkin.setDisplayName("§f" + string);
			reportedPlayer2.setItemMeta(reportedPlayer2MetaSkin);
			reportinv.addItem(reportedPlayer2);

		}
		return reportinv;
	}

	public static void openRimuoviPlayerMenu(Player player) {

		Inventory reportinv = openRimuoviPlayer(player);
		player.openInventory(reportinv);

	}

}
