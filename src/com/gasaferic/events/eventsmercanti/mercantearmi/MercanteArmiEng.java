package com.gasaferic.events.eventsmercanti.mercantearmi;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gasaferic.main.Main;

public class MercanteArmiEng implements Listener {
	
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
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lSchematic Helmet Kevlar » Dynamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
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
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lSchematic Chestplate Kevlar » Dynamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
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
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lSchematic Leggings Kevlar » Dynamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
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
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lSchematic Boots Kevlar » Dynamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
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
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lSchematic Sawed-Off » Dynamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
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
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lSchematic SV-98 » Dynamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 2));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
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
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lSchematic Dinamite » Dynamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
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
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lSchematic TNT » Dynamite")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 5));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any " + progetto.getItemMeta().getDisplayName().replace("§f", "§2"));
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
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lDynamite » Iron Axe")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 3)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.IRON_AXE, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 3))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have enough §2Dynamite");
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
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lDynamite » Crowbar")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 10)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 10))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have enough §2Dynamite");
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
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lShelter » Crowbar")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any §2Shelter");
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
		if (clicked.getType() == Material.INK_SACK && clicked.getDurability() == 10) {
			if (event.getInventory().getName().equals("§7§lLooting Trader"))
				if (clicked.getItemMeta().getLore().contains("§f§lTNT » Crowbar")) {
					event.setCancelled(true);
					if (player.getInventory().containsAtLeast(progetto, 1)) {
						player.getInventory().removeItem(progetto);
						player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
					} else if (!(player.getInventory().containsAtLeast(progetto, 1))) {
						player.closeInventory();
						player.sendMessage(prefix + "§6You don't have any §2" + progetto.toString().substring(10).replace(" x 1}", " "));
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
		if (event.getInventory().getName().equals("§7§lLooting Trader")) {
			event.setCancelled(true);
		}
	}
}