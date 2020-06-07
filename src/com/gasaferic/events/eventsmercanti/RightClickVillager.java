package com.gasaferic.events.eventsmercanti;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.gasaferic.events.eventsmercanti.mercantearmi.inventories.InventoryMercanteMunizioni;
import com.gasaferic.events.eventsmercanti.mercantearmi.inventories.InventoryMercanteMunizioniEng;
import com.gasaferic.events.eventsmercanti.mercantecibo.inventories.InventoryMercanteCibo;
import com.gasaferic.events.eventsmercanti.mercantecibo.inventories.InventoryMercanteCiboEng;
import com.gasaferic.events.eventsmercanti.mercantesaccheggio.inventories.InventoryMercanteSaccheggio;
import com.gasaferic.events.eventsmercanti.mercantesaccheggio.inventories.InventoryMercanteSaccheggioEng;

public class RightClickVillager implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerRightClick(PlayerInteractEntityEvent event) {

		Player player = (Player) event.getPlayer();
		File f = new File("plugins/MenuCasa/players/", player.getName() + ".yml");
		FileConfiguration useFile = YamlConfiguration.loadConfiguration(f);
		Entity entity = event.getRightClicked();
		if (!(entity instanceof Villager))
			return;
		if (entity.getName().equals("§fMercante Saccheggio")) {
			if (useFile.getString("Lingua").equals("it")) {
				InventoryMercanteSaccheggio.openMenuSaccheggio(player);
				event.setCancelled(true);
			} else if (useFile.getString("Lingua").equals("eng")) {
				InventoryMercanteSaccheggioEng.openMenuSaccheggio(player);
				event.setCancelled(true);
			}
		} else if (entity.getName().equals("§fMercante Munizioni")) {
			if (useFile.getString("Lingua").equals("it")) {
				InventoryMercanteMunizioni.openMenuArmi(player);
				event.setCancelled(true);
			} else if (useFile.getString("Lingua").equals("eng")) {
				InventoryMercanteMunizioniEng.openMenuArmi(player);
				event.setCancelled(true);
			}
		} else if (entity.getName().equals("§fMercante Cibo")) {
			if (useFile.getString("Lingua").equals("it")) {
				InventoryMercanteCibo.openMenuCibo(player);
				event.setCancelled(true);
			} else if (useFile.getString("Lingua").equals("eng")) {
				InventoryMercanteCiboEng.openMenuCibo(player);
				event.setCancelled(true);
			}
		}
	}
}