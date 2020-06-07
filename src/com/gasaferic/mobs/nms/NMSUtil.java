package com.gasaferic.mobs.nms;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.EntityInsentient;

public class NMSUtil {

	@SuppressWarnings("unchecked")
	public void registerEntity(String name, int id, Class<? extends EntityInsentient> customClass) {
		try {
			((Map<Class<?>, String>) getPrivateField(EntityTypes.class, "d")).put(customClass, name);
			((Map<Class<?>, Integer>) getPrivateField(EntityTypes.class, "f")).put(customClass, id);
		} catch (Exception ex) {
			// unable to create for some reason, check the stack trace
			ex.printStackTrace();
		}
	}

	private static Object getPrivateField(Class<?> clazz, String fieldName) throws Exception {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(null);
	}

}
