package com.gasaferic.mobs.nms;

public class RegisterCustomMobs {

	public static void registerMobs() {

		NMSUtil nmsu = new NMSUtil();
		
		nmsu.registerEntity("Bear", 92, EntityBear.class);
		nmsu.registerEntity("Mutant", 99, EntityMutant.class);
		nmsu.registerEntity("Radioactive Zombie", 54, EntityRadioactiveZombie.class);
	}

}
