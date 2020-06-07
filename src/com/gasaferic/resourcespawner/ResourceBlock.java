package com.gasaferic.resourcespawner;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ResourceBlock {

	private Location blockLocation;
	private Material type;
	private Material currentType;
	private ArrayList<ItemStack> dropItems;

	public ResourceBlock(Location blockLocation, Material type) {
		this.blockLocation = blockLocation;
		this.type = type;
		this.currentType = type;
		this.dropItems = new ArrayList<ItemStack>();
		dropItems.add(new ItemStack(type, 1));
	}

	public ResourceBlock(Location blockLocation, Material type, ItemStack... dropItem) {
		this.blockLocation = blockLocation;
		this.type = type;
		this.currentType = type;
		this.dropItems = new ArrayList<ItemStack>();
		for (ItemStack selectedDropItem : dropItem) {
			dropItems.add(selectedDropItem);
		}
	}

	public Location getBlockLocation() {
		return blockLocation;
	}

	public void setBlockLocation(Location blockLocation) {
		this.blockLocation = blockLocation;
	}

	public Material getType() {
		return type;
	}

	public Material getCurrentType() {
		return currentType;
	}

	public void setCurrentType(Material type) {
		this.currentType = type;
		blockLocation.getBlock().setType(currentType);
	}
	
	public void addDropItem(ItemStack dropItem) {
		this.dropItems.add(dropItem);
	}

	public void dropItems() {
		for (ItemStack dropItem : dropItems) {
			blockLocation.getWorld().dropItem(getBlockLocation(), dropItem);
		}
	}

}
