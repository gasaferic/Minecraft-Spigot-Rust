package com.gasaferic.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import com.gasaferic.main.Main;

public class Reboot implements CommandExecutor, Listener {

	private int count = 61;

	public static int countdown_id;

	private static Main plugin = Main.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender.hasPermission("rust.reboot")) {
			if (args.length == 0) {
				sender.sendMessage("§4§lReboot §cUtilizza /reboot <start|stop>");
			} else if (args.length == 1) {
				if (args[0].equals("start")) {
					countdown_id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
						@Override
						public void run() {

							count--;

							if (count == 60) {
								Bukkit.broadcastMessage("§8§l[§4§lReboot§8§l]§4§l Il server si riavvierà in 1 minuto");
							}

							if (count == 30 || count < 11 && count > 0 && count > 1) {
								Bukkit.broadcastMessage(
										"§8§l[§4§lReboot§8§l]§4§l Il server si riavvierà in " + count + " secondi");
							}

							if (count == 1) {
								Bukkit.broadcastMessage(
										"§8§l[§4§lReboot§8§l]§4§l Il server si riavvierà in " + count + " secondo");
							}

							if (count == 0) {
								Bukkit.getScheduler().cancelTask(countdown_id);
								Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
							}
						}
					}, 0L, 20L);
				} else if (args[0].equals("stop")) {
					if (countdown_id != 0) {
						Bukkit.getScheduler().cancelTask(countdown_id);
						sender.sendMessage("§cReboot annullato");
					} else {
						sender.sendMessage("§cNon è in corso un reboot!");
					}
				}
			} else if (args.length > 1) {
				sender.sendMessage("§cTroppi argomenti inseriti(<start|stop>)!");
			}
		} else {
			sender.sendMessage("§cDevi essere un admin per utilizzare questo comando!");
		}
		return false;
	}
}