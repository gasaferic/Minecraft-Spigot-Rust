package com.gasaferic.managers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.gasaferic.main.Main;
import com.gasaferic.model.Survivor;
import com.gasaferic.model.Team;

public class TeamManager {

	private HashMap<Survivor, Team> teams = new HashMap<Survivor, Team>();

	public Team getTeamByPlayer(OfflinePlayer offlinePlayer) {
		return this.teams.get(Main.getSurvivorManager().getSurvivorByPlayer(offlinePlayer.getPlayer()));
	}

	public Team getTeamByPlayer(Player player) {
		return this.teams.get(Main.getSurvivorManager().getSurvivorByPlayer(player));
	}

	public Team getTeamByUUID(UUID uuid) {
		return this.teams.get(Main.getSurvivorManager().getSurvivorByPlayer(Bukkit.getPlayer(uuid)));
	}

	public void registerTeam(Survivor survivor, Team team) {
		teams.put(survivor, team);
	}

	public void unregisterTeam(Survivor survivor) {
		if (teams.containsKey(survivor)) {
			teams.remove(survivor);
		}
	}

	public HashMap<Survivor, Team> getTeams() {
		return this.teams;
	}
	
}