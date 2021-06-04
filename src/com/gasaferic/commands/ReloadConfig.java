package com.gasaferic.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.LootsPercentuage;
import com.gasaferic.main.Main;

public class ReloadConfig implements CommandExecutor {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		int keyFoundCount = 0;

		ItemStack currentRandItem = null;
		for (int i = 0; i < 50000; i++) {
			currentRandItem = LootsPercentuage.getRandomLoot();
			Bukkit.getConsoleSender().sendMessage(
					prefix + "§9FORSE POTREBBE ESSERE UNA CHIAVE IL SUO MATERIALE E' §a" + currentRandItem.getType());
			if (currentRandItem.getType().equals(Material.TRIPWIRE_HOOK)) {
				keyFoundCount++;
			}
		}

		Bukkit.getConsoleSender()
				.sendMessage(prefix + "UAGLIONE SONO USCITE " + keyFoundCount + " CHIAVI DA 5000 ITEM RANDOM");

		return false;
	}

}