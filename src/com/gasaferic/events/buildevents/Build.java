package com.gasaferic.events.buildevents;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;
import com.gasaferic.main.Schematic;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.model.Shelter;
import com.gasaferic.model.Survivor;

public class Build implements Listener {

	private Main plugin = Main.getInstance();

	private SurvivorManager survivorManager = Main.getSurvivorManager();
	private ShelterManager shelterManager = Main.getShelterManager();

	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Survivor survivor = survivorManager.getSurvivorByPlayer(player);
		if (event.getBlock().getType().equals(Material.JUKEBOX)) {
			event.setCancelled(true);
			if (shelterManager.getShelter(survivor) == null) {
				if (!RegionProtected.isProtected(event.getBlock(), plugin, survivor.getPlayer())) {
					if (MethodBuild.enoughSpace(event.getBlock(), plugin, player, prefix)) {
						if (MethodBuild.noDoorSpace(event.getBlock(), plugin, player, prefix)) {
							if (MethodBuild.baseGrass(event.getBlock(), plugin, player, prefix)) {
								if (event.getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
									Inventory inv = player.getInventory();
									ItemStack item = new ItemStack(Material.JUKEBOX, 1);

									Schematic.paste("casarust.schematic", event.getBlock().getLocation(), false);

									Block consoleBlock = event.getBlock().getLocation().add(0, -1, -3).getBlock();
									Main.getMetadataManager().setBlockOwner(consoleBlock, player.getUniqueId());

									Main.getShelterManager().registerShelter(
											Main.getSurvivorManager().getSurvivorByPlayer(player),
											new Shelter(Main.getSurvivorManager().getSurvivorByPlayer(player),
													consoleBlock));

									Main.getPlayersRegionManager().registerPlayerRegion(survivor,
											event.getBlock().getLocation());

									inv.removeItem(item);
								}
							} else {
								player.sendMessage(prefix
										+ plugin.getConfig().getString("grassbase" + survivor.getLanguage().getLang()));
							}
						} else {
							player.sendMessage(prefix + plugin.getConfig()
									.getString("notenoughspacefordoor" + survivor.getLanguage().getLang()));
						}
					} else {
						player.sendMessage(prefix
								+ plugin.getConfig().getString("notenoughspace" + survivor.getLanguage().getLang()));
					}
				} else {
					player.sendMessage(prefix
							+ plugin.getConfig().getString("troppovicinoreg" + survivor.getLanguage().getLang()));
				}
			} else if (shelterManager.getShelter(survivor) != null) {
				player.sendMessage(
						prefix + plugin.getConfig().getString("alreadyhaveshelter" + survivor.getLanguage().getLang()));
			}
		}
	}

}