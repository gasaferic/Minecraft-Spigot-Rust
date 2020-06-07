package com.gasaferic.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gasaferic.database.MySQL;
import com.gasaferic.main.Main;
import com.gasaferic.model.Shelter;
import com.gasaferic.model.Survivor;

public class ShelterManager {
	
	MySQL mySQL = Main.getMySQL();

	private HashMap<Survivor, Shelter> shelters = new HashMap<Survivor, Shelter>();

	public void registerShelter(Survivor survivor, Shelter shelter) {
		shelters.put(survivor, shelter);
		mySQL.addShelter(shelter);
	}

	public void unregisterShelter(Survivor survivor) {
		if (shelters.containsKey(survivor)) {
			shelters.remove(survivor);
			mySQL.removeShelter(survivor);
		}
	}

	public HashMap<Survivor, Shelter> getShelters() {
		return this.shelters;
	}

	public Shelter getShelter(Survivor survivor) {
		if (shelters.containsKey(survivor)) {
			return shelters.get(survivor);
		}
		return null;
	}

	public void loadFromDatabase(UUID uuid) {
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
		Survivor survivor = Main.getSurvivorManager().getSurvivorByOfflinePlayer(offlinePlayer);
		if (mySQL.hasShelter(survivor)) {
			Shelter shelter = new Shelter(survivor, mySQL.getShelterConsoleBlock(survivor), mySQL.getFloor(survivor), mySQL.hasBunker(survivor), mySQL.getProtections(survivor), mySQL.getTeamers(survivor));
			Main.getPlayersRegionManager().registerFromShelter(shelter);
			registerShelter(survivor, shelter);
		}
	}

	public void exportShelter(Shelter shelter) {
		mySQL.saveToDatabase(shelter);
	}

	public void deleteShelter(Survivor survivor) {
		removeFurnitures(getShelter(survivor));
		mySQL.removeShelter(survivor);
		unregisterShelter(survivor);
	}
	
	public void removeFurnitures(Shelter shelter) {
		
	}

	public void configExport(Shelter shelter, File shelterFile) {

		FileConfiguration shelterConfig = YamlConfiguration.loadConfiguration(shelterFile);

		shelterConfig.set("Shelter.Console",
				shelter.getConsoleBlock() != null ? shelter.getConsoleBlock().getLocation().toString() : null);
		shelterConfig.set("Shelter.Floors", shelter.getBuiltFloors());
		shelterConfig.set("Shelter.Bunker", shelter.hasBuiltBunker());

		ArrayList<String> doors = new ArrayList<String>();
		if (shelter.getDoors() != null && !shelter.getDoors().isEmpty()) {
			for (Block door : shelter.getDoors()) {
				doors.add(door.getLocation().toString());
			}
		}
		shelterConfig.set("Shelter.Doors", doors);

		ArrayList<String> trapdoors = new ArrayList<String>();
		if (shelter.getTrapdoors() != null && !shelter.getTrapdoors().isEmpty()) {
			for (Block trapdoor : shelter.getTrapdoors()) {
				trapdoors.add(trapdoor.getLocation().toString());
			}
		}
		shelterConfig.set("Shelter.Trapdoors", trapdoors);

		ArrayList<String> barriers = new ArrayList<String>();
		if (shelter.getBarriers() != null && !shelter.getBarriers().isEmpty()) {
			for (Block barrier : shelter.getBarriers()) {
				barriers.add(barrier.getLocation().toString());
			}
		}
		shelterConfig.set("Shelter.Barriers", barriers);

		ArrayList<String> teamList = new ArrayList<String>();
		if (shelter.getTeam() != null && !shelter.getTeam().getTeamMembers().isEmpty()) {
			for (Survivor survivor : shelter.getTeam().getTeamMembers()) {
				teamList.add(survivor.getUniqueId().toString());
			}
		}
		shelterConfig.set("Shelter.Team", teamList);

		try {
			shelterConfig.save(shelterFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}