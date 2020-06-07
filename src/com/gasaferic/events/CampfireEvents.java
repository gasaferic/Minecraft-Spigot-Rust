package com.gasaferic.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class CampfireEvents implements Listener {

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		ItemStack itemStack = (ItemStack) e.getItemDrop().getItemStack();
		Player player = e.getPlayer();
		if (itemStack.getType().equals(Material.RAW_BEEF) || itemStack.getType().equals(Material.PORK)
				|| itemStack.getType().equals(Material.RAW_CHICKEN)) {
			e.getItemDrop().setCustomName(itemStack.getType().toString() + player.getName());
		}
	}

	@EventHandler
	public void onDroppedItemFire(EntityDamageEvent e) {
		if (e.getEntity().getType().equals(EntityType.DROPPED_ITEM) && e.getCause().equals(DamageCause.FIRE_TICK)
				|| e.getCause().equals(DamageCause.FIRE)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemFire(EntityCombustEvent e) {
		Entity entity = e.getEntity();
		if (entity instanceof Item) {
			ItemStack itemStack = ((Item) entity).getItemStack();
			if (itemStack.getType().equals(Material.RAW_BEEF)) {
				String playerName = entity.getName().substring(itemStack.getType().toString().length());
				Player playerWhoDropped = Bukkit.getPlayer(playerName);
				entity.remove();
				playerWhoDropped.getWorld().dropItemNaturally(playerWhoDropped.getLocation(),
						new ItemStack(Material.COOKED_BEEF, itemStack.getAmount()));
			} else if (itemStack.getType().equals(Material.PORK)) {
				String playerName = entity.getName().substring(itemStack.getType().toString().length());
				Player playerWhoDropped = Bukkit.getPlayer(playerName);
				entity.remove();
				playerWhoDropped.getWorld().dropItemNaturally(playerWhoDropped.getLocation(),
						new ItemStack(Material.GRILLED_PORK, itemStack.getAmount()));
			} else if (itemStack.getType().equals(Material.RAW_CHICKEN)) {
				String playerName = entity.getName().substring(itemStack.getType().toString().length());
				Player playerWhoDropped = Bukkit.getPlayer(playerName);
				entity.remove();
				playerWhoDropped.getWorld().dropItemNaturally(playerWhoDropped.getLocation(),
						new ItemStack(Material.COOKED_CHICKEN, itemStack.getAmount()));
			}
		}
		
		if (entity instanceof Zombie) {
			e.setCancelled(true);
		}
	}

}