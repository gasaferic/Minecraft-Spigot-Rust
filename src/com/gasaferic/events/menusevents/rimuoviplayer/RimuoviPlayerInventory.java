package com.gasaferic.events.menusevents.rimuoviplayer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.gasaferic.main.Main;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;
import com.gasaferic.model.Team;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class RimuoviPlayerInventory {

	private static SurvivorManager survivorManager = Main.getSurvivorManager();
	private static ShelterManager shelterManager = Main.getShelterManager();

	public static Inventory openRimuoviPlayer(Player player) {
		Inventory reportinv = Bukkit.createInventory(player, 54, "§7§lRimuovi Player");

		addedList(reportinv, player);

		return reportinv;
	}

	public static Inventory addedList(Inventory reportinv, Player player) {

		Survivor survivor = survivorManager.getSurvivorByPlayer(player);

		Team team = shelterManager.getShelter(survivor).getTeam();

		for (Survivor selectedSurvivor : team.getTeamMembers()) {
			ItemStack selectedSurvivorHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			
			net.minecraft.server.v1_8_R3.ItemStack nmsHead = CraftItemStack.asNMSCopy(selectedSurvivorHead);
			NBTTagCompound headCompound = (nmsHead.hasTag()) ? nmsHead.getTag() : new NBTTagCompound();
			headCompound.setString("survivorUniqueId", selectedSurvivor.getUniqueId().toString());
			nmsHead.setTag(headCompound);
			selectedSurvivorHead = CraftItemStack.asBukkitCopy(nmsHead);
			
			SkullMeta selectedSurvivorHeadMeta = (SkullMeta) selectedSurvivorHead.getItemMeta();
			
			
			selectedSurvivorHeadMeta.setOwner(selectedSurvivor.getName());
			selectedSurvivorHeadMeta.setDisplayName("§f" + selectedSurvivor.getName());
			selectedSurvivorHead.setItemMeta(selectedSurvivorHeadMeta);
			reportinv.addItem(selectedSurvivorHead);

		}
		return reportinv;
	}

	public static void openRimuoviPlayerMenu(Player player) {

		Inventory reportinv = openRimuoviPlayer(player);
		player.openInventory(reportinv);

	}

}
