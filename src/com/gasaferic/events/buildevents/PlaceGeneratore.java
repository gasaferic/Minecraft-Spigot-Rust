package com.gasaferic.events.buildevents;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gasaferic.main.Main;
import com.gasaferic.model.Survivor;
import com.gasaferic.resourcespawner.ResourceSpawner;
import com.gasaferic.resourcespawner.ResourceTreeSpawner;

public class PlaceGeneratore implements Listener {

	private static Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefixStaff");

	@EventHandler
	public void onResSpawnerPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);

		Block block = event.getBlock();

		if (survivor.setupModeEnabled()) {
			if (block.getType().equals(Material.DRAGON_EGG)) {
				event.setCancelled(true);
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("§4§lGeneratore di Minerali")) {
					ResourceSpawner resourceSpawner = new ResourceSpawner(block.getLocation());
					Main.registerEvent(resourceSpawner);
					Main.getMySQL().saveResSpawner(resourceSpawner);
					player.sendMessage(prefix + "§6Generatore di minerali piazzato.");
				} else if (player.getItemInHand().getItemMeta().getDisplayName().equals("§c§lAlbero per Risorse")) {
					ResourceTreeSpawner resourceTreeSpawner = new ResourceTreeSpawner(block.getLocation());
					Main.registerEvent(resourceTreeSpawner);
					Main.getMySQL().saveResTreeSpawner(resourceTreeSpawner);
					player.sendMessage(prefix + "§6Albero per risorse piazzato.");
				}
			}
		}
	}

}