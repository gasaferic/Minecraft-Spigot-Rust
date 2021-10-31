package com.gasaferic.events.menusevents.aggiungiplayer;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.gasaferic.main.Main;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;
import com.gasaferic.model.Team;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class AggiungiPlayerInventory {

	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();

	public int neededPages = 0;
	
	private ArrayList<Inventory> pageInventories;

	public void openAggiungiPlayer(Player player, int page) {
		
		pageInventories = new ArrayList<Inventory>();

		ArrayList<Survivor> survivorsList = survivorManager.getOnlineSurvivorsList();

		neededPages = (survivorsList.size() / 45) + (((survivorsList.size() % 45) + 45 - 1) / 45 * 45) / 45;

		Survivor survivor = survivorManager.getSurvivorByPlayer(player);

		Team team = shelterManager.getShelter(survivor).getTeam();

		if (survivorsList.size() <= 54) {
			Inventory pageInv = Bukkit.createInventory(player, 54, "§9§lAggiungi Sopravvissuto");

			pageInventories.add(pageInv);

			int size = survivorsList.size();
			for (int i = 0; i < size; i++) {
				Survivor currentSurvivor = survivorsList.get(i);
				if (currentSurvivor.getPlayer() != null) {
					if (!(team.getTeamMembers().contains(currentSurvivor))) {
						if (currentSurvivor.getPlayer() != player) {
							addPlayerItem(currentSurvivor, pageInventories.get(0), 1);
						} else if (currentSurvivor.getPlayer() == player) {
							addPlayerItem(currentSurvivor, pageInventories.get(0), 0);
						}
					} else if (team.getTeamMembers().contains(currentSurvivor)) {
						addPlayerItem(currentSurvivor, pageInventories.get(0), 0);
					}
				}
			}
		} else if (survivorsList.size() >= 55) {
			for (int pageAmount = 0; pageAmount < neededPages; pageAmount++) {
				Inventory pageInv = Bukkit.createInventory(player, 54, "§9§lAggiungi Sopravvissuto");
				int survivorLimit = (45 * pageAmount) + 46;

				Survivor currentSurvivor = null;

				for (int survivorIndex = 45 * pageAmount; survivorIndex < survivorLimit; survivorIndex++) {

					if (survivorIndex <= (survivorsList.size() - 1)) {
						currentSurvivor = survivorsList.get(survivorIndex);

						if (!(team.getTeamMembers().contains(currentSurvivor))) {
							if (currentSurvivor != survivor) {
								addPlayerItem(currentSurvivor, pageInv, 1);
							} else if (currentSurvivor == survivor) {
								addPlayerItem(currentSurvivor, pageInv, 0);
							}
						} else if (team.getTeamMembers().contains(currentSurvivor)) {
							addPlayerItem(currentSurvivor, pageInv, 0);
						}
					} else {
						break;
					}
				}

				addPageSwitchItem(PageItemType.PREV_PAGE, pageInv);
				addPageInfoItem(PageItemType.PAGE_INFO, pageInv, pageAmount + 1);
				addPageSwitchItem(PageItemType.NEXT_PAGE, pageInv);

				pageInventories.add(pageInv);
			}
		}

		player.openInventory(pageInventories.get(page - 1));
	}

	public void addPlayerItem(Survivor survivor, Inventory inv, int amount) {
		String playerName = survivor.getPlayer().getName();
		ItemStack skullItem = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
		
		net.minecraft.server.v1_8_R3.ItemStack nmsHead = CraftItemStack.asNMSCopy(skullItem);
		NBTTagCompound headCompound = (nmsHead.hasTag()) ? nmsHead.getTag() : new NBTTagCompound();
		headCompound.setString("survivorUniqueId", survivor.getUniqueId().toString());
		nmsHead.setTag(headCompound);
		skullItem = CraftItemStack.asBukkitCopy(nmsHead);
		
		SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
		skullMeta.setDisplayName("§f" + playerName);
		skullMeta.setOwner(playerName);
		skullItem.setItemMeta(skullMeta);
		inv.addItem(skullItem);
	}

	public void addPageInfoItem(PageItemType pageItemType, Inventory inventory, int page) {
		ItemStack pageItem = pageItemType.getPageItem();
		ItemMeta pageItemMeta = pageItem.getItemMeta();
		pageItemMeta.setDisplayName(pageItemType.getPageItemName() + page + "/" + neededPages);
		pageItem.setItemMeta(pageItemMeta);
		inventory.setItem(pageItemType.getItemSlot(), pageItem);
	}

	public void addPageSwitchItem(PageItemType pageItemType, Inventory inventory) {
		ItemStack pageItem = pageItemType.getPageItem();
		ItemMeta pageItemMeta = pageItem.getItemMeta();
		pageItemMeta.setDisplayName(pageItemType.getPageItemName());
		pageItem.setItemMeta(pageItemMeta);
		inventory.setItem(pageItemType.getItemSlot(), pageItem);
	}

}