package com.gasaferic.events.zainoevents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.events.teams.TeamMethod;
import com.gasaferic.main.Main;

public class ZainoDropEvent implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onPlayerDeathZaino(PlayerDeathEvent e) {
		Player player = e.getEntity();
		Inventory invplayer = player.getInventory();
		Inventory zaino = player.getEnderChest();
		if (invplayer.containsAtLeast(new ItemStack(Material.INK_SACK, 1, (byte) 14), 1)) {
			for (ItemStack items : zaino.getContents()) {
				if (items != null) {
					Bukkit.getWorld(player.getLocation().getWorld().getName()).dropItem(player.getLocation(), items);
					player.getEnderChest().remove(items);
				}
			}
		}
	}

	@EventHandler
	public void onZainoDrop(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		ItemStack zaino = new ItemStack(Material.INK_SACK, 1, (byte) 14);
		if (e.getItemDrop().getItemStack().equals(zaino)) {
			if (TeamMethod.combatLog.contains(player)) {
				e.setCancelled(true);
				player.sendMessage(prefix + "§6Non puoi droppare lo zaino mentre sei in §2PVP§6!");
			} else {
				e.setCancelled(false);
			}
		}
	}
}