package com.gasaferic.mobs.nms;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Cow;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityCombustByEntityEvent;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.EnchantmentManager;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityCow;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EnumMonsterType;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

public class EntityBear extends EntityCow {

	public EntityBear(World world) {
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

		this.getBukkitEntity().setCustomName("§cOrso");
		this.getBukkitEntity().setCustomNameVisible(true);

		this.n();
		this.a(0.9F, 1.3F);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void n() {
		this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, true));
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
	}

	public boolean aW() {
		super.aW();
		this.getAttributeInstance(GenericAttributes.maxHealth).setValue(25.0D);
		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.23000000417232513D);
		return ageLocked;
	}

	public boolean r(Entity entity) {
		float f = (float) 5.0D;
		int i = 0;

		boolean flag = entity.damageEntity(DamageSource.mobAttack(this), f);

		if (flag) {
			if (i > 0) {
				entity.g((double) (-MathHelper.sin(this.yaw * 3.1415927F / 180.0F) * (float) i * 0.5F), 0.1D,
						(double) (MathHelper.cos(this.yaw * 3.1415927F / 180.0F) * (float) i * 0.5F));
				this.motX *= 0.6D;
				this.motZ *= 0.6D;
			}

			int j = EnchantmentManager.getFireAspectEnchantmentLevel(this);

			if (j > 0) {
				// CraftBukkit start - Call a combust event when somebody hits with a fire
				// enchanted item
				EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent(this.getBukkitEntity(),
						entity.getBukkitEntity(), j * 4);
				org.bukkit.Bukkit.getPluginManager().callEvent(combustEvent);

				if (!combustEvent.isCancelled()) {
					entity.setOnFire(combustEvent.getDuration());
				}
				// CraftBukkit end
			}

			this.a((EntityLiving) this, entity);
		}

		return flag;
	}

	protected String z() {
		return "mob.cow.say";
	}

	protected String bn() {
		return "mob.cow.hurt";
	}

	protected String bo() {
		return "mob.cow.death";
	}

	protected void a(BlockPosition blockposition, Block block) {
		this.makeSound("mob.cow.step", 0.15F, 1.0F);
	}

	protected Item getLoot() {
		return Items.BEEF;
	}

	public EnumMonsterType getMonsterType() {
		return EnumMonsterType.UNDEFINED;
	}

	public Cow spawn(Location location) {
		World world = (World) ((CraftWorld) location.getWorld()).getHandle();
		EntityBear bear = new EntityBear(world);

		bear.setPosition(location.getX(), location.getY(), location.getZ());
		((CraftLivingEntity) bear.getBukkitEntity()).setRemoveWhenFarAway(false);
		world.addEntity(bear, SpawnReason.CUSTOM);
		
		return (Cow) bear.getBukkitEntity();

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

	/*
	 * UnsafeList<?> goalB = (UnsafeList<?>) getPrivateField("b",
	 * PathfinderGoalSelector.class, goalSelector); goalB.clear(); UnsafeList<?>
	 * goalC = (UnsafeList<?>) getPrivateField("c", PathfinderGoalSelector.class,
	 * goalSelector); goalC.clear(); UnsafeList<?> targetB = (UnsafeList<?>)
	 * getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
	 * targetB.clear(); UnsafeList<?> targetC = (UnsafeList<?>) getPrivateField("c",
	 * PathfinderGoalSelector.class, targetSelector); targetC.clear();
	 * 
	 * this.goalSelector.a(0, new PathfinderGoalFloat(this)); this.goalSelector.a(1,
	 * new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
	 * this.goalSelector.a(2, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
	 * this.goalSelector.a(3, new PathfinderGoalRandomStroll(this, 1.0D));
	 * this.goalSelector.a(4, new PathfinderGoalLookAtPlayer(this,
	 * EntityHuman.class, 8.0F)); this.goalSelector.a(5, new
	 * PathfinderGoalRandomLookaround(this));
	 * 
	 * this.getBukkitEntity().setCustomName("§cOrso");
	 * this.getBukkitEntity().setCustomNameVisible(true);
	 * 
	 * Bukkit.getConsoleSender().sendMessage(this.getBukkitEntity().toString());
	 * 
	 * this.n(); this.a(0.9F, 1.3F);
	 */

}