package com.gasaferic.mobs.nms;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.EnumMonsterType;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

public class EntityRadioactiveZombie extends EntityZombie {

	public EntityRadioactiveZombie(World world) {
		super(world);

		UnsafeList<?> goalB = (UnsafeList<?>) getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
		goalB.clear();
		UnsafeList<?> goalC = (UnsafeList<?>) getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
		goalC.clear();
		UnsafeList<?> targetB = (UnsafeList<?>) getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
		targetB.clear();
		UnsafeList<?> targetC = (UnsafeList<?>) getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
		targetC.clear();

		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
		this.goalSelector.a(2, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
		this.goalSelector.a(3, new PathfinderGoalRandomStroll(this, 1.0D));
		this.goalSelector.a(4, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.goalSelector.a(5, new PathfinderGoalRandomLookaround(this));
		
		this.getBukkitEntity().setCustomName("§cZombie");
		this.getBukkitEntity().setCustomNameVisible(true);
		
		this.n();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void n() {
		this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, true));
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
	}

	public boolean aW() {
		super.aW();
		this.getAttributeInstance(GenericAttributes.maxHealth).setValue(30.0D);
		this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(5.0D);
		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.23000000417232513D);
		return canPickUpLoot;
	}

	public EnumMonsterType getMonsterType() {
		return EnumMonsterType.UNDEFINED;
	}
	
	public Zombie spawn(Location location) {
		World world = ((CraftWorld) location.getWorld()).getHandle();
		EntityRadioactiveZombie customZombie = new EntityRadioactiveZombie(world);
		
		customZombie.setPosition(location.getX(), location.getY(), location.getZ());
		((CraftLivingEntity) customZombie.getBukkitEntity()).setRemoveWhenFarAway(false);
		world.addEntity(customZombie, SpawnReason.NATURAL);
		return (Zombie) customZombie.getBukkitEntity();
		
	}

	public static Object getPrivateField(String fieldName, Class<PathfinderGoalSelector> clazz, Object object) {
		Field field;
		Object o = null;

		try {
			field = clazz.getDeclaredField(fieldName);

			field.setAccessible(true);

			o = field.get(object);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return o;
	}

}