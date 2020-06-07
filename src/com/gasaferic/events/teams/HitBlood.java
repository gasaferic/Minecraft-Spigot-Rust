package com.gasaferic.events.teams;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HitBlood implements Listener {

	@EventHandler
	public void onEntityDamageTeam(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player || e.getDamager() instanceof Arrow) {
			if (!e.isCancelled()) {
				if (!e.getEntity().getType().equals(EntityType.VILLAGER)) {
					e.getEntity().getWorld().playEffect(e.getEntity().getLocation().add(0, 0.5, 0), Effect.STEP_SOUND,
							Material.REDSTONE_BLOCK);
				}
			}
		}
	}

}