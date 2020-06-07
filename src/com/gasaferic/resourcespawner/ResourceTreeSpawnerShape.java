package com.gasaferic.resourcespawner;

import java.util.ArrayList;

import org.bukkit.Location;

import com.gasaferic.main.Schematic;

public class ResourceTreeSpawnerShape {

	public Location originLocation;

	public ArrayList<Location> logLocations;

	public ResourceTreeSpawnerShape(Location originLocation) {
		this.originLocation = new Location(originLocation.getWorld(), originLocation.getBlockX(),
				originLocation.getBlockY(), originLocation.getBlockZ());
		this.logLocations = new ArrayList<Location>();
		generateShape();
	}

	public void generateShape() {
		logLocations.add(originLocation.clone().add(0, 0, 0));
		logLocations.add(originLocation.clone().add(0, 1, 0));
		logLocations.add(originLocation.clone().add(0, 2, 0));
		logLocations.add(originLocation.clone().add(0, 3, 0));
		logLocations.add(originLocation.clone().add(0, 4, 0));
		logLocations.add(originLocation.clone().add(0, 5, 0));
		logLocations.add(originLocation.clone().add(0, 6, 0));
		
		Schematic.paste("resourcetreeleaves.schematic", originLocation, true);
	}

	public ArrayList<Location> getLogLocations() {
		return this.logLocations;
	}

	public Location getOriginLocation() {
		return this.originLocation;
	}

}
