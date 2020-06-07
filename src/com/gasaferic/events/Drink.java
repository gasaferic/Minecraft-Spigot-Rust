package com.gasaferic.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import com.gasaferic.main.Main;

public class Drink implements Listener {
	
	@EventHandler
	public void onEntityDamageEvent(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		if (Main.getInstance().getConfig().getBoolean("WaterEXPBar") == true) {
			if (event.getItem().getType() == Material.POTION) {
				if (player.getExp() < 1) {
					player.setExp(1);
				}
			}
		}
	}
}