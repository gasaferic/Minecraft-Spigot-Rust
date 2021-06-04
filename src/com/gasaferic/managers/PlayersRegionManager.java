package com.gasaferic.managers;

import org.bukkit.Location;

import com.gasaferic.areaprotection.api.AreaProtectionAPI;
import com.gasaferic.areaprotection.enums.AreaFlagTypes;
import com.gasaferic.areaprotection.model.AreaFlag;
import com.gasaferic.areaprotection.model.AreaFlags;
import com.gasaferic.areaprotection.model.Selection;
import com.gasaferic.main.Main;
import com.gasaferic.model.Shelter;
import com.gasaferic.model.Survivor;

public class PlayersRegionManager {
	
	AreaProtectionAPI areaProtectionAPI = Main.getAreaProtectionAPI();

	public void registerPlayerRegion(Survivor survivor, Location location) {

		Selection selection = new Selection();
		selection.setFirstPos(location.clone().add(3, 26, -6).toVector());
		selection.setSecondPos(location.clone().add(-3, -6, 0).toVector());

		AreaFlags areaFlags = new AreaFlags();
		areaFlags.addAreaFlag(new AreaFlag(AreaFlagTypes.BUILD, false));
		areaFlags.addAreaFlag(new AreaFlag(AreaFlagTypes.BREAK, false));
		
		Main.getAreaManager().registerArea(areaProtectionAPI.createNewArea("Casa" + survivor.getName(), survivor.getPlayer(), selection, location, false, areaFlags, true));
		
	}
	
	public void registerFromShelter(Shelter shelter) {
		
		Survivor survivor = shelter.getShelterOwner();

		Location location = shelter.getConsoleBlock().getLocation().add(0, 1, 3);
		
		Selection selection = new Selection();
		selection.setFirstPos(location.clone().add(3, 26, -6).toVector());
		selection.setSecondPos(location.clone().add(-3, -6, 0).toVector());

		AreaFlags areaFlags = new AreaFlags();
		areaFlags.addAreaFlag(new AreaFlag(AreaFlagTypes.BUILD, false));
		areaFlags.addAreaFlag(new AreaFlag(AreaFlagTypes.BREAK, false));

		Main.getAreaManager().registerArea(areaProtectionAPI.createNewArea("Casa" + survivor.getName(), survivor.getPlayer(), selection, location, false, areaFlags, true));

		
	}
	
}