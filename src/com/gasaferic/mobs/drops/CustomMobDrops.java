package com.gasaferic.mobs.drops;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.RandomLoots;

public class CustomMobDrops implements Listener {

	@EventHandler
	public void onCustomMobDeath(EntityDeathEvent e) {
		Entity entity = e.getEntity();
		if (entity instanceof Cow) {
			if (entity.getName().equals("§cOrso")) {
				e.getDrops().clear();
				if (entity.getLastDamageCause().getCause().equals(DamageCause.FIRE)) {
					Random random = new Random();
					int quantity = random.nextInt((3 - 1) + 1) + 1;
					entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Material.COOKED_BEEF, quantity));
				} else if (entity.getLastDamageCause().getCause().equals(DamageCause.ENTITY_ATTACK)) {
					if (((LivingEntity) entity).getKiller() instanceof Player) {
						Player player = ((LivingEntity) entity).getKiller();
						if (!player.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS)) {
							if (player.getItemInHand().containsEnchantment(Enchantment.FIRE_ASPECT)) {
								Random random = new Random();
								int quantity = random.nextInt((3 - 1) + 1) + 1;
								entity.getWorld().dropItem(entity.getLocation(),
										new ItemStack(Material.COOKED_BEEF, quantity));
							} else if (!player.getItemInHand().containsEnchantment(Enchantment.FIRE_ASPECT)) {
								Random random = new Random();
								int quantity = random.nextInt((3 - 1) + 1) + 1;
								entity.getWorld().dropItem(entity.getLocation(),
										new ItemStack(Material.RAW_BEEF, quantity));
							}
						} else if (player.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS)) {
							Random random = new Random();
							int quantity = (random.nextInt((2 - 1) + 1) + 1)
									* player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
							if (player.getItemInHand().containsEnchantment(Enchantment.FIRE_ASPECT)) {
								entity.getWorld().dropItem(entity.getLocation(),
										new ItemStack(Material.COOKED_BEEF, quantity));
							} else if (!player.getItemInHand().containsEnchantment(Enchantment.FIRE_ASPECT)) {
								entity.getWorld().dropItem(entity.getLocation(),
										new ItemStack(Material.RAW_BEEF, quantity));
							}
						}
					}
				}
			}
		} else if (entity instanceof IronGolem) {
			if (entity.getName().equals("§cMutante")) {
				e.getDrops().clear();
				entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Material.STONE_SWORD, 1));
			}
		} else if (entity instanceof Zombie) {
			if (entity.getName().equals("§cZombie")) {
				e.getDrops().clear();
				RandomLoots loots = new RandomLoots();
				ItemStack loot = loots.getRandomLoot();
				if (!loot.getType().equals(Material.AIR)) {
					entity.getWorld().dropItem(entity.getLocation(), loot);
				}
			}
		}
	}

}