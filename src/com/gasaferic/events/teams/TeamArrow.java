package com.gasaferic.events.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.gasaferic.areaprotection.managers.AreaManager;
import com.gasaferic.areaprotection.model.Area;
import com.gasaferic.main.Main;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;

public class TeamArrow implements Listener {
	
	private AreaManager areaManager = Main.getAreaManager();

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	private SurvivorManager survivorManager = Main.getSurvivorManager();

	@EventHandler
	public void onEntityDamageTeam(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();
			if (arrow.getCustomName() != null) {

				Player damager = (Player) Bukkit.getPlayerExact(arrow.getName());
				Survivor survivorDamager = survivorManager.getSurvivorByPlayer(damager);

				if (event.getEntity() instanceof Player) {
					if (!event.isCancelled()) {

						Player damaged = (Player) event.getEntity();
						Survivor survivorDamaged = survivorManager.getSurvivorByPlayer(damaged);

						Area area = areaManager.getAreaFromName("Safezone");
						
						if (!(area.inArea(damager.getLocation()))) {
							TeamMethod teammethod = new TeamMethod();
							if (teammethod.isTeam(survivorDamager, survivorDamaged, plugin)) {
								event.setCancelled(true);
								damager.sendMessage(prefix
										+ plugin.getConfig().getString("canthitteam" + survivorDamager.getLanguage().getLang()));
							} else if (!(teammethod.isTeam(survivorDamager, survivorDamaged, plugin))) {
								teammethod.combatLog(survivorDamager, survivorDamaged, plugin);
								event.setCancelled(false);
							}
						} else if (area.inArea(damager.getLocation())) {
							damager.sendMessage(prefix
									+ plugin.getConfig().getString("canthitsafezone" + survivorDamager.getLanguage().getLang()));
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onEntityDamageSelf(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();
			if (arrow.getCustomName() != null) {
				Player damager = (Player) Bukkit.getPlayerExact(arrow.getName());
				if (event.getEntity() instanceof Player) {
					Player damagee = (Player) event.getEntity();
					if (damagee.getName().equalsIgnoreCase(damager.getName())) {
						event.setCancelled(true);
					}
				}
			}
		}
	}

}