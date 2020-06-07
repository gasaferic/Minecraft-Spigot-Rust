package com.gasaferic.events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.shampaggon.crackshot.events.WeaponScopeEvent;

import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;

public class ScopeSniper implements Listener {

	public static Map<Player, ItemStack> elmo = new HashMap<Player, ItemStack>();

	@EventHandler
	public void onPlayerInteractEvent(WeaponScopeEvent e) {
		Player player = e.getPlayer();
		if (player.getInventory().getHelmet() != null) {
			if (e.isZoomIn()) {
				elmo.put(player, player.getInventory().getHelmet());
				PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 3,
						CraftItemStack.asNMSCopy(new ItemStack(Material.PUMPKIN, 1)));
				((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
			} else if (!(e.isZoomIn())) {
				if (elmo.containsKey(player)) {
					PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 3,
							CraftItemStack.asNMSCopy(new ItemStack(elmo.get(player))));
					((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
					elmo.remove(player);
					player.updateInventory();
				} else {
					PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 3,
							CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)));
					((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
					player.updateInventory();
				}
			}
		} else if (player.getInventory().getHelmet() == null) {
			if (e.isZoomIn()) {
				elmo.put(player, new ItemStack(Material.AIR));
				PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 3,
						CraftItemStack.asNMSCopy(new ItemStack(Material.PUMPKIN, 1)));
				((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
			} else if (!(e.isZoomIn())) {
				if (elmo.containsKey(player)) {
					PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 3,
							CraftItemStack.asNMSCopy(new ItemStack(elmo.get(player))));
					((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
					elmo.remove(player);
					player.updateInventory();
				} else {
					PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 3,
							CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)));
					((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
					player.updateInventory();
				}
			}
		}
	}

	@EventHandler
	public void noPumpkinOff(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (elmo.containsKey(player)) {
			if (e.getCurrentItem().getType() == Material.PUMPKIN) {
				e.setCancelled(true);
				player.closeInventory();
			}
		}
	}
}