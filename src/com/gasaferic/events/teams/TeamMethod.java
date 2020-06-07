package com.gasaferic.events.teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gasaferic.main.Main;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.model.Survivor;
import com.gasaferic.model.Team;

public class TeamMethod {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	// private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();
	// private TeamManager teamManager = Main.getTeamManager();

	public static List<Player> combatLog = new ArrayList<Player>();

	public static int taskID;
	public static int taskID1;

	public static HashMap<Player, Integer> taskIDForPlayer = new HashMap<Player, Integer>();

	public boolean isTeam(Survivor survivorDamager, Survivor survivorDamaged, Main plugin) {

		boolean isTeam = false;

		Team damagerTeam = null;
		if (shelterManager.getShelter(survivorDamager) != null) {
			damagerTeam = shelterManager.getShelter(survivorDamager).getTeam();
		}

		Team damagedTeam = null;
		if (shelterManager.getShelter(survivorDamaged) != null) {
			damagedTeam = shelterManager.getShelter(survivorDamaged).getTeam();
		}

		if (damagerTeam != null && damagerTeam.getTeamMembers().contains(survivorDamaged)
				|| damagedTeam != null && damagedTeam.getTeamMembers().contains(survivorDamager)) {
			isTeam = true;
		}

		/*
		 * for (Survivor survivor : teamManager.getTeams().keySet()) { Team team =
		 * teamManager.getTeams().get(survivor); if
		 * (team.getTeamMembers().contains(damagerTeam) ||
		 * team.getTeamMembers().contains(damagedTeam)) { isTeam = true; } } }
		 */

		return isTeam;
	}

	public void combatLog(Survivor survivorDamager, Survivor survivorDamaged, Main plugin) {

		Player damager = survivorDamager.getPlayer();

		Player damaged = survivorDamaged.getPlayer();

		if (!(combatLog.contains(damaged) && combatLog.contains(damager))) {

			combatLog.add(damaged);
			combatLog.add(damager);

			damager.sendMessage(prefix + plugin.getConfig().getString("inPVPDamager" + survivorDamager.getLanguage().getLang())
					.replace("%damagee", damaged.getName()));
			damaged.sendMessage(prefix + plugin.getConfig().getString("inPVPDamagee" + survivorDamaged.getLanguage().getLang())
					.replace("%damager", damager.getName()));

			taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {

					combatLog.remove(damager);
					combatLog.remove(damaged);

					damager.sendMessage(
							prefix + plugin.getConfig().getString("outPVP" + survivorDamager.getLanguage().getLang()));
					damaged.sendMessage(
							prefix + plugin.getConfig().getString("outPVP" + survivorDamaged.getLanguage().getLang()));

				}
			}, 300L);

			taskIDForPlayer.put(damager, taskID);
			taskIDForPlayer.put(damaged, taskID);

		} else if (combatLog.contains(damaged) && combatLog.contains(damager)) {

			if (taskIDForPlayer.containsKey(damager) && taskIDForPlayer.containsKey(damager)) {

				Bukkit.getScheduler().cancelTask(taskIDForPlayer.get(damager));
				Bukkit.getScheduler().cancelTask(taskIDForPlayer.get(damaged));

				taskIDForPlayer.remove(damager);
				taskIDForPlayer.remove(damaged);
			}

			taskID1 = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {

					combatLog.remove(damager);
					combatLog.remove(damaged);

					damager.sendMessage(
							prefix + plugin.getConfig().getString("outPVP" + survivorDamager.getLanguage().getLang()));
					damaged.sendMessage(
							prefix + plugin.getConfig().getString("outPVP" + survivorDamaged.getLanguage().getLang()));

				}
			}, 300L);

			taskIDForPlayer.put(damager, taskID1);
			taskIDForPlayer.put(damaged, taskID1);

		}
	}

}