package com.gasaferic.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gasaferic.areaprotection.api.AreaProtectionAPI;
import com.gasaferic.areaprotection.managers.AreaManager;
import com.gasaferic.commands.AddRadzone;
import com.gasaferic.commands.Reboot;
import com.gasaferic.commands.ReloadConfig;
import com.gasaferic.commands.SetupMode;
import com.gasaferic.database.MySQL;
import com.gasaferic.events.CampfireEvents;
import com.gasaferic.events.CloseAirdropEvent;
import com.gasaferic.events.CombatLogQuit;
import com.gasaferic.events.Drink;
import com.gasaferic.events.EquipParachute;
import com.gasaferic.events.FixAnvil;
import com.gasaferic.events.OpenAirdrop;
import com.gasaferic.events.OpenChestEvent;
import com.gasaferic.events.OpenSafe;
import com.gasaferic.events.PlayerSurvivorRegistering;
import com.gasaferic.events.RadiazioniEvent;
import com.gasaferic.events.RespawnEventNoBed;
import com.gasaferic.events.SchematicLearn;
import com.gasaferic.events.ScopeSniper;
import com.gasaferic.events.SetBedSpawn;
import com.gasaferic.events.buildevents.AntiBreakDoors;
import com.gasaferic.events.buildevents.AntiBuildBlocks;
import com.gasaferic.events.buildevents.AntiBuildDoors;
import com.gasaferic.events.buildevents.Build;
import com.gasaferic.events.buildevents.ChangeBarrierDrop;
import com.gasaferic.events.buildevents.CraftingFurnaceTemp;
import com.gasaferic.events.buildevents.DoorPlaceEvents;
import com.gasaferic.events.buildevents.NoPhysics;
import com.gasaferic.events.buildevents.PlaceGeneratore;
import com.gasaferic.events.buildevents.PlaceMobSpawnerEvent;
import com.gasaferic.events.buildmodes.onPlaceBeacon;
import com.gasaferic.events.buildmodes.onPlaceChest;
import com.gasaferic.events.cureevents.Cura;
import com.gasaferic.events.cureevents.Frattura;
import com.gasaferic.events.cureevents.Morfina;
import com.gasaferic.events.dooropenandclose.OpenIronDoor;
import com.gasaferic.events.dooropenandclose.OpenIronTrapdoor;
import com.gasaferic.events.dooropenandclose.OpenWoodDoor;
import com.gasaferic.events.dooropenandclose.OpenWoodTrapdoor;
import com.gasaferic.events.eventsmercanti.RightClickVillager;
import com.gasaferic.events.eventsmercanti.mercantesaccheggio.MercanteSaccheggio;
import com.gasaferic.events.eventsmercanti.mercantesaccheggio.MercanteSaccheggioEng;
import com.gasaferic.events.menusevents.InfoRifugio;
import com.gasaferic.events.menusevents.OpenMenuCasa;
import com.gasaferic.events.menusevents.abbandona.AbbandonaRifugio;
import com.gasaferic.events.menusevents.abbandona.AbbandonaRifugioAnnulla;
import com.gasaferic.events.menusevents.abbandona.AbbandonaRifugioConferma;
import com.gasaferic.events.menusevents.aggiungiplayer.AggiungiPlayer;
import com.gasaferic.events.menusevents.aggiungiplayer.AggiungiPlayerEvent;
import com.gasaferic.events.menusevents.build.Bunker;
import com.gasaferic.events.menusevents.build.CostruisciPiano;
import com.gasaferic.events.menusevents.build.CostruisciPianoAdmin;
import com.gasaferic.events.menusevents.rimuovi.RimuoviRifugio;
import com.gasaferic.events.menusevents.rimuovi.RimuoviRifugioAnnulla;
import com.gasaferic.events.menusevents.rimuovi.RimuoviRifugioConferma;
import com.gasaferic.events.menusevents.rimuoviplayer.RimuoviPlayer;
import com.gasaferic.events.menusevents.rimuoviplayer.RimuoviPlayerEvent;
import com.gasaferic.events.raid.PlaceBarriera;
import com.gasaferic.events.raid.Raid;
import com.gasaferic.events.teams.HitBlood;
import com.gasaferic.events.teams.ShootArrow;
import com.gasaferic.events.teams.TeamArrow;
import com.gasaferic.events.teams.TeamHit;
import com.gasaferic.events.teams.TeamShoot;
import com.gasaferic.events.zainoevents.Zaino;
import com.gasaferic.events.zainoevents.ZainoDropEvent;
import com.gasaferic.managers.AirDropManager;
import com.gasaferic.managers.PlayersRegionManager;
import com.gasaferic.managers.RadChestManager;
import com.gasaferic.managers.ShelterManager;
import com.gasaferic.managers.SurvivorManager;
import com.gasaferic.managers.TeamManager;
import com.gasaferic.mobs.VillagerDamageEvent;
import com.gasaferic.mobs.drops.CustomMobDrops;
import com.gasaferic.mobs.nms.RegisterCustomMobs;
import com.gasaferic.mobs.spawners.BearSpawner;
import com.gasaferic.mobs.spawners.HogSpawner;
import com.gasaferic.mobs.spawners.MutantSpawner;
import com.gasaferic.mobs.spawners.RadioactiveZombieSpawner;
import com.gasaferic.model.Survivor;
import com.gasaferic.resourcespawner.ResourceSpawner;
import com.gasaferic.resourcespawner.ResourceSpawnerUtils;
import com.gasaferic.resourcespawner.ResourceTreeSpawner;

