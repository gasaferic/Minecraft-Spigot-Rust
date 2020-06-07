package com.gasaferic.events.buildevents;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gasaferic.database.MySQL;
import com.gasaferic.main.Main;
import com.gasaferic.model.Survivor;

public class PlaceMobSpawnerEvent implements Listener {
	
	MySQL mySQL = Main.getMySQL();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlaceSpawner(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);

		Block block = event.getBlock();

		if (survivor.setupModeEnabled()) {
			if (block.getType().equals(Material.MOSSY_COBBLESTONE)) {
				mySQL.saveMobSpawner(block.getLocation(), "radiozombie");
				player.sendMessage(Main.getInstance().getPrefixString("prefixStaff") + "Hai piazzato uno spawner di ZOMBIE RADIOATTIVI");
			} else if (block.getType().equals(Material.IRON_BLOCK)) {
				mySQL.saveMobSpawner(block.getLocation(), "mutant");
				player.sendMessage(Main.getInstance().getPrefixString("prefixStaff") + "Hai piazzato uno spawner di MUTANTI");
			} else if (block.getType().equals(Material.HAY_BLOCK)) {
				mySQL.saveMobSpawner(block.getLocation(), "bear");
				player.sendMessage(Main.getInstance().getPrefixString("prefixStaff") + "Hai piazzato uno spawner di ORSI");
			} else if (block.getType().equals(Material.WOOL)) {
				if (block.getData() == 6) {
					mySQL.saveMobSpawner(block.getLocation(), "hog");
				}
				player.sendMessage(Main.getInstance().getPrefixString("prefixStaff") + "Hai piazzato uno spawner di CINGHIALI");
			}
			event.setCancelled(true);
		}

	}

}
