package com.gasaferic.events.menusevents;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gasaferic.events.menusevents.inventari.mainMenu;
import com.gasaferic.events.menusevents.inventari.mainMenuAdmin;
import com.gasaferic.main.Main;
import com.gasaferic.main.MetadataManager;
import com.gasaferic.model.Language;
import com.gasaferic.model.Survivor;

public class OpenMenuCasa implements Listener {

	public static List<Player> staffplayersinv = new ArrayList<Player>();

	private static Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	private MetadataManager metadataManager = Main.getMetadataManager();

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME) {
				Player player = (Player) e.getPlayer();
				Survivor survivor = Main.getSurvivorManager().getSurvivorByPlayer(player);
				if (!player.isSneaking()) {
					if (survivor.getLanguage().equals(Language.IT)) {
						if (e.getClickedBlock().hasMetadata("owner")) {
							if (e.getPlayer().getName().equals(Bukkit.getServer()
									.getOfflinePlayer(metadataManager.getBlockOwner(e.getClickedBlock())).getName())) {
								mainMenu.openMenuCasa(player, player);
							} else if (e.getPlayer().hasPermission("rust.consolebypass")) {
								Player controllo = Bukkit.getPlayer(metadataManager.getBlockOwner(e.getClickedBlock()));
								if (controllo != null) {
									Player playerblocco = Bukkit.getServer()
											.getPlayer(metadataManager.getBlockOwner(e.getClickedBlock()));
									mainMenuAdmin.openMenuCasa(player, playerblocco);
								} else if (controllo == null) {
									OfflinePlayer playeroffline = Bukkit.getServer()
											.getOfflinePlayer(metadataManager.getBlockOwner(e.getClickedBlock()));
									mainMenuAdmin.openMenuCasa(player, playeroffline);
								}
							} else if (!(e.getPlayer().getName().equals(Bukkit.getServer()
									.getOfflinePlayer(metadataManager.getBlockOwner(e.getClickedBlock())).getName()))) {
								e.getPlayer().sendMessage(prefix + "§6Questa casa appartiene a "
										+ Bukkit.getServer()
												.getOfflinePlayer(metadataManager.getBlockOwner(e.getClickedBlock()))
												.getName());
							}
						} else if (!(e.getClickedBlock().hasMetadata("owner"))) {
							e.getPlayer().sendMessage(prefix + "§6Questo rifugio è abbandonato.");
						}
					}
				} else if (player.isSneaking()) {
					if (survivor.getLanguage().equals(Language.IT)) {
						if (e.getClickedBlock().hasMetadata("owner")) {
							if (e.getPlayer().getName().equals(Bukkit.getServer()
									.getOfflinePlayer(metadataManager.getBlockOwner(e.getClickedBlock())).getName())) {
								if (!e.getPlayer().hasPermission("rust.consolebypass")) {
									mainMenu.openMenuCasa(player, player);
								} else {
									mainMenuAdmin.openMenuCasa(player, player);
								}
							} else if (e.getPlayer().hasPermission("rust.consolebypass")) {
								Player controllo = Bukkit.getPlayer(metadataManager.getBlockOwner(e.getClickedBlock()));
								if (controllo != null) {
									Player playerblocco = Bukkit.getServer()
											.getPlayer(metadataManager.getBlockOwner(e.getClickedBlock()));
									mainMenuAdmin.openMenuCasa(player, playerblocco);
								} else if (controllo == null) {
									OfflinePlayer playeroffline = Bukkit.getServer()
											.getOfflinePlayer(metadataManager.getBlockOwner(e.getClickedBlock()));
									mainMenuAdmin.openMenuCasa(player, playeroffline);
								}
							} else if (!(e.getPlayer().getName().equals(Bukkit.getServer()
									.getOfflinePlayer(metadataManager.getBlockOwner(e.getClickedBlock())).getName()))) {
								e.getPlayer().sendMessage(prefix + "§6Questa casa appartiene a "
										+ Bukkit.getServer()
												.getOfflinePlayer(metadataManager.getBlockOwner(e.getClickedBlock()))
												.getName());
							}
						} else if (!(e.getClickedBlock().hasMetadata("owner"))) {
							e.getPlayer().sendMessage(prefix + "§6Questo rifugio è abbandonato.");
						}
					}
				}
			}
		}
	}

}
