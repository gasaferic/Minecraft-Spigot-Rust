package com.gasaferic.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.gasaferic.events.teams.TeamMethod;

public class RespawnEventNoBed implements Listener {

	@EventHandler
	public void onPlayerRespawnWater(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if (player.getBedSpawnLocation() != null) {
			event.getPlayer().setExp(1.0F);
		} else if (player.getBedSpawnLocation() == null) {
			event.getPlayer().setExp(1.0F);
			player.getInventory().addItem(new ItemStack(Material.WORKBENCH, 1));
		}
	}

	@EventHandler
	public void onPlayerDeathPVP(PlayerDeathEvent event) {
		Player player = event.getEntity();

		if (TeamMethod.combatLog.contains(player)) {
			if (TeamMethod.taskIDForPlayer.containsKey(player)) {
				Bukkit.getScheduler().cancelTask(TeamMethod.taskIDForPlayer.get(player));
				TeamMethod.combatLog.remove(player);
			}
			if (TeamMethod.taskIDForPlayer.containsKey(player.getKiller())) {
				Bukkit.getScheduler().cancelTask(TeamMethod.taskIDForPlayer.get(player.getKiller()));
				TeamMethod.combatLog.remove(player.getKiller());
			}
		}

		if (RadiazioniEvent.radiazioni.containsKey(player) && player.hasPotionEffect(PotionEffectType.HUNGER)) {
			Bukkit.getScheduler().cancelTask((RadiazioniEvent.radiazioni.get(player)));
			player.removePotionEffect(PotionEffectType.HUNGER);
			RadiazioniEvent.radiazioni.remove(player);
		}

		player.setExp(100);
	}

	@EventHandler
	public void onPlayerDeathWithScope(PlayerDeathEvent event) {
		for (ItemStack i : event.getDrops()) {
			Player player = event.getEntity();
			if (ScopeSniper.elmo.containsKey(player)) {
				event.getDrops().add((ItemStack) ScopeSniper.elmo.get(player));
				ScopeSniper.elmo.remove(player);
				if (i.getType() == Material.PUMPKIN) {
					event.getDrops().remove(i);
				}
			}
		}
	}

	@EventHandler
	public void onFirstJoin(PlayerJoinEvent event) {
		if (!event.getPlayer().hasPlayedBefore()) {
			event.getPlayer().setExp(1.0F);
		}
	}
}