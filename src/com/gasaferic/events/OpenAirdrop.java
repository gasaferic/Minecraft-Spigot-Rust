package com.gasaferic.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gasaferic.main.Main;
import com.gasaferic.main.RandomLoots;

public class OpenAirdrop implements Listener {

	public Main plugin = Main.getInstance();

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.STAINED_GLASS) {
				if (e.getPlayer().getItemInHand().getType() != Material.STAINED_GLASS) {
					if (e.getClickedBlock().getRelative(BlockFace.DOWN).getType() == Material.BEACON) {
						e.getClickedBlock().setType(Material.AIR);

						RandomLoots loots = new RandomLoots();
						
						Bukkit.getWorld("world").dropItem(e.getClickedBlock().getLocation(), loots.getRandomLoot());
						Bukkit.getWorld("world").dropItem(e.getClickedBlock().getLocation(), loots.getRandomLoot());
						Bukkit.getWorld("world").dropItem(e.getClickedBlock().getLocation(), loots.getRandomLoot());
					}
				}
			}
		}
	}
}