package com.gasaferic.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FixAnvil implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			if (e.getClickedBlock().getType() != Material.ANVIL)
				return;
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				e.setCancelled(true);
				Player player = e.getPlayer();
				if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
					if (player.getItemInHand().getDurability() != (short) 0) {
						player.getItemInHand().setDurability((short) 0);
						player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
					}
				}
			}
		}
	}
}