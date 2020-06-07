package com.gasaferic.events.teams;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.gasaferic.main.Main;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class TeamArrow implements Listener {

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

						if (!(isRegionProtected(damager.getLocation(), plugin))) {
							TeamMethod teammethod = new TeamMethod();
							if (teammethod.isTeam(survivorDamager, survivorDamaged, plugin)) {
								event.setCancelled(true);
								damager.sendMessage(prefix
										+ plugin.getConfig().getString("canthitteam" + survivorDamager.getLanguage().getLang()));
							} else if (!(teammethod.isTeam(survivorDamager, survivorDamaged, plugin))) {
								teammethod.combatLog(survivorDamager, survivorDamaged, plugin);
								event.setCancelled(false);
							}
						} else if (isRegionProtected(damager.getLocation(), plugin)) {
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

	public static BlockVector convertToSk89qBV(Location location) {
		return (new BlockVector(location.getX(), location.getY(), location.getZ()));
	}

	public static boolean isRegionProtected(Location location, Main plugin) {
		WorldGuardPlugin worldGuard = plugin.getWorldGuard();
		RegionManager regionManager = worldGuard.getRegionManager(location.getWorld());
		ApplicableRegionSet regions = regionManager.getApplicableRegions(location);
		if (regions.size() == 0) {
			return false;
		} else {
			for (ProtectedRegion region : regions) {
				if (region.getId().equalsIgnoreCase("Safezone")) {
					return true;
				}
			}
		}
		return false;
	}
}