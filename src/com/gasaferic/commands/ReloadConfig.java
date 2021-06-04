package com.gasaferic.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gasaferic.main.Main;

public class ReloadConfig implements CommandExecutor {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		sender.sendMessage(prefix + " §7Ricarico il config...");
		plugin.reloadConfig();
		sender.sendMessage(prefix + " §aRicaricato il config con successo.");

		return false;
	}

}