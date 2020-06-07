package com.gasaferic.events.dooropenandclose;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gasaferic.main.Main;
import com.gasaferic.main.MetadataManager;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Survivor;
import com.gasaferic.model.Team;

public class OpenWoodTrapdoor implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();
	private MetadataManager metadataManager = Main.getMetadataManager();

	@EventHandler
	public void onDoorClick(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player player = e.getPlayer();
				Survivor survivor = survivorManager.getSurvivorByPlayer(player);
				if (Main.getMetadataManager().getBlockOwner(e.getClickedBlock()) != null) {

					Block b = e.getClickedBlock();

					if (b.getType() == Material.TRAP_DOOR) {

						Team team = shelterManager.getShelter(
								survivorManager.getSurvivorByUUID(metadataManager.getBlockOwner(e.getClickedBlock())))
								.getTeam();

						if ((team.getTeamMembers().contains(survivor)
								|| player.getUniqueId()
										.equals(Main.getMetadataManager().getBlockOwner(e.getClickedBlock()))
								|| player.hasPermission("rust.doorbypass"))) {
							e.setCancelled(false);
						} else {
							player.sendMessage(prefix + "§6Non puoi aprire questa botola!");
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}
}