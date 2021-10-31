package com.gasaferic.managers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.gasaferic.model.Survivor;

public class SurvivorManager {

	private ArrayList<Survivor> survivors = new ArrayList<Survivor>();

	public ArrayList<Survivor> getSurvivors() {
		return this.survivors;
	}

	public ArrayList<Survivor> getOnlineSurvivorsList() {

		ArrayList<Survivor> survivorsList = new ArrayList<Survivor>();

		for (Survivor currentSurvivor : survivors) {
			if (currentSurvivor.getPlayer() != null) {
				survivorsList.add(currentSurvivor);
			}
		}

		survivorsList.sort(new Comparator<Survivor>() {
			@Override
			public int compare(Survivor survivor1, Survivor survivor2) {
				return survivor1.getName().compareToIgnoreCase(survivor2.getName());
			}
		});

		return survivorsList;
	}

	public Survivor getSurvivorByPlayer(Player player) {
		Survivor targetSurvivor = null;

		for (Survivor currentSurvivor : survivors) {
			if (currentSurvivor.getPlayer() != null && currentSurvivor.getPlayer().equals(player)) {
				targetSurvivor = currentSurvivor;
				break;
			}
		}

		return targetSurvivor;
	}

	public Survivor getSurvivorByUniqueId(UUID uuid) {
		Survivor targetSurvivor = null;

		for (Survivor currentSurvivor : survivors) {
			if (currentSurvivor.getUniqueId().equals(uuid)) {
				targetSurvivor = currentSurvivor;
				break;
			}
		}

		return targetSurvivor;
	}

	public boolean survivorAlreadyExists(Survivor survivor) {
		boolean alreadyExists = false;
		
		if (getSurvivorByUniqueId(survivor.getUniqueId()) != null) {
			alreadyExists = true;
		}
		
		return alreadyExists;
	}

	public boolean survivorAlreadyExists(UUID uniqueId) {
		boolean alreadyExists = false;
				
		if (getSurvivorByUniqueId(uniqueId) != null) {
			alreadyExists = true;
		}
		
		return alreadyExists;
	}

	public void loadSurvivorsFromArrayList(ArrayList<Survivor> survivors) {
		for (Survivor survivor : survivors) {
			if (!survivorAlreadyExists(survivor)) {
				this.survivors.add(survivor);
			}
		}
	}

	public void registerSurvivor(Survivor survivor) {
		if (!survivorAlreadyExists(survivor)) {
			this.survivors.add(survivor);
		}
	}

	public void unregisterSurvivor(Survivor survivor) {
		if (survivors.contains(survivor)) {
			survivors.remove(survivor);
		}
	}
	
	public void disableSetupMode() {
		for (Survivor survivor : survivors) {
			if (survivor.setupModeEnabled()) {
				survivor.toggleSetupMode();
			}
		}
	}
}
