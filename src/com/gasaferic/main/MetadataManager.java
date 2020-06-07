package com.gasaferic.main;

import java.util.List;
import java.util.UUID;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;

public class MetadataManager {

	private static Main plugin = Main.getInstance();

	private static SurvivorManager survivorManager = Main.getSurvivorManager();
	private static ShelterManager shelterManager = Main.getShelterManager();

	public void setBlockOwner(Block b, UUID uuid) {
		if (uuid != null) {
			b.setMetadata("owner", new FixedMetadataValue(plugin, uuid.toString()));
		} else {
			b.setMetadata("owner", new FixedMetadataValue(plugin, null));
		}
	}

	public void removeBlockOwner(Block b) {
		b.removeMetadata("owner", plugin);
	}

	public boolean isOwned(Block b) {
		return b.hasMetadata("owner");
	}

	public UUID getBlockOwner(Block b) {
		UUID owner = null;
		List<MetadataValue> metas = b.getMetadata("owner");
		for (MetadataValue meta : metas) {
			if (meta.getOwningPlugin().getName().equals(plugin.getName())) {
				owner = UUID.fromString(meta.asString());
			}
		}
		return owner;
	}

	public void saveDoor(Block block, Player player) {
		setBlockOwner(block, player.getUniqueId());
		shelterManager.getShelter(survivorManager.getSurvivorByPlayer(player)).addDoor(block);
	}

	public void saveTrapdoor(Block block, Player player) {
		setBlockOwner(block, player.getUniqueId());
		shelterManager.getShelter(survivorManager.getSurvivorByPlayer(player)).addTrapdoor(block);
	}

	public void saveBarriera(Block block, Player player) {
		setBlockOwner(block, player.getUniqueId());
		shelterManager.getShelter(survivorManager.getSurvivorByPlayer(player)).addBarrier(block);
	}

	public void removeBarriera(Block block, Player player) {
		if (shelterManager.getShelter(survivorManager.getSurvivorByPlayer(player)).getBarriers().contains(block)) {
			removeBlockOwner(block);
			shelterManager.getShelter(survivorManager.getSurvivorByPlayer(player)).removeBarrier(block);
		}
	}

	public void removeBarriera(Block block, UUID uuid) {
		if (shelterManager.getShelter(survivorManager.getSurvivorByUUID(uuid)).getBarriers().contains(block)) {
			removeBlockOwner(block);
			shelterManager.getShelter(survivorManager.getSurvivorByUUID(uuid)).removeBarrier(block);
		}
	}

	public void removeDoor(Block block, Player player) {
		if (shelterManager.getShelter(survivorManager.getSurvivorByPlayer(player)).getDoors().contains(block)) {
			removeBlockOwner(block);
			shelterManager.getShelter(survivorManager.getSurvivorByPlayer(player)).removeDoor(block);
		}
	}

	public void removeDoor(Block block, UUID uuid) {
		if (shelterManager.getShelter(survivorManager.getSurvivorByUUID(uuid)).getDoors().contains(block)) {
			removeBlockOwner(block);
			shelterManager.getShelter(survivorManager.getSurvivorByUUID(uuid)).removeDoor(block);
		}
	}

	public void removeTrapdoor(Block block, Player player) {
		if (shelterManager.getShelter(survivorManager.getSurvivorByPlayer(player)).getTrapdoors().contains(block)) {
			removeBlockOwner(block);
			shelterManager.getShelter(survivorManager.getSurvivorByPlayer(player)).removeTrapdoor(block);
		}
	}

	public void removeTrapdoor(Block block, UUID uuid) {
		if (shelterManager.getShelter(survivorManager.getSurvivorByUUID(uuid)).getTrapdoors().contains(block)) {
			removeBlockOwner(block);
			shelterManager.getShelter(survivorManager.getSurvivorByUUID(uuid)).removeTrapdoor(block);
		}
	}

}