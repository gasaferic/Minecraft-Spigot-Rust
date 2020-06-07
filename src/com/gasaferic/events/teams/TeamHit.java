package com.gasaferic.events.teams;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.gasaferic.main.Main;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;

public class TeamHit implements Listener {

	private Main plugin = Main.getInstance();
	
	private String prefix = plugin.getPrefixString("prefix");

	private SurvivorManager survivorManager = Main.getSurvivorManager();

	@EventHandler
	public void onEntityDamageTeam(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			
			Player damager = (Player) event.getDamager();
			Survivor survivorDamager = survivorManager.getSurvivorByPlayer(damager);
			
			if (event.getEntity() instanceof Player) {
				if (!event.isCancelled()) {

					Player damaged = (Player) event.getEntity();
					Survivor survivorDamaged = survivorManager.getSurvivorByPlayer(damaged);

					if (!(TeamArrow.isRegionProtected(damager.getLocation(), plugin))) {
						TeamMethod teammethod = new TeamMethod();
						if (teammethod.isTeam(survivorDamager, survivorDamaged, plugin)) {
							event.setCancelled(true);
							damager.sendMessage(prefix + plugin.getConfig().getString("canthitteam" + survivorDamager.getLanguage().getLang()));
						} else if (!(teammethod.isTeam(survivorDamager, survivorDamaged, plugin))) {
							teammethod.combatLog(survivorDamager, survivorDamaged, plugin);
							event.setCancelled(false);
						}
					} else if (TeamArrow.isRegionProtected(damager.getLocation(), plugin)) {
						damager.sendMessage(prefix + plugin.getConfig().getString("canthitsafezone" + survivorDamager.getLanguage().getLang()));
						event.setCancelled(true);
					}
				}
			}
		}
	}

}