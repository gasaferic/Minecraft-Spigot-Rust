package com.gasaferic.resourcespawner;

import java.util.ArrayList;

import org.bukkit.Location;

public class ResourceSpawnerShape {

	public Location originLocation;

	public ArrayList<Location> insideShape;
	public ArrayList<Location> outsideShape;

	public ResourceSpawnerShape(Location originLocation) {
		this.originLocation = new Location(originLocation.getWorld(), originLocation.getBlockX(),
				originLocation.getBlockY(), originLocation.getBlockZ());
		this.insideShape = new ArrayList<Location>();
		this.outsideShape = new ArrayList<Location>();
		generateShape();
	}

	public void generateShape() {
		insideShape.add(originLocation.clone().add(1, 0, 1));
		insideShape.add(originLocation.clone().add(1, 0, 2));
		insideShape.add(originLocation.clone().add(2, 0, 1));
		insideShape.add(originLocation.clone().add(2, 0, 2));

		outsideShape.add(originLocation.clone().add(0, 0, 1));
		outsideShape.add(originLocation.clone().add(0, 0, 2));
		outsideShape.add(originLocation.clone().add(1, 0, 0));
		outsideShape.add(originLocation.clone().add(2, 0, 0));
		outsideShape.add(originLocation.clone().add(3, 0, 1));
		outsideShape.add(originLocation.clone().add(3, 0, 2));
		outsideShape.add(originLocation.clone().add(1, 0, 3));
		outsideShape.add(originLocation.clone().add(2, 0, 3));

		outsideShape.add(originLocation.clone().add(1, 1, 1));
		outsideShape.add(originLocation.clone().add(1, 1, 2));
		outsideShape.add(originLocation.clone().add(2, 1, 1));
		outsideShape.add(originLocation.clone().add(2, 1, 2));
	}

	public ArrayList<Location> getInsideShape() {
		return this.insideShape;
	}

	public ArrayList<Location> getOutsideShape() {
		return this.outsideShape;
	}

	public Location getOriginLocation() {
		return this.originLocation;
	}

}
