package com.gasaferic.mobs.nms;

import java.lang.reflect.Field;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import net.minecraft.server.v1_8_R3.Entity;

public enum EntityTypes {

	BEAR("Cow", 92, EntityBear.class), MUTANT("Iron Golem", 99, EntityMutant.class),
	RADIOACTIVE_ZOMBIE("Zombie", 52, EntityRadioactiveZombie.class);

	private EntityTypes(String name, int id, Class<? extends Entity> custom) {
		addToMaps(custom, name, id);
	}

	public static Object getPrivateField(String fieldName, Class<?> clazz, Object object) {
        Field field;
        Object o = null;

        try {
            field = clazz.getDeclaredField(fieldName);

            field.setAccessible(true);

            o = field.get(object);
        } catch(NoSuchFieldException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }

        return o;
    }
   
    public static void spawnEntity(Entity entity, Location loc) {
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftWorld)loc.getWorld()).getHandle().addEntity(entity);
    }

    @SuppressWarnings("unchecked")
    private static void addToMaps(Class<?> clazz, String name, int id) {
        ((Map<Class<?>, String>)getPrivateField("d", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, name);
        ((Map<Class<?>, Integer>)getPrivateField("f", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, Integer.valueOf(id));
    }

}