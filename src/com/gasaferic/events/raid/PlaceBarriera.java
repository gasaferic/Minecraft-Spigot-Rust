package com.gasaferic.events.raid;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gasaferic.main.Main;

import net.minecraft.server.v1_8_R3.EnumDirection;

public class PlaceBarriera implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRaidIronDoor(PlayerInteractEvent event) {
		if (event.getClickedBlock() != null) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player player = (Player) event.getPlayer();

				Block block = event.getClickedBlock();

				if (player.getItemInHand().getType().equals(Material.RAILS)) {
					if (block.getType().equals(Material.IRON_DOOR_BLOCK)
							|| block.getType().equals(Material.WOODEN_DOOR)) {

						File f = new File(plugin.getDataFolder() + "/players/",
								Main.getMetadataManager().getBlockOwner(event.getClickedBlock()) + ".yml");
						FileConfiguration useFile = YamlConfiguration.loadConfiguration(f);
						
						event.setCancelled(true);
						if (useFile.getStringList("Team" + Main.getMetadataManager().getBlockOwner(event.getClickedBlock()))
								.contains(player.getName())
								|| player.getUniqueId().equals(Main.getMetadataManager().getBlockOwner(event.getClickedBlock()))) {
							HashMap<Block, Byte> blocchiIntorno = checkRelatives(block, player);
							if (blocchiIntorno != null) {
								if (!blocchiIntorno.isEmpty()) {
									for (Block barrierFree : blocchiIntorno.keySet()) {
										barrierFree.setType(Material.WALL_SIGN);
										barrierFree.setData(blocchiIntorno.get(barrierFree));
										Main.getMetadataManager().setBlockOwner(barrierFree, player.getUniqueId());
										Main.getMetadataManager().saveBarriera(barrierFree, player);
										removeBarriera(player);
										break;
									}
								}
							} else if (blocchiIntorno == null) {
								player.sendMessage(prefix
										+ "§6Hai raggiunto il numero limite di barricate costruibili su questa porta!");
							}
						} else if (!(useFile
								.getStringList("Team" + Main.getMetadataManager().getBlockOwner(event.getClickedBlock()))
								.contains(player.getName())
								|| player.getUniqueId().equals(Main.getMetadataManager().getBlockOwner(event.getClickedBlock())))) {
						}
					}
				}
			}
		}
	}

	public List<Block> getRelatives(Block block) {
		List<Block> relativeBlocks = new ArrayList<Block>();
		String[] relativesPositions = { "NORTH", "SOUTH" };
		for (String relativePosition : relativesPositions) {
			if (block.getRelative(BlockFace.DOWN).getType().equals(Material.IRON_DOOR_BLOCK)
					|| block.getRelative(BlockFace.DOWN).getType().equals(Material.WOODEN_DOOR)) {
				Block relativeBlock = block.getRelative(BlockFace.DOWN)
						.getRelative(BlockFace.valueOf(relativePosition));
				relativeBlocks.add(relativeBlock);
			} else if (block.getRelative(BlockFace.UP).getType().equals(Material.IRON_DOOR_BLOCK)
					|| block.getRelative(BlockFace.UP).getType().equals(Material.WOODEN_DOOR)) {
				Block relativeBlock = block.getRelative(BlockFace.UP).getRelative(BlockFace.valueOf(relativePosition));
				relativeBlocks.add(relativeBlock);
			}
			Block relativeBlock = block.getRelative(BlockFace.valueOf(relativePosition));
			relativeBlocks.add(relativeBlock);
		}
		return relativeBlocks;
	}

	public HashMap<Block, Byte> checkRelatives(Block block, Player player) {

		List<Block> relativeBlocks = getRelatives(block);
		HashMap<Block, Byte> availableBlocks = new HashMap<Block, Byte>();

		int ammountBarriere = 0;

		for (Block relativeBlock : relativeBlocks) {
			if (relativeBlock.getType().equals(Material.WALL_SIGN)) {
				ammountBarriere++;
			} else if (relativeBlock.getType().equals(Material.AIR)) {
				if (relativeBlock.getRelative(BlockFace.NORTH).equals(block)
						|| relativeBlock.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH).equals(block)
						|| relativeBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).equals(block)) {
					availableBlocks.put(relativeBlock, (byte) 0x03);
				} else if (relativeBlock.getRelative(BlockFace.SOUTH).equals(block)
						|| relativeBlock.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH).equals(block)
						|| relativeBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).equals(block)) {
					availableBlocks.put(relativeBlock, (byte) 0x02);
				}
			}
		}

		if (ammountBarriere < 2) {
			if (((CraftPlayer) player).getHandle().getDirection() == EnumDirection.NORTH) {
				Iterator<Block> itrBlocks = availableBlocks.keySet().iterator();
				while (itrBlocks.hasNext()) {
					Block barrierascelta = itrBlocks.next();
					Block portaNorth = barrierascelta.getRelative(BlockFace.NORTH);
					Block portaSouth = barrierascelta.getRelative(BlockFace.SOUTH);
					if (portaSouth.equals(block) || portaSouth.getRelative(BlockFace.UP).equals(block)
							|| portaSouth.getRelative(BlockFace.DOWN).equals(block)) {
						itrBlocks.remove();
					}
					if (portaNorth.getRelative(BlockFace.UP).equals(block)
							|| portaNorth.getRelative(BlockFace.DOWN).equals(block)) {
						if (!portaNorth.equals(block)) {
							itrBlocks.remove();
						}
					}
				}
			} else if (((CraftPlayer) player).getHandle().getDirection() == EnumDirection.SOUTH) {
				Iterator<Block> itrBlocks = availableBlocks.keySet().iterator();
				while (itrBlocks.hasNext()) {
					Block barrierascelta = itrBlocks.next();
					Block portaNorth = barrierascelta.getRelative(BlockFace.NORTH);
					Block portaSouth = barrierascelta.getRelative(BlockFace.SOUTH);
					if (portaNorth.equals(block) || portaNorth.getRelative(BlockFace.UP).equals(block)
							|| portaNorth.getRelative(BlockFace.DOWN).equals(block)) {
						itrBlocks.remove();
					}
					if (portaSouth.getRelative(BlockFace.UP).equals(block)
							|| portaSouth.getRelative(BlockFace.DOWN).equals(block)) {
						if (!portaSouth.equals(block)) {
							itrBlocks.remove();
						}
					}
				}
			}
			return availableBlocks;
		} else {
			return null;
		}
	}

	public void removeBarriera(Player player) {
		if (player.getItemInHand().getAmount() > 1) {
			player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
		} else if (player.getItemInHand().getAmount() == 1) {
			player.getInventory().remove(player.getItemInHand());
		}
	}

}