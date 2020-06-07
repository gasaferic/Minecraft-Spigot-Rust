package com.gasaferic.events.buildevents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Sign;

import com.gasaferic.main.Main;

public class ChangeBarrierDrop implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onBuildOutside(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.WALL_SIGN) {
			Sign blocksign = (Sign) event.getBlock().getState().getData();
			Block attached = event.getBlock().getRelative(blocksign.getAttachedFace());
			if (attached.getType().equals(Material.IRON_DOOR_BLOCK)
					|| attached.getType().equals(Material.WOODEN_DOOR)) {
				if (Main.getMetadataManager().getBlockOwner(event.getBlock()).equals(event.getPlayer().getUniqueId())) {
					event.setCancelled(true);
					event.getBlock().setType(Material.AIR);
					Main.getMetadataManager().removeBarriera(event.getBlock(), event.getPlayer());
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ZOMBIE_PIG_HURT, 1, 1);
					Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.RAILS, 1));
				} else if (!(Main.getMetadataManager().getBlockOwner(event.getBlock())
						.equals(event.getPlayer().getUniqueId()))) {
					if (event.getPlayer().getItemInHand().getType() != Material.IRON_AXE) {
						event.setCancelled(true);
						event.getPlayer().sendMessage(prefix + "§6Non puoi rimuovere questa barriera.");
					} else if (event.getPlayer().getItemInHand().getType() == Material.IRON_AXE) {
						event.setCancelled(true);
						event.getBlock().setType(Material.AIR);
						Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(),
								new ItemStack(Material.RAILS, 1));
						Main.getMetadataManager().removeBarriera(event.getBlock(),
								Bukkit.getPlayer(Main.getMetadataManager().getBlockOwner(event.getBlock())));
						event.getPlayer().getInventory().removeItem(event.getPlayer().getItemInHand());
					}
				}
			}
		}
	}

}