package com.gasaferic.managers;

import java.util.ArrayList;

import org.bukkit.Location;

import com.gasaferic.main.Main;
import com.gasaferic.model.Shelter;
import com.gasaferic.model.Survivor;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class PlayersRegionManager {

	ArrayList<ProtectedRegion> regions = new ArrayList<ProtectedRegion>();

	WorldGuardPlugin wg = Main.getInstance().getWorldGuard();
	
	public void registerPlayerRegion(Survivor survivor, Location location) {

		RegionManager rm = wg.getRegionManager(location.getWorld());
		
		Location maxPos = location.clone().add(3, 26, -6);
		Location minPos = location.clone().add(-3, -6, 0);

		BlockVector bvMin = convertToSk89qBV(maxPos);
		BlockVector bvMax = convertToSk89qBV(minPos);
		
		ProtectedCuboidRegion pr = new ProtectedCuboidRegion("Casa" + survivor.getName(),
				bvMin.toBlockVector(), bvMax.toBlockVector());
		pr.setFlag(DefaultFlag.PVP, State.ALLOW);
		pr.setFlag(DefaultFlag.INTERACT, State.ALLOW);
		pr.setFlag(DefaultFlag.BUILD, State.ALLOW);
		pr.setFlag(DefaultFlag.BLOCK_PLACE, State.ALLOW);
		pr.setFlag(DefaultFlag.BLOCK_BREAK, State.ALLOW);
		pr.getOwners().addPlayer(survivor.getUniqueId());
		rm.addRegion(pr);
		
		regions.add(pr);
		
	}
	
	public void registerFromShelter(Shelter shelter) {
		
		Survivor survivor = shelter.getShelterOwner();

		Location location = shelter.getConsoleBlock().getLocation().add(0, 1, 3);
		
		Location maxPos = location.clone().add(3, 26, -6);
		Location minPos = location.clone().add(-3, -6, 0);

		BlockVector bvMin = convertToSk89qBV(maxPos);
		BlockVector bvMax = convertToSk89qBV(minPos);
		
		ProtectedCuboidRegion pr = new ProtectedCuboidRegion("Casa" + survivor.getName(),
				bvMin.toBlockVector(), bvMax.toBlockVector());
		pr.setFlag(DefaultFlag.PVP, State.ALLOW);
		pr.setFlag(DefaultFlag.INTERACT, State.ALLOW);
		pr.setFlag(DefaultFlag.BUILD, State.ALLOW);
		pr.setFlag(DefaultFlag.BLOCK_PLACE, State.ALLOW);
		pr.setFlag(DefaultFlag.BLOCK_BREAK, State.ALLOW);
		pr.getOwners().addPlayer(survivor.getUniqueId());
		
		regions.add(pr);
		
	}
	
	public ArrayList<ProtectedRegion> getPlayerRegions() {
		return regions;
	}

	public static BlockVector convertToSk89qBV(Location location) {
		return (new BlockVector(location.getX(), location.getY(), location.getZ()));
	}

	public boolean isRegionProtected(Location location, Main plugin) {
		WorldGuardPlugin worldGuard = plugin.getWorldGuard();
		RegionManager regionManager = worldGuard.getRegionManager(location.getWorld());
		ApplicableRegionSet regions = regionManager.getApplicableRegions(location);
		if (regions.size() == 0) {
			return false;
		} else {
			for (ProtectedRegion region : regions) {
				for (ProtectedRegion protectedregion : Main.getPlayersRegionManager().getPlayerRegions()) {
					if (region.equals(protectedregion)) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
}