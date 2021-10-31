package com.gasaferic.events.raid;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;
import com.gasaferic.main.MetadataManager;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;
import com.gasaferic.model.Team;

public class Raid implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();
	private MetadataManager metadataManager = Main.getMetadataManager();

	@EventHandler
	public void onRaidIronDoor(PlayerInteractEvent event) {
		if (event.getClickedBlock() != null) {
			if (event.getClickedBlock().getType().equals(Material.IRON_DOOR_BLOCK)) {
				if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
					if (metadataManager.isOwned(event.getClickedBlock())) {
						Player player = (Player) event.getPlayer();

						Team team = shelterManager
								.getShelter(survivorManager
										.getSurvivorByUniqueId(metadataManager.getBlockOwner(event.getClickedBlock())))
								.getTeam();

						raidDoor(player, event.getClickedBlock(), player.getLocation().getX(),
								player.getLocation().getY(), player.getLocation().getZ(), team);
					}
				}
			}
		}
	}

	@EventHandler
	public void onRaidWoodDoorTNT(PlayerInteractEvent event) {
		if (event.getClickedBlock() != null) {
			if (event.getClickedBlock().getType().equals(Material.WOODEN_DOOR)) {
				if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
					if (metadataManager.isOwned(event.getClickedBlock())) {
						Player player = (Player) event.getPlayer();

						Team team = shelterManager
								.getShelter(survivorManager
										.getSurvivorByUniqueId(metadataManager.getBlockOwner(event.getClickedBlock())))
								.getTeam();

						raidDoor(player, event.getClickedBlock(), player.getLocation().getX(),
								player.getLocation().getY(), player.getLocation().getZ(), team);
					}
				}
			}
		}
	}

	@EventHandler
	public void onRaidWoodDoorAxe(PlayerInteractEvent event) {
		if (event.getClickedBlock() != null) {
			if (event.getClickedBlock().getType().equals(Material.WOODEN_DOOR)) {
				if (metadataManager.isOwned(event.getClickedBlock())) {
					Player player = (Player) event.getPlayer();
					if (event.getPlayer().getItemInHand().getType().equals(Material.IRON_AXE)) {

						Team team = shelterManager
								.getShelter(survivorManager
										.getSurvivorByUniqueId(metadataManager.getBlockOwner(event.getClickedBlock())))
								.getTeam();

						raidWoodDoor(player, event.getClickedBlock(), player.getLocation().getX(),
								player.getLocation().getY(), player.getLocation().getZ(), team);
					}
				}
			}
		}
	}

	@EventHandler
	public void onRaidTrapDoorTNT(PlayerInteractEvent event) {
		if (event.getClickedBlock() != null) {
			if (event.getClickedBlock().getType() == Material.TRAP_DOOR
					|| event.getClickedBlock().getType() == Material.IRON_TRAPDOOR) {
				if (metadataManager.isOwned(event.getClickedBlock())) {
					if ((event.getAction() == Action.RIGHT_CLICK_BLOCK
							|| event.getAction() == Action.LEFT_CLICK_BLOCK)) {
						Player player = (Player) event.getPlayer();

						Survivor survivor = survivorManager.getSurvivorByPlayer(player);

						Team team = shelterManager
								.getShelter(survivorManager
										.getSurvivorByUniqueId(metadataManager.getBlockOwner(event.getClickedBlock())))
								.getTeam();

						Block block = event.getClickedBlock();
						if ((event.getPlayer().getInventory().getItemInHand().getType() == Material.TNT)) {
							if (!(team.getTeamMembers().contains(survivor) || player.getUniqueId()
									.equals(metadataManager.getBlockOwner(event.getClickedBlock())))) {
								ItemStack item = new ItemStack(Material.TNT, 1);
								double x = event.getClickedBlock().getX();
								double y = event.getClickedBlock().getY();
								double z = event.getClickedBlock().getZ();
								Location location = new Location(player.getWorld(), x, y, z);
								player.getWorld().playEffect(location, Effect.EXPLOSION_LARGE, 1);
								player.getWorld().playSound(location, Sound.EXPLODE, 1, 1);
								metadataManager.removeTrapdoor(block, metadataManager.getBlockOwner(block));
								block.breakNaturally();
								player.getInventory().removeItem(item);
							} else if (team.getTeamMembers().contains(survivor) || player.getUniqueId()
									.equals(metadataManager.getBlockOwner(event.getClickedBlock()))) {
								player.sendMessage(prefix + "§6Non puoi raidare le tue Botole!");
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onRaidTrapDoorAxe(PlayerInteractEvent event) {
		if (event.getClickedBlock() != null) {
			if (event.getClickedBlock().getType() == Material.TRAP_DOOR) {
				if (metadataManager.isOwned(event.getClickedBlock())) {
					if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
						Player player = (Player) event.getPlayer();

						Survivor survivor = survivorManager.getSurvivorByPlayer(player);

						Team team = shelterManager
								.getShelter(survivorManager
										.getSurvivorByUniqueId(metadataManager.getBlockOwner(event.getClickedBlock())))
								.getTeam();

						Block block = event.getClickedBlock();
						if ((event.getPlayer().getInventory().getItemInHand().getType() == Material.IRON_AXE)) {
							if (!(team.getTeamMembers().contains(survivor) || player.getUniqueId()
									.equals(metadataManager.getBlockOwner(event.getClickedBlock())))) {
								double x = event.getClickedBlock().getX();
								double y = event.getClickedBlock().getY();
								double z = event.getClickedBlock().getZ();
								Location location = new Location(player.getWorld(), x, y, z);
								player.getWorld().playEffect(location, Effect.EXPLOSION_LARGE, 2003);
								player.getWorld().playSound(location, Sound.EXPLODE, 1, 1);
								metadataManager.removeTrapdoor(block, metadataManager.getBlockOwner(block));
								block.breakNaturally();
								player.getInventory().removeItem(player.getItemInHand());
								player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_LARGE, 1);
							} else if (team.getTeamMembers().contains(survivor) || player.getUniqueId()
									.equals(metadataManager.getBlockOwner(event.getClickedBlock()))) {
								player.sendMessage(prefix + "§6Non puoi raidare le tue Botole!");
							}
						}
					}
				}
			}
		}
	}

	public void raidDoor(Player player, Block clickedblock, double x, double y, double z, Team team) {

		Survivor survivor = survivorManager.getSurvivorByPlayer(player);

		Location location = new Location(player.getWorld(), x, y, z);

		Block block = clickedblock;
		if (player.getInventory().getItemInHand().getType().equals(Material.TNT)) {
			if (!(team.getTeamMembers().contains(survivor)
					|| player.getUniqueId().equals(metadataManager.getBlockOwner(clickedblock)))) {
				List<Block> attachedBlocks = checkRelatives(block);
				if (!attachedBlocks.isEmpty()) {
					for (Block attachedBarrier : attachedBlocks) {
						if (attachedBarrier.hasMetadata("owner")) {
							metadataManager.removeBarriera(attachedBarrier,
									metadataManager.getBlockOwner(attachedBarrier));
							attachedBarrier.setType(Material.AIR);
							attachedBarrier.getLocation().getWorld().dropItem(attachedBarrier.getLocation(),
									new ItemStack(Material.RAILS, 1));
							break;
						}
					}
				} else if (attachedBlocks.isEmpty()) {
					if (block.hasMetadata("owner")) {
						if (block.getRelative(BlockFace.DOWN).getType().equals(Material.IRON_DOOR_BLOCK)) {
							metadataManager.removeDoor(block, metadataManager.getBlockOwner(block));
							metadataManager.removeDoor(block.getRelative(BlockFace.DOWN),
									metadataManager.getBlockOwner(block.getRelative(BlockFace.DOWN)));
							block.getRelative(BlockFace.DOWN).breakNaturally();
						} else if (block.getRelative(BlockFace.UP).getType().equals(Material.IRON_DOOR_BLOCK)) {
							metadataManager.removeDoor(block, metadataManager.getBlockOwner(block));
							metadataManager.removeDoor(block.getRelative(BlockFace.UP),
									metadataManager.getBlockOwner(block.getRelative(BlockFace.UP)));
							block.breakNaturally();
						}
					}
				}
				location.getWorld().playEffect(location, Effect.EXPLOSION_LARGE, 1);
				location.getWorld().playSound(location, Sound.EXPLODE, 1, 1);
				if (player.getItemInHand().getAmount() > 1) {
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
				} else if (player.getItemInHand().getAmount() == 1) {
					player.getInventory().removeItem(player.getItemInHand());
				}
			} else if ((team.getTeamMembers().contains(survivor)
					|| player.getUniqueId().equals(metadataManager.getBlockOwner(clickedblock))))

			{
				player.sendMessage(prefix + "§6Non puoi raidare le tue Porte!");
			}
		}

	}

	public void raidWoodDoor(Player player, Block block, double x, double y, double z, Team team) {

		Survivor survivor = survivorManager.getSurvivorByPlayer(player);

		Location location = new Location(player.getWorld(), x, y, z);

		if (player.getInventory().getItemInHand().getType().equals(Material.IRON_AXE)) {
			if (!(team.getTeamMembers().contains(survivor)
					|| player.getUniqueId().equals(metadataManager.getBlockOwner(block)))) {
				List<Block> attachedBlocks = checkRelatives(block);
				if (!attachedBlocks.isEmpty()) {
					for (Block attachedBarrier : attachedBlocks) {
						if (attachedBarrier.hasMetadata("owner")) {
							metadataManager.removeBarriera(attachedBarrier,
									metadataManager.getBlockOwner(attachedBarrier));
							attachedBarrier.setType(Material.AIR);
							attachedBarrier.getLocation().getWorld().dropItem(attachedBarrier.getLocation(),
									new ItemStack(Material.RAILS, 1));
							break;
						}
					}
				} else if (attachedBlocks.isEmpty()) {
					if (block.hasMetadata("owner")) {
						if (block.getRelative(BlockFace.DOWN).getType().equals(Material.WOODEN_DOOR)) {
							metadataManager.removeDoor(block, metadataManager.getBlockOwner(block));
							metadataManager.removeDoor(block.getRelative(BlockFace.DOWN),
									metadataManager.getBlockOwner(block.getRelative(BlockFace.DOWN)));
							block.getRelative(BlockFace.DOWN).breakNaturally();
						} else if (block.getRelative(BlockFace.UP).getType().equals(Material.WOODEN_DOOR)) {
							metadataManager.removeDoor(block, metadataManager.getBlockOwner(block));
							metadataManager.removeDoor(block.getRelative(BlockFace.UP),
									metadataManager.getBlockOwner(block.getRelative(BlockFace.UP)));
							block.breakNaturally();
						}
					}
				}
				location.getWorld().playEffect(location, Effect.EXPLOSION_LARGE, 2003);
				location.getWorld().playSound(location, Sound.EXPLODE, 1, 1);
				player.getInventory().removeItem(player.getItemInHand());
			} else if ((team.getTeamMembers().contains(survivor)
					|| player.getUniqueId().equals(metadataManager.getBlockOwner(block))))

			{
				player.sendMessage(prefix + "§6Non puoi raidare le tue Porte!");
			}
		}

	}

	public List<Block> getRelatives(List<Block> list) {
		List<Block> checkedBlocks = new ArrayList<Block>();
		for (Block block : list) {
			if (block.getType().equals(Material.WALL_SIGN)) {
				checkedBlocks.add(block);
			}
		}
		return checkedBlocks;
	}

	public List<Block> checkRelatives(Block block) {
		List<Block> relativeBlocks = new ArrayList<Block>();
		if (block.getRelative(BlockFace.DOWN).getType().equals(Material.IRON_DOOR_BLOCK)
				|| block.getRelative(BlockFace.DOWN).getType().equals(Material.WOODEN_DOOR)) {
			Block downBlock = block.getRelative(BlockFace.DOWN);
			if (block.getRelative(BlockFace.EAST).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(block.getRelative(BlockFace.EAST));
			} else if (block.getRelative(BlockFace.WEST).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(block.getRelative(BlockFace.WEST));
			} else if (block.getRelative(BlockFace.NORTH).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(block.getRelative(BlockFace.NORTH));
			} else if (block.getRelative(BlockFace.SOUTH).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(block.getRelative(BlockFace.SOUTH));
			} else if (downBlock.getRelative(BlockFace.EAST).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(downBlock.getRelative(BlockFace.EAST));
			} else if (downBlock.getRelative(BlockFace.WEST).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(downBlock.getRelative(BlockFace.WEST));
			} else if (downBlock.getRelative(BlockFace.NORTH).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(downBlock.getRelative(BlockFace.NORTH));
			} else if (downBlock.getRelative(BlockFace.SOUTH).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(downBlock.getRelative(BlockFace.SOUTH));
			}
		} else if (block.getRelative(BlockFace.UP).getType().equals(Material.IRON_DOOR_BLOCK)
				|| block.getRelative(BlockFace.UP).getType().equals(Material.WOODEN_DOOR)) {
			Block upBlock = block.getRelative(BlockFace.UP);
			if (block.getRelative(BlockFace.EAST).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(block.getRelative(BlockFace.EAST));
			} else if (block.getRelative(BlockFace.WEST).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(block.getRelative(BlockFace.WEST));
			} else if (block.getRelative(BlockFace.NORTH).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(block.getRelative(BlockFace.NORTH));
			} else if (block.getRelative(BlockFace.SOUTH).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(block.getRelative(BlockFace.SOUTH));
			} else if (upBlock.getRelative(BlockFace.EAST).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(upBlock.getRelative(BlockFace.EAST));
			} else if (upBlock.getRelative(BlockFace.WEST).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(upBlock.getRelative(BlockFace.WEST));
			} else if (upBlock.getRelative(BlockFace.NORTH).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(upBlock.getRelative(BlockFace.NORTH));
			} else if (upBlock.getRelative(BlockFace.SOUTH).getType().equals(Material.WALL_SIGN)) {
				relativeBlocks.add(upBlock.getRelative(BlockFace.SOUTH));
			}
		}
		return relativeBlocks;
	}

}