package com.gasaferic.mobs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class VillagerDamageEvent implements Listener {

	@EventHandler
	public void onVillagerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Villager) {
			Entity villager = e.getEntity();
			if (villager.getCustomName().equalsIgnoreCase("§fMercante Saccheggio")
					|| villager.getCustomName().equalsIgnoreCase("§fMercante Cibo")
					|| villager.getCustomName().equalsIgnoreCase("§fMercante Munizioni")) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onVillagerDamagedByEntity(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Villager && e.getDamager() instanceof Player) {
			Entity villager = e.getEntity();
			if (villager.getCustomName().equalsIgnoreCase("§fMercante Saccheggio")
					|| villager.getCustomName().equalsIgnoreCase("§fMercante Cibo")
					|| villager.getCustomName().equalsIgnoreCase("§fMercante Munizioni")) {
				e.setCancelled(true);
			}
		}
	}

}