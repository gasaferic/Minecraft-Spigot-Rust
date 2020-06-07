package com.gasaferic.events.menusevents.build;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;
import com.gasaferic.main.Schematic;
import com.gasaferic.model.Floor;
import com.gasaferic.model.Shelter;
import com.gasaferic.model.Survivor;

public class FloorMethod {

	public static void buildFloor(Shelter shelter, Survivor survivor, Floor floor) {

		String prefix = Main.getInstance().getPrefixString("prefix");

		Player player = survivor.getPlayer();

		if (floor != null && floor.getLogAmount() != 0 && floor.getIronAmount() != 0 && floor.getSchematic() != null) {
			if (player.getInventory().contains(Material.LOG, floor.getLogAmount())) {
				if (player.getInventory().contains(Material.IRON_INGOT, floor.getIronAmount())) {
					player.getInventory().removeItem(new ItemStack(Material.LOG, floor.getLogAmount()));
					player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, floor.getIronAmount()));

					Schematic.paste(floor.getSchematic(), shelter.getConsoleBlock().getLocation(), true);

					shelter.setBuiltFloors(shelter.getBuiltFloors() + 1);

					player.closeInventory();

				} else if (!(player.getInventory().contains(Material.IRON_INGOT, floor.getIronAmount()))) {
					player.closeInventory();
					player.sendMessage(prefix + "§6Non hai abbastanza materiali");
				}
			} else if (!(player.getInventory().contains(Material.LOG, floor.getLogAmount()))) {
				player.closeInventory();
				player.sendMessage(prefix + "§6Non hai abbastanza materiali");
			}
		}
	}

	public static void buildFloor(Shelter shelter, Survivor survivor, Floor floor, boolean costs) {

		Player player = survivor.getPlayer();

		if (floor != null && floor.getLogAmount() != 0 && floor.getIronAmount() != 0 && floor.getSchematic() != null) {
			if (costs) {
				buildFloor(shelter, survivor, floor);
			} else {

				Schematic.paste(floor.getSchematic(), shelter.getConsoleBlock().getLocation(), true);

				shelter.setBuiltFloors(shelter.getBuiltFloors() + 1);

				player.closeInventory();

			}
		}
	}

	public static void buildBunker(Shelter shelter, Survivor survivor, Floor floor) {

		String prefix = Main.getInstance().getPrefixString("prefix");

		Player player = survivor.getPlayer();

		if (floor != null && floor.getLogAmount() != 0 && floor.getIronAmount() != 0 && floor.getSchematic() != null) {
			if (player.getInventory().contains(Material.COBBLESTONE, floor.getLogAmount())) {
				if (player.getInventory().contains(Material.LOG, floor.getIronAmount())) {
					player.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, floor.getLogAmount()));
					player.getInventory().removeItem(new ItemStack(Material.LOG, floor.getIronAmount()));

					Schematic.paste(floor.getSchematic(), shelter.getConsoleBlock().getLocation(), false);

					shelter.setHasBuiltBunker(true);

					player.closeInventory();

				} else if (!(player.getInventory().contains(Material.LOG, floor.getIronAmount()))) {
					player.closeInventory();
					player.sendMessage(prefix + "§6Non hai abbastanza materiali");
				}
			} else if (!(player.getInventory().contains(Material.COBBLESTONE, floor.getLogAmount()))) {
				player.closeInventory();
				player.sendMessage(prefix + "§6Non hai abbastanza materiali");
			}
		}
	}

	public static void buildBunker(Shelter shelter, Survivor survivor, Floor floor, boolean costs) {

		Player player = survivor.getPlayer();

		if (floor != null && floor.getLogAmount() != 0 && floor.getIronAmount() != 0 && floor.getSchematic() != null) {
			if (costs) {
				buildBunker(shelter, survivor, floor);
			} else {

				Schematic.paste(floor.getSchematic(), shelter.getConsoleBlock().getLocation(), false);

				shelter.setHasBuiltBunker(true);

				player.closeInventory();
			}
		}
	}

}