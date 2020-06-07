package com.gasaferic.events.teams;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gasaferic.main.Main;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;
import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;

public class TeamShoot implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	private SurvivorManager survivorManager = Main.getSurvivorManager();

	@EventHandler
	public void onEntityDamageTeam(WeaponDamageEntityEvent event) {

		Player damager = (Player) event.getPlayer();
		Survivor survivorDamager = survivorManager.getSurvivorByPlayer(damager);

		if (event.getVictim() instanceof Player) {

			Player damaged = (Player) event.getVictim();
			Survivor survivorDamaged = survivorManager.getSurvivorByPlayer(damaged);

			if (!(TeamArrow.isRegionProtected(damager.getLocation(), plugin))) {
				if (!event.isCancelled()) {
					TeamMethod teammethod = new TeamMethod();
					if (teammethod.isTeam(survivorDamager, survivorDamaged, plugin)) {
						event.setCancelled(true);
						damager.sendMessage(
								prefix + plugin.getConfig().getString("cantshootteam" + survivorDamager.getLanguage().getLang()));
					} else if (!(teammethod.isTeam(survivorDamager, survivorDamaged, plugin))) {
						teammethod.combatLog(survivorDamager, survivorDamaged, plugin);
						event.setCancelled(false);
					}
				}
			} else if (TeamArrow.isRegionProtected(damager.getLocation(), plugin)) {
				damager.sendMessage(
						prefix + plugin.getConfig().getString("canthitsafezone" + survivorDamager.getLanguage().getLang()));
				event.setCancelled(true);
			}
		}
	}
}