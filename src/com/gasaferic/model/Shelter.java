package com.gasaferic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import com.gasaferic.main.Main;
import com.gasaferic.main.MetadataManager;
import com.gasaferic.managers.SurvivorManager;

public class Shelter {

	private Survivor shelterOwner;
	private Block consoleBlock;
	private int floors = 1;
	private boolean bunker = false;
	private ArrayList<Block> doors = new ArrayList<Block>();
	private ArrayList<Block> trapdoors = new ArrayList<Block>();
	private ArrayList<Block> barriers = new ArrayList<Block>();
	private ArrayList<Block> doorsToRemove = new ArrayList<Block>();
	private ArrayList<Block> trapdoorsToRemove = new ArrayList<Block>();
	private ArrayList<Block> barriersToRemove = new ArrayList<Block>();
	private Team team;
	private MetadataManager metadataManager;
	private SurvivorManager survivorManager;

	public Shelter(Survivor shelterOwner, Block consoleBlock, int floor, boolean bunker,
			HashMap<Location, String> protections, ArrayList<String> teamersUniqueIds) {

		metadataManager = Main.getMetadataManager();
		survivorManager = Main.getSurvivorManager();

		this.shelterOwner = shelterOwner;
		this.consoleBlock = consoleBlock;

		this.floors = floor;
		this.bunker = bunker;

		metadataManager.setBlockOwner(consoleBlock, shelterOwner.getUniqueId());

		initializeProtections(protections);

		ArrayList<Survivor> teamList = getTeam(teamersUniqueIds);

		team = new Team(shelterOwner, shelterOwner.getName(), teamList.size(), teamList);
	}

	public Shelter(Survivor shelterOwner, Block consoleBlock) {
		metadataManager = Main.getMetadataManager();
		survivorManager = Main.getSurvivorManager();

		this.shelterOwner = shelterOwner;
		this.consoleBlock = consoleBlock;

		metadataManager.setBlockOwner(consoleBlock, shelterOwner.getUniqueId());

		initializeProtections(null);

		ArrayList<Survivor> teamList = new ArrayList<Survivor>();

		team = new Team(shelterOwner, shelterOwner.getName(), teamList.size(), teamList);
	}

	public void initializeProtections(HashMap<Location, String> protections) {
		if (protections != null && !protections.isEmpty()) {
			ArrayList<Location> doors = new ArrayList<Location>();
			ArrayList<Location> trapdoors = new ArrayList<Location>();
			ArrayList<Location> barriers = new ArrayList<Location>();
			for (Location prot : protections.keySet()) {
				if (protections.get(prot).equalsIgnoreCase("door")) {
					doors.add(prot);
				}
				if (protections.get(prot).equalsIgnoreCase("trapdoor")) {
					trapdoors.add(prot);
				}
				if (protections.get(prot).equalsIgnoreCase("barrier")) {
					barriers.add(prot);
				}
			}

			initializeDoors(doors);
			initializeTrapDoors(trapdoors);
			initializeDoors(barriers);
		}
	}

	public void initializeDoors(List<Location> doorsList) {
		if (doorsList != null && !doorsList.isEmpty()) {
			for (Location location : doorsList) {
				Block door = location.getBlock();
				doors.add(door);
				metadataManager.setBlockOwner(door, shelterOwner.getUniqueId());
			}
		}
	}

	public void initializeTrapDoors(List<Location> trapdoorsList) {
		if (trapdoorsList != null && !trapdoorsList.isEmpty()) {
			for (Location location : trapdoorsList) {
				Block trapdoor = location.getBlock();
				trapdoors.add(trapdoor);
				metadataManager.setBlockOwner(trapdoor, shelterOwner.getUniqueId());
			}
		}
	}

	public void initializeBarriers(List<Location> barriersList) {
		if (barriersList != null && !barriersList.isEmpty()) {
			for (Location location : barriersList) {
				Block barrier = location.getBlock();
				barriers.add(barrier);
				metadataManager.setBlockOwner(barrier, shelterOwner.getUniqueId());
			}
		}
	}

	public void addDoor(Block door) {
		this.doors.add(door);
		if (!this.doorsToRemove.isEmpty() && this.doorsToRemove.contains(door)) {
			this.doorsToRemove.remove(door);
		}
	}

	public void removeDoor(Block door) {
		if (this.doors.contains(door)) {
			System.out.println("IN DOORS");
			this.doors.remove(door);
			this.doorsToRemove.add(door);
		}
	}

	public void addTrapdoor(Block trapdoor) {
		this.trapdoors.add(trapdoor);
		if (!this.trapdoorsToRemove.isEmpty() && this.trapdoorsToRemove.contains(trapdoor)) {
			this.trapdoorsToRemove.remove(trapdoor);
		}
	}

	public void removeTrapdoor(Block trapdoor) {
		if (this.trapdoors.contains(trapdoor)) {
			this.trapdoors.remove(trapdoor);
			this.trapdoorsToRemove.add(trapdoor);
		}
	}

	public void addBarrier(Block barrier) {
		this.barriers.add(barrier);
		if (!this.barriersToRemove.isEmpty() && this.barriersToRemove.contains(barrier)) {
			this.barriersToRemove.remove(barrier);
		}
	}

	public void removeBarrier(Block barrier) {
		if (this.barriers.contains(barrier)) {
			this.barriers.remove(barrier);
			this.barriersToRemove.add(barrier);
		}
	}

	public ArrayList<Survivor> getTeam(List<String> survivorList) {
		ArrayList<Survivor> teamList = new ArrayList<Survivor>();

		for (String survivorString : survivorList) {
			UUID uuid = UUID.fromString(survivorString);
			teamList.add(survivorManager.getSurvivorByUniqueId(uuid));
		}

		return teamList;
	}

	public Survivor getShelterOwner() {
		return shelterOwner;
	}

	public Block getConsoleBlock() {
		return consoleBlock;
	}

	public int getBuiltFloors() {
		return floors;
	}

	public boolean hasBuiltBunker() {
		return bunker;
	}

	public void setBuiltFloors(int floors) {
		this.floors = floors;
	}

	public void setHasBuiltBunker(boolean bunker) {
		this.bunker = bunker;
	}

	public ArrayList<Block> getDoors() {
		return doors;
	}

	public ArrayList<Block> getTrapdoors() {
		return trapdoors;
	}

	public ArrayList<Block> getBarriers() {
		return barriers;
	}

	public ArrayList<Block> getDoorsToRemove() {
		return doorsToRemove;
	}

	public ArrayList<Block> getTrapdoorsToRemove() {
		return trapdoorsToRemove;
	}

	public ArrayList<Block> getBarriersToRemove() {
		return barriersToRemove;
	}

	public Team getTeam() {
		return team;
	}

	public void initializeShelter() {

		metadataManager.removeBlockOwner(consoleBlock);
		this.consoleBlock = null;

		this.floors = 1;
		this.bunker = false;

		for (Block barrier : barriers) {
			metadataManager.removeBlockOwner(barrier);
			barrier.setType(Material.AIR);
		}
		this.barriers = new ArrayList<Block>();

		for (Block trapdoor : trapdoors) {
			metadataManager.removeBlockOwner(trapdoor);
			trapdoor.setType(Material.AIR);
		}
		this.trapdoors = new ArrayList<Block>();

		for (Block door : doors) {
			metadataManager.removeBlockOwner(door);
			door.setType(Material.AIR);
		}
		this.doors = new ArrayList<Block>();

		this.team = null;
	}

}