package com.gasaferic.mobs.spawners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import com.gasaferic.main.Main;

public class HogSpawner implements Listener {

	final int RESPAWN_TIME;

	public HogSpawner(ArrayList<Location> spawnersLocations, int respawn_time) {
		this.RESPAWN_TIME = respawn_time;
		spawnersLocations.forEach((spawner) -> spawnPig(spawner, false));
	}

	@EventHandler
	public void onBearDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Pig) {
			if (e.getEntity().hasMetadata("spawnLoc")) {
				e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(),
						new ItemStack(Material.FERMENTED_SPIDER_EYE, new Random().nextInt(3)));
				new BukkitRunnable() {
					@Override
					public void run() {
						spawnPig(getSpawnLoc(e.getEntity()), true);
					}
				}.runTaskLater(Main.getInstance(), RESPAWN_TIME * 20L);
			}
		}
	}

	public Location getSpawnLoc(Entity entity) {
		Location location = null;
		List<MetadataValue> metas = entity.getMetadata("spawnLoc");
		for (MetadataValue meta : metas) {
			if (meta.getOwningPlugin().getName().equals(Main.getInstance().getName())) {
				location = Main.fromString(meta.asString());
			}
		}
		return location;
	}

	public void spawnPig(Location spawner, boolean respawn) {
		if (!respawn) {
			for (int i = 0; i < 3; i++) {
				spawner.getWorld().spawnEntity(spawner, EntityType.PIG).setMetadata("spawnLoc",
						new FixedMetadataValue(Main.getInstance(), spawner.toString()));
				spawner.getWorld().playEffect(spawner, Effect.MOBSPAWNER_FLAMES, 1, 1);
			}
		} else {
			spawner.getWorld().spawnEntity(spawner, EntityType.PIG).setMetadata("spawnLoc",
					new FixedMetadataValue(Main.getInstance(), spawner.toString()));
			spawner.getWorld().playEffect(spawner, Effect.MOBSPAWNER_FLAMES, 1, 1);
		}
	}

}