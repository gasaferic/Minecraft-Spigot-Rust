package com.gasaferic.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gasaferic.main.Main;

public class OpenChestEvent implements Listener {
	
	private Main plugin = Main.getInstance();
	
	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onPlayerRightClickChestCheck(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getClickedBlock() != null) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (event.getClickedBlock().getType() == Material.CHEST) {
					if (isEnoughNearToInteract(player, event.getClickedBlock())) {
						event.setCancelled(false);
					} else {
						player.sendMessage(prefix + "§6Devi avvicinarti al baule per interagirci!");
						event.setCancelled(true);
					}
				}
			}
		}
	}

	public boolean isEnoughNearToInteract(Player player, Block block) {

		Block upfaceblock = block.getRelative(BlockFace.UP);
		Block northfaceblock = block.getRelative(BlockFace.NORTH);
		Block westfaceblock = block.getRelative(BlockFace.WEST);
		Block eastfaceblock = block.getRelative(BlockFace.EAST);
		Block southfaceblock = block.getRelative(BlockFace.SOUTH);
		Block northeastfaceblock = block.getRelative(BlockFace.NORTH_EAST);
		Block northwestfaceblock = block.getRelative(BlockFace.NORTH_WEST);
		Block southeastfaceblock = block.getRelative(BlockFace.SOUTH_EAST);
		Block southwestfaceblock = block.getRelative(BlockFace.SOUTH_WEST);

		if (!(block.getRelative(BlockFace.DOWN).getType().equals(Material.BEACON))) {
			if (player.getLocation().getBlock().equals(block) || player.getLocation().getBlock().equals(upfaceblock)
					|| player.getLocation().getBlock().equals(northfaceblock)
					|| player.getLocation().getBlock().equals(westfaceblock)
					|| player.getLocation().getBlock().equals(eastfaceblock)
					|| player.getLocation().getBlock().equals(southfaceblock)
					|| player.getLocation().getBlock().equals(northeastfaceblock)
					|| player.getLocation().getBlock().equals(northwestfaceblock)
					|| player.getLocation().getBlock().equals(southeastfaceblock)
					|| player.getLocation().getBlock().equals(southwestfaceblock)) {
				return true;
			} else {
				return false;
			}
		} else if (block.getRelative(BlockFace.DOWN).getType().equals(Material.BEACON)) {
			return true;
		}
		return false;
	}
}
