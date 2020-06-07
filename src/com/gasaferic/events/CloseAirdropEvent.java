package com.gasaferic.events;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CloseAirdropEvent implements Listener {

	@EventHandler
	public void onCloseInv(InventoryCloseEvent event) {
		if (event.getInventory().getHolder() instanceof Chest) {
			Chest c = (Chest) event.getInventory().getHolder();
			if (c.getInventory().getName().equals("Airdrop")) {
				c.getBlock().setType(Material.AIR);
			}
		}
	}
}