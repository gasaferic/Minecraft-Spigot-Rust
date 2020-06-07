package com.gasaferic.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gasaferic.main.Main;

public class AddRadzone implements CommandExecutor {

	private static Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefixStaff");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {

		Player player = (Player) sender;
		if (player.hasPermission("rustadmin.addradzone")) {
			if (args.length == 0) {
				player.sendMessage(prefix + "§6Utilizza /addradzone <nome>");
			} else if (args.length == 1) {
				List<String> listaRadzones = plugin.getConfig().getStringList("RegionsRad");
				if (listaRadzones != null) {
					if (!listaRadzones.contains(args[0])) {
						plugin.getConfig().set("RegionsRad",
								plugin.getConfig().getStringList("RegionsRad").add(args[0]));
						plugin.saveConfig();
						plugin.reloadConfig();
						player.sendMessage(prefix + "§6Radzone aggiunta al config.");
					} else {
						player.sendMessage(prefix + "§6Radzone già presente nel config!");
					}
				}
			}
		} else if (!(player.hasPermission("rustadmin.addradzone"))) {
			player.sendMessage(prefix + "§cNon puoi utilizzare questo comando!");
		}
		return false;
	}
	
}