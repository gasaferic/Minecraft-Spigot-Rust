package com.gasaferic.events.cureevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gasaferic.main.Main;

public class Frattura implements Listener {

	private Main plugin = Main.getInstance();
	
	@EventHandler
	public void onEntityDamageEvent(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		if (e.getCause() == DamageCause.FALL) {
			if (e.getDamage() >= 5) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 2), false);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						if (player.hasPotionEffect(PotionEffectType.SLOW)) {
						player.removePotionEffect(PotionEffectType.SLOW);
					}
				}}, 1200L);
			}
		}
	}
}