import net.minecraft.server.v1_8_R3.GenericAttributes;

public class Main extends JavaPlugin implements Listener {

	private static Main instance;

	public static ArrayList<ResourceSpawner> resourceSpawners = new ArrayList<ResourceSpawner>();
	public static ArrayList<ResourceTreeSpawner> resourceTreeSpawners = new ArrayList<ResourceTreeSpawner>();

	public static ArrayList<UUID> playersUUID = new ArrayList<UUID>();

	public static boolean mysqlConnected = false;

	private static MySQL mySQL;
	private static SurvivorManager survivorManager;
	private static TeamManager teamManager;
	private static MetadataManager metadataManager;
	private static ShelterManager shelterManager;
	private static PlayersRegionManager playersRegionManager;
	private static AirDropManager airDropManager;
	private static RadChestManager radChestManager;
	private static AreaProtectionAPI areaProtectionAPI;

	Connection connection;
	String host, database, username, password;
	int port;

	public void onEnable() {

		instance = this;

		PluginDescriptionFile pdfFile = getDescription();

		reloadConfig();

		try {
			mySQL = new MySQL(getConfig().getString("MySQL.address"), getConfig().getString("MySQL.database"),
					getConfig().getString("MySQL.username"), getConfig().getString("MySQL.password"),
					getConfig().getInt("MySQL.port"), pdfFile.getName().toLowerCase());
			mysqlConnected = true;
		} catch (ClassNotFoundException | SQLException e) {
			Collection<? extends Player> onlinePlayers = getServer().getOnlinePlayers();
			if (onlinePlayers.size() > 0) {
				for (Player player : onlinePlayers) {
					player.kickPlayer(getPrefixString("prefix") + "§6Il database non è connesso!");
				}
			}
			e.printStackTrace();
		}

		registerEvent(this);

		if (mysqlConnected) {

			survivorManager = new SurvivorManager();
			teamManager = new TeamManager();
			shelterManager = new ShelterManager();
			metadataManager = new MetadataManager();
			areaProtectionAPI = new AreaProtectionAPI();
			playersRegionManager = new PlayersRegionManager();

			getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.IRON_INGOT, 1), Material.BOWL));
			getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.IRON_INGOT, 2), Material.IRON_ORE));
			getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.SULPHUR, 4), Material.GOLD_ORE));

			prepareRust();

			registerEvents();
			registerCommands();

			registerOnlineSurvivors();
			registerOfflineSurvivors();
			registerShelters();

			PluginManager pm = getServer().getPluginManager();
			
			clearCustomMobs();
			
			pm.registerEvents(
					new BearSpawner(mySQL.getMobSpawners("bear"), getConfig().getInt("bear_spawners_respawn_time")),
					this);
			pm.registerEvents(new MutantSpawner(mySQL.getMobSpawners("mutant"),
					getConfig().getInt("mutant_spawners_respawn_time")), this);
			pm.registerEvents(new RadioactiveZombieSpawner(mySQL.getMobSpawners("radiozombie"),
					getConfig().getInt("radiozombie_spawners_respawn_time")), this);
			pm.registerEvents(
					new HogSpawner(mySQL.getMobSpawners("hog"), getConfig().getInt("hog_spawners_respawn_time")), this);

			sheltersStoreRepeatingTask();

			Bukkit.getConsoleSender().sendMessage("§8[§9§l" + pdfFile.getName() + "§8]§r"
					+ " §a§lhas been enabled on version " + pdfFile.getVersion());

		}

	}

	public void onDisable() {

		PluginDescriptionFile pdfFile = getDescription();

		if (mysqlConnected) {

			survivorManager.disableSetupMode();

			saveGenBlocks();

			storeShelters();
			
			saveConfig();

		}

		Bukkit.getConsoleSender().sendMessage("§8[§9§l" + pdfFile.getName() + "§8]§r" + " §c§lhas been disabled");
	}

	public void registerEvents() {

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerSurvivorRegistering(), this);

		pm.registerEvents(new PlaceMobSpawnerEvent(), this);

		// Sistema Casa
		pm.registerEvents(new Build(), this);
		pm.registerEvents(new DoorPlaceEvents(), this);
		pm.registerEvents(new OpenWoodDoor(), this);
		pm.registerEvents(new OpenWoodTrapdoor(), this);
		pm.registerEvents(new OpenIronDoor(), this);
		pm.registerEvents(new OpenIronTrapdoor(), this);
		pm.registerEvents(new NoPhysics(), this);
		pm.registerEvents(new OpenMenuCasa(), this);
		pm.registerEvents(new AggiungiPlayer(), this);
		pm.registerEvents(new AggiungiPlayerEvent(), this);
		pm.registerEvents(new RimuoviPlayer(), this);
		pm.registerEvents(new RimuoviPlayerEvent(), this);
		pm.registerEvents(new AbbandonaRifugio(), this);
		pm.registerEvents(new AbbandonaRifugioAnnulla(), this);
		pm.registerEvents(new AbbandonaRifugioConferma(), this);
		pm.registerEvents(new RimuoviRifugio(), this);
		pm.registerEvents(new RimuoviRifugioAnnulla(), this);
		pm.registerEvents(new RimuoviRifugioConferma(), this);
		pm.registerEvents(new Bunker(), this);
		pm.registerEvents(new CostruisciPiano(), this);
		pm.registerEvents(new CostruisciPianoAdmin(), this);
		pm.registerEvents(new InfoRifugio(), this);
		pm.registerEvents(new Raid(), this);
		pm.registerEvents(new RespawnEventNoBed(), this);
		pm.registerEvents(new SetBedSpawn(), this);

		// pm.registerEvents(new PlayerJoinMakeFile(), this); YML Version

		pm.registerEvents(new CraftingFurnaceTemp(), this);
		pm.registerEvents(new OpenSafe(), this);
		pm.registerEvents(new FixAnvil(), this);
		pm.registerEvents(new Zaino(), this);
		pm.registerEvents(new ZainoDropEvent(), this);
		pm.registerEvents(new Cura(), this);
		pm.registerEvents(new TeamHit(), this);
		pm.registerEvents(new TeamShoot(), this);
		pm.registerEvents(new TeamArrow(), this);
		pm.registerEvents(new Frattura(), this);
		pm.registerEvents(new Morfina(), this);
		pm.registerEvents(new SchematicLearn(), this);
		pm.registerEvents(new ScopeSniper(), this);
		pm.registerEvents(new AntiBuildDoors(), this);
		pm.registerEvents(new AntiBreakDoors(), this);
		pm.registerEvents(new AntiBuildBlocks(), this);
		pm.registerEvents(new HitBlood(), this);
		pm.registerEvents(new ShootArrow(), this);
		pm.registerEvents(new onPlaceBeacon(), this);
		pm.registerEvents(new onPlaceChest(), this);
		pm.registerEvents(new EquipParachute(), this);
		pm.registerEvents(new OpenAirdrop(), this);
		pm.registerEvents(new Drink(), this);
		pm.registerEvents(new CombatLogQuit(), this);
		pm.registerEvents(new RightClickVillager(), this);
		pm.registerEvents(new MercanteSaccheggio(), this);
		pm.registerEvents(new MercanteSaccheggioEng(), this);
		pm.registerEvents(new CloseAirdropEvent(), this);
		pm.registerEvents(new RadiazioniEvent(), this);
		pm.registerEvents(new PlaceBarriera(), this);
		pm.registerEvents(new ChangeBarrierDrop(), this);
		pm.registerEvents(new OpenChestEvent(), this);
		pm.registerEvents(new CustomMobDrops(), this);
		pm.registerEvents(new CampfireEvents(), this);
		pm.registerEvents(new PlaceGeneratore(), this);
		pm.registerEvents(new VillagerDamageEvent(), this);

	}

	public static void registerEvent(Listener listener) {

		PluginManager pm = getInstance().getServer().getPluginManager();

		pm.registerEvents(listener, getInstance());

	}

	@EventHandler
	public void onPlayerPreLogin(AsyncPlayerPreLoginEvent e) {
		if (!Main.mysqlConnected) {
			e.disallow(Result.KICK_OTHER,
					Main.getInstance().getPrefixString("prefix") + "§6Il database non è connesso!");
		}
	}

	public void registerCommands() {

		getCommand("reboot").setExecutor(new Reboot());
		getCommand("reloadconfig").setExecutor(new ReloadConfig());
		getCommand("setupmode").setExecutor(new SetupMode());
		getCommand("addradzone").setExecutor(new AddRadzone());

	}

	public ArrayList<Location> getSpawners(String spawnerType) {
		ArrayList<Location> locations = new ArrayList<Location>();
		for (String locationString : getConfig().getStringList(spawnerType)) {
			locations.add(fromString(locationString));
		}
		return locations;
	}

	public static SurvivorManager getSurvivorManager() {
		return survivorManager;
	}

	public static TeamManager getTeamManager() {
		return teamManager;
	}

	public static MetadataManager getMetadataManager() {
		return metadataManager;
	}

	public static ShelterManager getShelterManager() {
		return shelterManager;
	}

	public static AreaProtectionAPI getAreaProtectionAPI() {
		return areaProtectionAPI;
	}

	public static AreaManager getAreaManager() {
		return areaProtectionAPI.getAreaManager();
	}

	public static PlayersRegionManager getPlayersRegionManager() {
		return playersRegionManager;
	}

	public static MySQL getMySQL() {
		return mySQL;
	}

	public static AirDropManager getAirDropManager() {
		return airDropManager;
	}

	public static RadChestManager getRadChestManager() {
		return radChestManager;
	}

	public void clearCustomMobs() {
		for (Entity entity : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			if (entity instanceof Zombie) {
				entity.remove();
			} else if (entity instanceof IronGolem) {
				entity.remove();
			} else if (entity instanceof Cow) {
				entity.remove();
			} else if (entity instanceof Pig) {
				entity.remove();
			}
		}
	}

	public void registerOnlineSurvivors() {
		if (Bukkit.getOnlinePlayers().size() > 0) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				Survivor survivor = new Survivor(player);
				survivorManager.registerSurvivor(survivor);
				if (!mySQL.survivorExists(player)) {
					mySQL.addSurvivor(survivor);
				}
			}
		}
	}

	public void registerOfflineSurvivors() {
		survivorManager.loadSurvivorsFromArrayList(mySQL.getSurvivors());
	}

	public void registerShelters() {
		for (Survivor survivor : survivorManager.getSurvivors()) {
			shelterManager.loadFromDatabase(survivor.getUniqueId());
		}
	}

	public void storeShelters() {
		for (Survivor shelterOwner : shelterManager.getShelters().keySet()) {
			shelterManager.exportShelter(shelterManager.getShelter(shelterOwner));
		}
	}

	public void sheltersStoreRepeatingTask() {
		new BukkitRunnable() {
			@Override
			public void run() {
				System.out.println("Storing shelters to the database...");
				storeShelters();
				System.out.println("Successfully stored shelters to the database.");
			}
		}.runTaskTimer(this, 0, 6000);
	}

	public void loadGenBlocks() {

		for (ResourceSpawner resourceSpawner : mySQL.getResourceSpawners()) {
			registerEvent(resourceSpawner);
			resourceSpawners.add(resourceSpawner);
		}

		Bukkit.getConsoleSender().sendMessage(getPrefixString("prefix") + "§9§lGeneratori caricati con successo.");

		for (ResourceTreeSpawner resourceTreeSpawner : mySQL.getResourceTreeSpawners()) {
			registerEvent(resourceTreeSpawner);
			resourceTreeSpawners.add(resourceTreeSpawner);
		}

		Bukkit.getConsoleSender().sendMessage(getPrefixString("prefix") + "§9§lForeste caricate con successo.");

	}

	public void saveGenBlocks() {

		for (ResourceSpawner resourceSpawner : resourceSpawners) {
			ResourceSpawnerUtils.removeResourceSpawner(resourceSpawner);
		}

		for (ResourceTreeSpawner resourceTreeSpawner : resourceTreeSpawners) {
			ResourceSpawnerUtils.removeResourceTreeSpawner(resourceTreeSpawner);
		}

		Bukkit.getConsoleSender()
				.sendMessage(getPrefixString("prefix") + "§9§lForeste salvate nel config con successo.");

		saveConfig();
	}

	public static Main getInstance() {
		return instance;
	}

	public String getPrefixString(String string) {
		return ChatColor.translateAlternateColorCodes('&', getConfig().getString(string));
	}

	public static Location fromString(String loc) {
		loc = loc.substring(loc.indexOf("{") + 1);
		loc = loc.substring(loc.indexOf("{") + 1);
		String worldName = loc.substring(loc.indexOf("=") + 1, loc.indexOf("}"));
		loc = loc.substring(loc.indexOf(",") + 1);
		String xCoord = loc.substring(loc.indexOf("=") + 1, loc.indexOf(","));
		loc = loc.substring(loc.indexOf(",") + 1);
		String yCoord = loc.substring(loc.indexOf("=") + 1, loc.indexOf(","));
		loc = loc.substring(loc.indexOf(",") + 1);
		String zCoord = loc.substring(loc.indexOf("=") + 1, loc.indexOf(","));
		loc = loc.substring(loc.indexOf(",") + 1);
		String pitch = loc.substring(loc.indexOf("=") + 1, loc.indexOf(","));
		loc = loc.substring(loc.indexOf(",") + 1);
		String yaw = loc.substring(loc.indexOf("=") + 1, loc.indexOf("}"));
		return new Location(Bukkit.getWorld(worldName), Double.parseDouble(xCoord), Double.parseDouble(yCoord),
				Double.parseDouble(zCoord), Float.parseFloat(yaw), Float.parseFloat(pitch));
	}

	public void prepareRust() {

		loadGenBlocks();

		LootsPercentuage.fillLootsList();

		RegisterCustomMobs.registerMobs();

		airDropManager = new AirDropManager(mySQL.getLootResources("airdrop"),
				getConfig().getInt("airdrop_regen_time"));
		airDropManager.start();

		radChestManager = new RadChestManager(mySQL.getLootResources("radchest"),
				getConfig().getInt("radchest_regen_time"));
		radChestManager.start();

		for (Entity entity : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			if (entity instanceof Villager) {
				Villager villager = (Villager) entity;
				if (villager.getCustomName() != null) {
					if (villager.getCustomName().equalsIgnoreCase("§fMercante Saccheggio")
							|| villager.getCustomName().equalsIgnoreCase("§fMercante Cibo")
							|| villager.getCustomName().equalsIgnoreCase("§fMercante Munizioni")) {
						villager.remove();
						villager.eject();
					}
				}
			}
		}

		spawnVillager(getConfig().getString("MercanteSaccheggio"), "§fMercante Saccheggio");
		spawnVillager(getConfig().getString("MercanteCibo"), "§fMercante Cibo");
		spawnVillager(getConfig().getString("MercanteMunizioni"), "§fMercante Munizioni");

	}

	public void spawnVillager(String locstring, String name) {
		Location location = fromString(locstring);
		Villager v = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
		((CraftVillager) v).getHandle().getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0);
		v.setProfession(Profession.LIBRARIAN);
		v.setCustomName(name);
		v.setCustomNameVisible(true);

	}

}