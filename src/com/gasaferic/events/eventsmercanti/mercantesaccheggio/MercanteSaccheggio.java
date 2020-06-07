package com.gasaferic.events.eventsmercanti.mercantesaccheggio;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gasaferic.main.Main;

public class MercanteSaccheggio implements Listener {
	
	private Main plugin = Main.getInstance();
	
	private String prefix = plugin.getPrefixString("prefix");

	@EventHandler
	public void progettoElmoKevlar(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BOOK, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progettometa.setDisplayName("§fProgetto Elmo Kevlar");
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lProgetto Elmo Kevlar » Dinamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessun " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
					}
				}
		}
	}

	@EventHandler
	public void progettoCorazzaKevlar(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BOOK, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progettometa.setDisplayName("§fProgetto Corazza Kevlar");
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lProgetto Corazza Kevlar » Dinamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessun " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
					}
				}
		}
	}

	@EventHandler
	public void progettoGambaliKevlar(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BOOK, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progettometa.setDisplayName("§fProgetto Gambali Kevlar");
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lProgetto Gambali Kevlar » Dinamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessun " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
					}
				}
		}
	}

	@EventHandler
	public void progettoStivaliKevlar(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BOOK, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progettometa.setDisplayName("§fProgetto Stivali Kevlar");
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lProgetto Stivali Kevlar » Dinamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessun " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
					}
				}
		}
	}

	@EventHandler
	public void progettoSawedOff(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BOOK, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progettometa.setDisplayName("§fProgetto Sawed-Off");
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lProgetto Sawed-Off » Dinamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessun " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
					}
				}
		}
	}

	@EventHandler
	public void progettoSV98(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BOOK, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progettometa.setDisplayName("§fProgetto SV-98");
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lProgetto SV-98 » Dinamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 2));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessun " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
					}
				}
		}
	}

	@EventHandler
	public void progettoDinamite(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BOOK, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progettometa.setDisplayName("§fProgetto Dinamite");
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lProgetto Dinamite » Dinamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessun " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
					}
				}
		}
	}

	@EventHandler
	public void progettoTNT(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BOOK, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progettometa.setDisplayName("§fProgetto TNT");
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lProgetto TNT » Dinamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 5));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessun " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
					}
				}
		}
	}

	@EventHandler
	public void progettoAsciaFerro(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BLAZE_ROD, 3);
		ItemMeta progettometa = progetto.getItemMeta();
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lDinamite » Ascia in Ferro")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 3)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.IRON_AXE, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 3))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessuna Dinamite");
					}
				}
		}
	}

	@EventHandler
	public void progettoSprangaDinamite(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.BLAZE_ROD, 10);
		ItemMeta progettometa = progetto.getItemMeta();
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lDinamite » Spranga")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 10)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 10))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessuna §2Dinamite");
					}
				}
		}
	}

	@EventHandler
	public void progettoSprangaCasa(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.JUKEBOX, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lCasa » Spranga")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessun §2Rifugio");
					}
				}
		}
	}

	@EventHandler
	public void progettoSprangaTNT(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked(); // The player that clicked the item
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		ItemStack progetto = new ItemStack(Material.TNT, 1);
		ItemMeta progettometa = progetto.getItemMeta();
		progettometa.setDisplayName("§fTNT");
		progetto.setItemMeta(progettometa);
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lMercante Saccheggio"))
				if (clicked.getItemMeta().getLore().contains("§f§lTNT » Spranga")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6Non hai nessuna " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
					}
				}
		}
	}



	@EventHandler
	public void clickChiave(InventoryClickEvent event) {
		if (event.getInventory() != null)
			;
		if (event.getInventory().getType() == InventoryType.CREATIVE)
			return;
		if (event.getCurrentItem() == null)
			return;
		if (event.getInventory().getName().equals("§7§lMercante Saccheggio")) {
			event.setCancelled(true);
		}
	}
}