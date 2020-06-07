package com.gasaferic.events.cureevents;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Morfina implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		Player player = (Player) event.getPlayer();
		
		if ((event.getPlayer().getInventory().getItemInHand().getType() == Material.BONE)) {
			ItemStack item = new ItemStack(Material.BONE, 1);
				if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
					if (event.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) {
						event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
						player.getInventory().removeItem(item);
					} else if (player.getItemInHand().getType() != Material.PAPER) {
						if (event.getPlayer().getGameMode() != GameMode.SURVIVAL) {
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
}