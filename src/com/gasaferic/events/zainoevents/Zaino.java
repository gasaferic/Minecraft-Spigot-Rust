package com.gasaferic.events.zainoevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.gasaferic.events.teams.TeamMethod;
import com.gasaferic.main.Main;

public class Zaino implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (player.getInventory().getItemInHand().getType() == Material.INK_SACK) {
				if (player.getInventory().getItemInHand().getDurability() == 14) {
					if (!TeamMethod.combatLog.contains(player)) {
						Inventory zaino = player.getEnderChest();
						player.openInventory(zaino);
					} else {
						player.sendMessage(prefix + "§6Non puoi aprire lo zaino mentre sei in §2PVP§6!");
					}
				}
			}
		}
	}
}