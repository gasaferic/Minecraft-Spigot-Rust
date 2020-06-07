package com.gasaferic.events.menusevents.aggiungiplayer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum PageItemType {

	PREV_PAGE(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "§f§lPagina precedente", 45),
	PAGE_INFO(new ItemStack(Material.PAPER, 1, (short) 0), "§f§lPagina ", 49),
	NEXT_PAGE(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "§f§lPagina successiva", 53);

	private ItemStack pageItem;
	private String pageItemName;
	private int itemSlot;

	PageItemType(ItemStack pageItem, String pageItemName, int itemSlot) {
		this.pageItem = pageItem;
		this.pageItemName = pageItemName;
		this.itemSlot = itemSlot;
	}

	public ItemStack getPageItem() {
		return pageItem;
	}

	public String getPageItemName() {
		return pageItemName;
	}

	public int getItemSlot() {
		return itemSlot;
	}

}
