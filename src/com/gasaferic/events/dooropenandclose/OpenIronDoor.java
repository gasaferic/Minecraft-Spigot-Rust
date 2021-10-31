package com.gasaferic.events.dooropenandclose;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;

import com.gasaferic.main.Main;
import com.gasaferic.main.MetadataManager;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;
import com.gasaferic.model.Team;

public class OpenIronDoor implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();
	private MetadataManager metadataManager = Main.getMetadataManager();

	@EventHandler
	public void onIronDoorTeamer(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player player = e.getPlayer();
				Survivor survivor = survivorManager.getSurvivorByPlayer(player);
				if (metadataManager.getBlockOwner(e.getClickedBlock()) != null) {
					if (!player.getItemInHand().getType().equals(Material.RAILS)) {

						Block b = e.getClickedBlock();

						if (b.getType().equals(Material.IRON_DOOR_BLOCK)
								&& b.getRelative(BlockFace.UP).getType().equals(Material.IRON_DOOR_BLOCK)) {

							Team team = shelterManager
									.getShelter(survivorManager
											.getSurvivorByUniqueId(metadataManager.getBlockOwner(e.getClickedBlock())))
									.getTeam();

							BlockState state = b.getState();
							MaterialData openable = (MaterialData) state.getData();

							if ((team.getTeamMembers().contains(survivor)
									|| player.getUniqueId()
											.equals(Main.getMetadataManager().getBlockOwner(e.getClickedBlock()))
									|| player.hasPermission("rust.doorbypass"))) {
								if (!((Openable) openable).isOpen()) {
									((Openable) openable).setOpen(true);
									state.setData(openable);
									state.update();
									e.getClickedBlock().getWorld().playEffect(e.getClickedBlock().getLocation(),
											Effect.DOOR_TOGGLE, 0);
								} else {
									((Openable) openable).setOpen(false);
									state.setData(openable);
									state.update();
									e.getClickedBlock().getWorld().playEffect(e.getClickedBlock().getLocation(),
											Effect.DOOR_TOGGLE, 0);
								}
							} else {
								player.sendMessage(prefix + "§6Non puoi aprire questa porta!");
							}
						}
					} else {
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onIronDoorTeamerTop(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player player = e.getPlayer();
				Survivor survivor = survivorManager.getSurvivorByPlayer(player);
				if (metadataManager.getBlockOwner(e.getClickedBlock()) != null) {
					if (!player.getItemInHand().getType().equals(Material.RAILS)) {

						Block b = e.getClickedBlock();
						if (b.getType().equals(Material.IRON_DOOR_BLOCK)
								&& b.getRelative(BlockFace.DOWN).getType().equals(Material.IRON_DOOR_BLOCK)) {

							Team team = shelterManager
									.getShelter(survivorManager
											.getSurvivorByUniqueId(metadataManager.getBlockOwner(e.getClickedBlock())))
									.getTeam();

							BlockState state = b.getRelative(BlockFace.DOWN).getState();
							MaterialData openable = (MaterialData) state.getData();

							if ((team.getTeamMembers().contains(survivor)
									|| player.getUniqueId()
											.equals(Main.getMetadataManager().getBlockOwner(e.getClickedBlock()))
									|| player.hasPermission("rust.doorbypass"))) {
								if (!((Openable) openable).isOpen()) {
									((Openable) openable).setOpen(true);
									state.setData(openable);
									state.update();
									e.getClickedBlock().getWorld().playEffect(e.getClickedBlock().getLocation(),
											Effect.DOOR_TOGGLE, 0);
								} else {
									((Openable) openable).setOpen(false);
									state.setData(openable);
									state.update();
									e.getClickedBlock().getWorld().playEffect(e.getClickedBlock().getLocation(),
											Effect.DOOR_TOGGLE, 0);
								}
							} else {
								player.sendMessage(prefix + "§6Non puoi aprire questa porta!");
							}
						}
					} else {
						e.setCancelled(true);
					}
				}
			}
		}
	}
}