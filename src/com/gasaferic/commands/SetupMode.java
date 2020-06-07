package com.gasaferic.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.gasaferic.main.Main;
import com.gasaferic.model.Survivor;

public class SetupMode implements CommandExecutor, Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefixStaff");

	public SetupMode() {
		Main.registerEvent(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;

		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);

		boolean setupModeEnabled = survivor.setupModeEnabled();
		String message = "§6Modalità Setup " + (!setupModeEnabled ? "abilitata" : "disabilitata");

		if (player.hasPermission("rust.setupmode")) {
			survivor.toggleSetupMode();
			player.sendMessage(prefix + message);
		}

		return false;
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent event) {

		if (event.getInventory() == null || event.getCurrentItem() == null) {
			return;
		}

		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer((Player) event.getWhoClicked());

		if (survivor.setupModeEnabled()) {
			survivor.getPlayer().playSound(survivor.getPlayer().getLocation(), Sound.NOTE_BASS, 5, 1);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {

		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(event.getPlayer());

		if (survivor.setupModeEnabled()) {
			survivor.getPlayer().playSound(survivor.getPlayer().getLocation(), Sound.NOTE_BASS, 5, 1);
			event.setCancelled(true);
		}
	}

}