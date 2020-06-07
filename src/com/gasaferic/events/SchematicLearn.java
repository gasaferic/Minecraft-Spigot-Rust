package com.gasaferic.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;
import com.gasaferic.model.Survivor;

public class SchematicLearn implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onPlayerRightClickSchematic(PlayerInteractEvent event) {
		if (event.getPlayer().getItemInHand() != null) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (!(event.getPlayer().getItemInHand().hasItemMeta()))
					return;
				if (!(event.getPlayer().getItemInHand().getItemMeta().hasDisplayName()))
					return;
				Player player = event.getPlayer();

				Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);

				ItemStack iteminmano = player.getItemInHand();

				if (iteminmano.getType() == Material.BOOK) {
					if (iteminmano.getItemMeta().getDisplayName() != null) {
						if (iteminmano.getAmount() > 1) {
							String name = iteminmano.getItemMeta().getDisplayName().substring(2).replace("-", "")
									.replace(" ", "_");
							if (!(player.hasPermission("rustschematic." + name.toLowerCase()))) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										"pex user " + player.getName() + " add rustschematic." + name.toLowerCase());
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(prefix + plugin.getConfig()
										.getString("schematiclearn" + survivor.getLanguage().getLang())
										.replace("%Schematic", iteminmano.getItemMeta().getDisplayName().substring(2)));
								player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
								event.setCancelled(true);
							} else if (player.hasPermission("rustschematic." + name.toLowerCase())) {
								player.sendMessage(prefix + plugin.getConfig()
										.getString("schematicalredylearn" + survivor.getLanguage().getLang())
										.replace("%Schematic", iteminmano.getItemMeta().getDisplayName().substring(2)));
							} else if (player.getItemInHand().getType() != Material.BOOK) {
								event.setCancelled(false);
							}
						} else if (iteminmano.getAmount() == 1) {
							String name = iteminmano.getItemMeta().getDisplayName().substring(2).replace("-", "")
									.replace(" ", "_");
							if (!(player.hasPermission("rustschematic." + name.toLowerCase()))) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										"pex user " + player.getName() + " add rustschematic." + name.toLowerCase());
								player.getInventory().remove(iteminmano);
								player.sendMessage(prefix + plugin.getConfig()
										.getString("schematiclearn" + survivor.getLanguage().getLang())
										.replace("%Schematic", iteminmano.getItemMeta().getDisplayName().substring(2)));
								player.getWorld().playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
								event.setCancelled(true);
							} else if (player.hasPermission("rustschematic." + name.toLowerCase())) {
								player.sendMessage(prefix + plugin.getConfig()
										.getString("schematicalredylearn" + survivor.getLanguage().getLang())
										.replace("%Schematic", iteminmano.getItemMeta().getDisplayName().substring(2)));
							} else if (player.getItemInHand().getType() != Material.BOOK) {
								event.setCancelled(false);
							}
						}
					}
				}
			}
		}
	}
}