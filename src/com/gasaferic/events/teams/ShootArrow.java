package com.gasaferic.events.teams;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ShootArrow implements Listener {

	@EventHandler
	public boolean onPlayerShootArrow(ProjectileLaunchEvent event) {
		Player shooter = (Player) event.getEntity().getShooter();
		if (shooter instanceof Player) {
			if (event.getEntity() instanceof Arrow) {
				if (!shooter.getItemInHand().getItemMeta().hasDisplayName()) {
					event.getEntity().setCustomName(shooter.getName());
				}
			}
		}
		return false;
	}
}