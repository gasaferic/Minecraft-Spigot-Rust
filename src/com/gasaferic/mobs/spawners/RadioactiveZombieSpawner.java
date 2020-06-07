package com.gasaferic.mobs.spawners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import com.gasaferic.main.Main;
import com.gasaferic.mobs.nms.EntityRadioactiveZombie;

import net.minecraft.server.v1_8_R3.World;

public class RadioactiveZombieSpawner implements Listener {

	final int RESPAWN_TIME;

	public RadioactiveZombieSpawner(ArrayList<Location> spawnersLocations, int respawn_time) {
		this.RESPAWN_TIME = respawn_time;
		spawnersLocations.forEach((spawner) -> spawnRadioactiveZombie(spawner));
	}

	@EventHandler
	public void onBearDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Zombie) {
			if (e.getEntity().hasMetadata("spawnLoc")) {
				new BukkitRunnable() {
					@Override
					public void run() {
						spawnRadioactiveZombie(getSpawnLoc(e.getEntity()));
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

	public void spawnRadioactiveZombie(Location spawner) {
		World craftWorld = ((CraftWorld) spawner.getWorld()).getHandle();
		EntityRadioactiveZombie radioactiveZombie = new EntityRadioactiveZombie(craftWorld);
		Zombie zombie = radioactiveZombie.spawn(spawner.getWorld().getHighestBlockAt(spawner).getLocation());
		zombie.setMetadata("spawnLoc", new FixedMetadataValue(Main.getInstance(), spawner.toString()));
		spawner.getWorld().playEffect(spawner, Effect.MOBSPAWNER_FLAMES, 1, 1);
	}

}