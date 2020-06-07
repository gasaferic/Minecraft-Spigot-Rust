package com.gasaferic.events.cureevents;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gasaferic.main.Main;

public class Cura implements Listener {

	private Main plugin = Main.getInstance();

	private String prefix = plugin.getPrefixString("prefix");

	List<Player> cooldown = new ArrayList<Player>();

	@EventHandler
	public void onCureSelf(PlayerInteractEvent e) {

		Player player = (Player) e.getPlayer();

		Inventory playerInventory = player.getInventory();
		ItemStack itemInHand = player.getItemInHand();

		Action action = e.getAction();

		if ((itemInHand.getType().equals(Material.PAPER))) {
			if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
				if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) {
					return;
				}
				if (!cooldown.contains(player)) {
					if (player.getHealth() < 20.0) {
						if (itemInHand.getAmount() > 1) {

							addHealth(player);

							itemInHand.setAmount(itemInHand.getAmount() - 1);

							player.sendMessage(prefix + "§6Ti sei curato");

							cooldown.add(player);

							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {
									cooldown.remove(player);
								}
							}, 300L);
						} else if (itemInHand.getAmount() == 1) {

							addHealth(player);

							playerInventory.removeItem(itemInHand);

							player.sendMessage(prefix + "§6Ti sei curato");

							cooldown.add(player);

							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

								public void run() {
									cooldown.remove(player);
								}

							}, 300L);
						}
					} else if (player.getHealth() == 20.0) {
						player.sendMessage(prefix + "§6Non hai bisogno di cure, la tua salute è al massimo!");
					}
				} else if (cooldown.contains(player)) {
					player.sendMessage(prefix + "§6Devi aspettare prima di poterti curare di nuovo.");
				}
			}
		}
	}

	@EventHandler
	public void onCureOthers(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {

			Player damager = (Player) e.getDamager();
			Player damagee = (Player) e.getEntity();

			Inventory playerInventory = damager.getInventory();
			ItemStack itemInHand = damager.getItemInHand();

			if ((itemInHand.getType().equals(Material.PAPER))) {
				if (damager.getGameMode().equals(GameMode.CREATIVE)
						|| damager.getGameMode().equals(GameMode.SPECTATOR)) {
					return;
				}
				if (!cooldown.contains(damager)) {
					if (damagee.getHealth() < 20.0) {
						if (itemInHand.getAmount() > 1) {

							addHealth(damagee);

							itemInHand.setAmount(itemInHand.getAmount() - 1);

							damager.sendMessage(prefix + "§6Hai curato §a" + damagee.getName() + "§6.");
							damagee.sendMessage(prefix + "§a" + damager.getName() + " §6ti ha curato.");

							cooldown.add(damager);

							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {
									cooldown.remove(damager);
								}
							}, 300L);
						} else if (itemInHand.getAmount() == 1) {

							addHealth(damagee);

							playerInventory.removeItem(itemInHand);

							damager.sendMessage(prefix + "§6Hai curato §a" + damagee.getName() + "§6.");
							damagee.sendMessage(prefix + "§a" + damager.getName() + " §6ti ha curato.");

							cooldown.add(damager);

							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

								public void run() {
									cooldown.remove(damager);
								}

							}, 300L);
						}
					} else if (damagee.getHealth() == 20.0) {
						damager.sendMessage(prefix + "§a" + damagee.getName() + " §6non ha bisogno di cure, la sua salute è al massimo!");
					}
				} else if (cooldown.contains(damager)) {
					damager.sendMessage(prefix + "§6Devi aspettare prima di poter curare di nuovo un altro player!");
				}
				e.setCancelled(true);
			}
		}
	}

	public void addHealth(Player player) {

		double missingHealth = 20.0 - player.getHealth();

		Bukkit.getConsoleSender().sendMessage("Missing Health " + missingHealth);

		if (player.getHealth() <= 16.0) {
			player.setHealth(player.getHealth() + 4.0);
		} else if (player.getHealth() > 16.0) {
			player.setHealth(20.0);
		}

	}
}
