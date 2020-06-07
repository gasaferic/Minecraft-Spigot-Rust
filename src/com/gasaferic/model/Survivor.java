package com.gasaferic.model;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gasaferic.database.MySQL;
import com.gasaferic.main.Main;

public class Survivor {

	// The model package contains all classes that contain data, but do nothing (or
	// barely anything) on their own. They don't interact with other classes that do
	// things
	// For instance, The SurvivaorManager does all kind of things and interacts with
	// stuff, but the Survivor class only interacts with itself, not with anything
	// else

	private MySQL mySQL = Main.getMySQL();
	private OfflinePlayer offlinePlayer;
	private Player player;
	private UUID uuid;
	private Language language;
	private int addPlayerPage;
	private boolean changingPage;
	private boolean setupModeEnabled;
	
	private ItemStack[] inventoryContents;

	public Survivor(OfflinePlayer offlinePlayer) {
		this.offlinePlayer = offlinePlayer;
		if (offlinePlayer.getPlayer() != null) {
			this.player = offlinePlayer.getPlayer();
		}
		this.uuid = offlinePlayer.getUniqueId();

		this.language = Language.IT;
		
		this.addPlayerPage = 1;
		
		this.setupModeEnabled = false;
	}

	public OfflinePlayer getOfflinePlayer() {
		return this.offlinePlayer;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getName() {
		return this.offlinePlayer.getName();
	}

	public UUID getUniqueId() {
		return this.uuid;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
		mySQL.setLanguage(this, language.getLang());
	}

	public int getAddPlayerPage() {
		return addPlayerPage;
	}

	public void setAddPlayerPage(int addPlayerPage) {
		this.addPlayerPage = addPlayerPage < 1 ? 1 : addPlayerPage;
	}

	public boolean isChangingPage() {
		return changingPage;
	}

	public void setChangingPage(boolean changingPage) {
		this.changingPage = changingPage;
	}
	
	public boolean setupModeEnabled() {
		return setupModeEnabled;
	}
	
	public void toggleSetupMode() {
		setupModeEnabled = !setupModeEnabled;
		setupModeInventory(setupModeEnabled);
	}
	
	public void setupModeInventory(boolean enable) {
		Inventory playerInventory = getPlayer().getInventory();
		
		if (enable) {
			
			inventoryContents = playerInventory.getContents();
			playerInventory.clear();
			
			playerInventory.addItem(getSetupItem(Material.DRAGON_EGG, (byte) 0, "§4§lGeneratore di Minerali"));
			playerInventory.addItem(getSetupItem(Material.DRAGON_EGG, (byte) 0, "§c§lAlbero per Risorse"));
			playerInventory.addItem(getSetupItem(Material.CHEST, (byte) 0, "§a§lRadzone Loot Chest"));
			playerInventory.addItem(getSetupItem(Material.BEACON, (byte) 0, "§3§lAirdrop"));
			
		} else {
			playerInventory.clear();
			playerInventory.setContents(inventoryContents);
		}
	}
	
	public ItemStack getSetupItem(Material type, byte data, String name) {
		ItemStack setupItem = new ItemStack(type, 1, data);
		ItemMeta itemMeta = setupItem.getItemMeta();
		itemMeta.setDisplayName(name);
		setupItem.setItemMeta(itemMeta);
		return setupItem;
	}
	
}