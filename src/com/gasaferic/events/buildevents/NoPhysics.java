package com.gasaferic.events.buildevents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Sign;

import com.gasaferic.main.Main;

public class NoPhysics implements Listener {

	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent event) {
		if (event.getBlock().getType().equals(Material.LADDER)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBarrierPhysics(BlockPhysicsEvent event) {
		Block block = event.getBlock();
		Location loc = block.getLocation();
		if (loc.getBlock().getType() == Material.WALL_SIGN) {
			Sign blocksign = (Sign) loc.getBlock().getState().getData();
			Block attached = block.getRelative(blocksign.getAttachedFace());
			if (attached.getType() != Material.AIR && attached.getType() == Material.IRON_DOOR_BLOCK
					|| attached.getType() == Material.WOODEN_DOOR || attached.getType() == Material.STAINED_CLAY) {
				event.setCancelled(false);
			} else if (attached.getType() == Material.AIR && attached.getType() != Material.IRON_DOOR_BLOCK
					|| attached.getType() != Material.WOODEN_DOOR) {
				event.setCancelled(true);
				Main.getMetadataManager().removeBarriera(event.getBlock(),
						Bukkit.getPlayer(Main.getMetadataManager().getBlockOwner(event.getBlock())));
				event.getBlock().setType(Material.AIR);
				Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.RAILS, 1));
			}
		}
	}

	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent e) {
		e.setCancelled(true);
	}
}