package com.gasaferic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.gasaferic.main.Main;
import com.gasaferic.model.Shelter;
import com.gasaferic.model.Survivor;
import com.gasaferic.resourcespawner.ResourceSpawner;
import com.gasaferic.resourcespawner.ResourceTreeSpawner;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQL {

	public static Main plugin = Main.getInstance();

	private Connection connection;;

	private String host;
	private String database;
	private String username;
	private String password;
	private int port;
	private String tablePrefix;

	public MySQL(String host, String database, String username, String password, int port, String tablePrefix)
			throws ClassNotFoundException, SQLException {
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
		this.port = port;
		this.tablePrefix = tablePrefix;
		connect();
	}

	public void connect() throws ClassNotFoundException, SQLException {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(host);
		dataSource.setPort(port);
		dataSource.setDatabaseName(database);
		dataSource.setUser(username);
		dataSource.setPassword(password);

		connection = dataSource.getConnection();

		createTable();

		System.out.println("Connessione con il database stabilita con successo.");
	}

	public Connection getConnection() {
		return connection;
	}

	public void createTable() {
		try {

			PreparedStatement survivorsTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + tablePrefix
					+ "_survivors` (`uuid` VARCHAR(36), `name` VARCHAR(16), `lang` VARCHAR(4) NOT NULL DEFAULT 'eng', `shelter` TINYINT(1) NOT NULL DEFAULT '0', PRIMARY KEY `uuid` (`uuid`))");
			survivorsTable.executeUpdate();

			PreparedStatement sheltersTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + tablePrefix
					+ "_shelters` (`uuid` VARCHAR(36), `console_location` VARCHAR(255), `floor` TINYINT(1) NOT NULL DEFAULT '1', `bunker` TINYINT(1) NOT NULL DEFAULT '0', PRIMARY KEY `uuid` (`uuid`))");
			sheltersTable.executeUpdate();

			PreparedStatement teamsTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + tablePrefix
					+ "_teams` (`uuid` VARCHAR(36), `teamer_uuid` VARCHAR(36), UNIQUE KEY `teamer_uuid` (`teamer_uuid`))");
			teamsTable.executeUpdate();

			PreparedStatement sheltersProtection = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `"
					+ tablePrefix
					+ "_shelters_protection` (`uuid` VARCHAR(36), `prot_type` VARCHAR(8), `world` VARCHAR(16), `x` FLOAT, `y` FLOAT, `z` FLOAT, UNIQUE KEY `Coordinate` (`world`,`x`,`y`,`z`))");
			sheltersProtection.executeUpdate();

			PreparedStatement lootingResources = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `"
					+ tablePrefix
					+ "_looting_resources` (`loot_type` VARCHAR(8), `world` VARCHAR(16), `x` FLOAT, `y` FLOAT, `z` FLOAT, UNIQUE KEY `Coordinate` (`world`,`x`,`y`,`z`))");
			lootingResources.executeUpdate();

			PreparedStatement resourceSpawners = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `"
					+ tablePrefix
					+ "_resource_spawners` (`gen_type` VARCHAR(8), `world` VARCHAR(16), `x` FLOAT, `y` FLOAT, `z` FLOAT, UNIQUE KEY `Coordinate` (`world`,`x`,`y`,`z`))");
			resourceSpawners.executeUpdate();

			PreparedStatement mobSpawners = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + tablePrefix
					+ "_mob_spawners` (`spawner_type` VARCHAR(16), `world` VARCHAR(16), `x` FLOAT, `y` FLOAT, `z` FLOAT, UNIQUE KEY `Coordinate` (`world`,`x`,`y`,`z`))");
			mobSpawners.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (connection != null || !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean hasShelter(Survivor survivor) {
		try {
			PreparedStatement stat;
			stat = connection.prepareStatement("SELECT shelter FROM " + tablePrefix + "_survivors WHERE uuid = ?");

			stat.setString(1, survivor.getUniqueId().toString());
			ResultSet result = stat.executeQuery();

			if (result.next()) {
				boolean hasShelter = result.getBoolean("shelter");
				if (hasShelter) {
					return true;
				} else {
					return false;
				}
			} else if (!result.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public void addShelter(Shelter shelter) {
		try {
			PreparedStatement shelterStat = connection.prepareStatement("INSERT IGNORE INTO " + tablePrefix
					+ "_shelters(uuid,console_location,floor,bunker) VALUES (?,?,?,?)");
			shelterStat.setString(1, shelter.getShelterOwner().getUniqueId().toString());
			shelterStat.setString(2, shelter.getConsoleBlock().getLocation().toString());
			shelterStat.setInt(3, shelter.getBuiltFloors());
			shelterStat.setBoolean(4, shelter.hasBuiltBunker());
			shelterStat.executeUpdate();

			PreparedStatement survivorStat = connection
					.prepareStatement("UPDATE " + tablePrefix + "_survivors SET shelter = ? WHERE uuid = ?");
			survivorStat.setBoolean(1, true);
			survivorStat.setString(2, shelter.getShelterOwner().getUniqueId().toString());
			survivorStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeShelter(Survivor survivor) {
		try {
			PreparedStatement shelterRemovingStat = connection
					.prepareStatement("DELETE FROM " + tablePrefix + "_shelters WHERE uuid = ?");
			shelterRemovingStat.setString(1, survivor.getUniqueId().toString());
			shelterRemovingStat.executeUpdate();

			PreparedStatement protectionRemovingStat = connection
					.prepareStatement("DELETE FROM " + tablePrefix + "_shelters_protection WHERE uuid = ?");
			protectionRemovingStat.setString(1, survivor.getUniqueId().toString());
			protectionRemovingStat.executeUpdate();

			PreparedStatement teamRemovingStat = connection
					.prepareStatement("DELETE FROM " + tablePrefix + "_teams WHERE uuid = ?");
			teamRemovingStat.setString(1, survivor.getUniqueId().toString());
			teamRemovingStat.executeUpdate();

			PreparedStatement survivorStat = connection
					.prepareStatement("UPDATE " + tablePrefix + "_survivors SET shelter = ? WHERE uuid = ?");
			survivorStat.setBoolean(1, false);
			survivorStat.setString(2, survivor.getUniqueId().toString());
			survivorStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getFloor(Survivor survivor) {

		try {
			PreparedStatement stat = connection
					.prepareStatement("SELECT floor FROM " + tablePrefix + "_shelters WHERE uuid = ?");
			stat.setString(1, survivor.getUniqueId().toString());
			ResultSet result = stat.executeQuery();

			if (result.next()) {
				int floor = result.getInt("floor");
				if (floor != 0) {
					return floor;
				} else {
					return 0;
				}
			} else if (!result.next()) {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

	public boolean hasBunker(Survivor survivor) {

		try {
			PreparedStatement stat = connection
					.prepareStatement("SELECT bunker FROM " + tablePrefix + "_shelters WHERE uuid = ?");
			stat.setString(1, survivor.getUniqueId().toString());
			ResultSet result = stat.executeQuery();

			if (result.next()) {
				boolean hasBunker = result.getBoolean("bunker");
				if (hasBunker) {
					return true;
				} else {
					return false;
				}
			} else if (!result.next()) {
				return false;
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		return false;

	}
	
	public ArrayList<Location> getLootResources(String lootType) {

		ArrayList<Location> lootResources = new ArrayList<Location>();

		try {
			PreparedStatement stat = connection.prepareStatement("SELECT * FROM " + tablePrefix + "_looting_resources WHERE loot_type = ?");
			stat.setString(1, lootType);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				Location lootResourceLoc = new Location(Bukkit.getWorld(result.getString("world")),
						result.getInt("x"), result.getInt("y"), result.getInt("z"));
				if (lootResourceLoc != null) {
					lootResources.add(lootResourceLoc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lootResources;

	}

	public void saveLootResource(Location location, String lootType) {
		try {
			PreparedStatement stat = connection.prepareStatement(
					"INSERT IGNORE INTO " + tablePrefix + "_looting_resources(loot_type,world,x,y,z) VALUES (?,?,?,?,?)");
			stat.setString(1, lootType);
			stat.setString(2, location.getWorld().getName());
			stat.setFloat(3, location.getBlockX());
			stat.setFloat(4, location.getBlockY());
			stat.setFloat(5, location.getBlockZ());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteLootResource(Location location, String lootType) {
		try {
			PreparedStatement stat = connection.prepareStatement(
					"DELETE FROM " + tablePrefix + "_looting_resources WHERE loot_type = ? AND world = ? AND x = ? AND y = ? AND z = ?");
			stat.setString(1, lootType);
			stat.setString(2, location.getWorld().getName());
			stat.setFloat(3, location.getBlockX());
			stat.setFloat(4, location.getBlockY());
			stat.setFloat(5, location.getBlockZ());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Location> getMobSpawners(String spawnerType) {

		ArrayList<Location> mobSpawners = new ArrayList<Location>();

		try {
			PreparedStatement stat = connection.prepareStatement("SELECT * FROM " + tablePrefix + "_mob_spawners WHERE spawner_type = ?");
			stat.setString(1, spawnerType);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				Location lootResourceLoc = new Location(Bukkit.getWorld(result.getString("world")),
						result.getInt("x"), result.getInt("y"), result.getInt("z"));
				if (lootResourceLoc != null) {
					mobSpawners.add(lootResourceLoc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mobSpawners;

	}

	public void saveMobSpawner(Location location, String spawnerType) {
		try {
			PreparedStatement stat = connection.prepareStatement(
					"INSERT IGNORE INTO " + tablePrefix + "_mob_spawners(spawner_type,world,x,y,z) VALUES (?,?,?,?,?)");
			stat.setString(1, spawnerType);
			stat.setString(2, location.getWorld().getName());
			stat.setFloat(3, location.getBlockX());
			stat.setFloat(4, location.getBlockY());
			stat.setFloat(5, location.getBlockZ());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ResourceSpawner> getResourceSpawners() {

		ArrayList<ResourceSpawner> resourceSpawners = new ArrayList<ResourceSpawner>();

		try {
			PreparedStatement stat = connection.prepareStatement("SELECT * FROM " + tablePrefix + "_resource_spawners WHERE gen_type = ?");
			stat.setString(1, "ore");
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				Location resSpawnerLocation = new Location(Bukkit.getWorld(result.getString("world")),
						result.getInt("x"), result.getInt("y"), result.getInt("z"));
				if (resSpawnerLocation != null) {
					resourceSpawners.add(new ResourceSpawner(resSpawnerLocation));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resourceSpawners;

	}

	public ArrayList<ResourceTreeSpawner> getResourceTreeSpawners() {

		ArrayList<ResourceTreeSpawner> resourceSpawners = new ArrayList<ResourceTreeSpawner>();

		try {
			PreparedStatement stat = connection.prepareStatement("SELECT * FROM " + tablePrefix + "_resource_spawners WHERE gen_type = ?");
			stat.setString(1, "log");
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				Location resSpawnerLocation = new Location(Bukkit.getWorld(result.getString("world")),
						result.getInt("x"), result.getInt("y"), result.getInt("z"));
				if (resSpawnerLocation != null) {
					resourceSpawners.add(new ResourceTreeSpawner(resSpawnerLocation));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resourceSpawners;

	}

	public void saveResSpawner(ResourceSpawner resourceSpawner) {
		try {
			PreparedStatement stat = connection.prepareStatement(
					"INSERT IGNORE INTO " + tablePrefix + "_resource_spawners(gen_type,world,x,y,z) VALUES (?,?,?,?,?)");
			stat.setString(1, "ore");
			stat.setString(2, resourceSpawner.getResSpawnerLoc().getWorld().getName());
			stat.setFloat(3, resourceSpawner.getResSpawnerLoc().getBlockX());
			stat.setFloat(4, resourceSpawner.getResSpawnerLoc().getBlockY());
			stat.setFloat(5, resourceSpawner.getResSpawnerLoc().getBlockZ());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveResTreeSpawner(ResourceTreeSpawner resourceTreeSpawner) {
		try {
			PreparedStatement stat = connection.prepareStatement(
					"INSERT IGNORE INTO " + tablePrefix + "_resource_spawners(gen_type,world,x,y,z) VALUES (?,?,?,?,?)");
			stat.setString(1, "log");
			stat.setString(2, resourceTreeSpawner.getTreeSpawnerLoc().getWorld().getName());
			stat.setFloat(3, resourceTreeSpawner.getTreeSpawnerLoc().getBlockX());
			stat.setFloat(4, resourceTreeSpawner.getTreeSpawnerLoc().getBlockY());
			stat.setFloat(5, resourceTreeSpawner.getTreeSpawnerLoc().getBlockZ());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteResSpawner(ResourceSpawner resourceSpawner) {
		try {
			PreparedStatement stat = connection.prepareStatement(
					"DELETE FROM " + tablePrefix + "_resource_spawners WHERE gen_type = ? AND world = ? AND x = ? AND y = ? AND z = ?");
			stat.setString(1, "ore");
			stat.setString(2, resourceSpawner.getResSpawnerLoc().getWorld().getName());
			stat.setFloat(3, resourceSpawner.getResSpawnerLoc().getBlockX());
			stat.setFloat(4, resourceSpawner.getResSpawnerLoc().getBlockY());
			stat.setFloat(5, resourceSpawner.getResSpawnerLoc().getBlockZ());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteResTreeSpawner(ResourceTreeSpawner resourceTreeSpawner) {
		try {
			PreparedStatement stat = connection.prepareStatement(
					"DELETE FROM " + tablePrefix + "_resource_spawners WHERE gen_type = ? AND world = ? AND x = ? AND y = ? AND z = ?");
			stat.setString(1, "log");
			stat.setString(2, resourceTreeSpawner.getTreeSpawnerLoc().getWorld().getName());
			stat.setFloat(3, resourceTreeSpawner.getTreeSpawnerLoc().getBlockX());
			stat.setFloat(4, resourceTreeSpawner.getTreeSpawnerLoc().getBlockY());
			stat.setFloat(5, resourceTreeSpawner.getTreeSpawnerLoc().getBlockZ());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HashMap<Location, String> getProtections(Survivor survivor) {

		HashMap<Location, String> protections = new HashMap<Location, String>();

		try {
			PreparedStatement stat = connection
					.prepareStatement("SELECT * FROM " + tablePrefix + "_shelters_protection WHERE uuid = ?");
			stat.setString(1, survivor.getUniqueId().toString());
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				String prot_type = result.getString("prot_type");
				Location prot_location = new Location(Bukkit.getWorld(result.getString("world")), result.getInt("x"),
						result.getInt("y"), result.getInt("z"));
				if (prot_location != null) {
					protections.put(prot_location, prot_type);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return protections;

	}

	public Block getShelterConsoleBlock(Survivor survivor) {

		Block consoleBlock = null;

		try {
			PreparedStatement stat = connection
					.prepareStatement("SELECT console_location FROM " + tablePrefix + "_shelters WHERE uuid = ?");
			stat.setString(1, survivor.getUniqueId().toString());

			ResultSet result = stat.executeQuery();

			if (result.next()) {
				Location console_location = Main.fromString(result.getString("console_location"));
				if (console_location.getBlock() != null) {
					consoleBlock = console_location.getBlock();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return consoleBlock;

	}

	public ArrayList<String> getTeamers(Survivor survivor) {

		ArrayList<String> teamers = new ArrayList<String>();

		try {
			PreparedStatement stat = connection
					.prepareStatement("SELECT * FROM " + tablePrefix + "_teams WHERE uuid = ?");
			stat.setString(1, survivor.getUniqueId().toString());
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				String teamerUniqueId = result.getString("teamer_uuid");
				if (teamerUniqueId != null) {
					teamers.add(teamerUniqueId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teamers;

	}

	public boolean survivorExists(Player player) {

		try {
			PreparedStatement stat = connection
					.prepareStatement("SELECT uuid FROM " + tablePrefix + "_survivors WHERE uuid = ?");
			stat.setString(1, player.getUniqueId().toString());
			ResultSet result = stat.executeQuery();

			if (result.next()) {
				return true;
			} else if (!result.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public void addSurvivor(Survivor survivor) {
		try {
			PreparedStatement stat = connection.prepareStatement(
					"INSERT IGNORE INTO " + tablePrefix + "_survivors(uuid,name,lang,shelter) VALUES (?,?,?,?)");
			stat.setString(1, survivor.getUniqueId().toString());
			stat.setString(2, survivor.getName());
			stat.setString(3, survivor.getLanguage().getLang());
			stat.setInt(4, 0);
			stat.executeUpdate();
			Main.getSurvivorManager().registerSurvivor(survivor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveToDatabase(Shelter shelter) {
		try {

			UUID shelterOwnerUUID = shelter.getShelterOwner().getUniqueId();

			PreparedStatement teamStat = connection
					.prepareStatement("INSERT IGNORE INTO " + tablePrefix + "_teams(uuid,teamer_uuid) VALUES (?,?)");
			for (Survivor survivor : shelter.getTeam().getTeamMembers()) {
				teamStat.setString(1, shelterOwnerUUID.toString());
				teamStat.setString(2, survivor.getUniqueId().toString());
				teamStat.executeUpdate();
			}

			PreparedStatement teamRemovingStat = connection
					.prepareStatement("DELETE FROM " + tablePrefix + "_teams WHERE uuid = ? AND teamer_uuid = ?");
			for (Survivor survivor : shelter.getTeam().getTeamersToRemove()) {
				teamRemovingStat.setString(1, shelterOwnerUUID.toString());
				teamRemovingStat.setString(2, survivor.getUniqueId().toString());
				teamRemovingStat.executeUpdate();
			}

			PreparedStatement protStat = connection.prepareStatement("INSERT IGNORE INTO " + tablePrefix
					+ "_shelters_protection(uuid,prot_type,world,x,y,z) VALUES (?,?,?,?,?,?)");
			for (Block door : shelter.getDoors()) {
				protStat.setString(1, shelterOwnerUUID.toString());
				protStat.setString(2, "door");
				protStat.setString(3, door.getWorld().getName());
				protStat.setDouble(4, door.getX());
				protStat.setDouble(5, door.getY());
				protStat.setDouble(6, door.getZ());
				protStat.execute();
			}

			for (Block trapdoor : shelter.getTrapdoors()) {
				protStat.setString(1, shelterOwnerUUID.toString());
				protStat.setString(2, "trapdoor");
				protStat.setString(3, trapdoor.getWorld().getName());
				protStat.setDouble(4, trapdoor.getX());
				protStat.setDouble(5, trapdoor.getY());
				protStat.setDouble(6, trapdoor.getZ());
				protStat.execute();
			}

			for (Block barrier : shelter.getBarriers()) {
				protStat.setString(1, shelterOwnerUUID.toString());
				protStat.setString(2, "barrier");
				protStat.setString(3, barrier.getWorld().getName());
				protStat.setDouble(4, barrier.getX());
				protStat.setDouble(5, barrier.getY());
				protStat.setDouble(6, barrier.getZ());
				protStat.execute();
			}

			PreparedStatement protRemovingStat = connection.prepareStatement("DELETE FROM " + tablePrefix
					+ "_shelters_protection WHERE uuid = ? AND prot_type = ? AND world = ? AND x = ? AND y = ? AND z = ?");
			for (Block doorToRemove : shelter.getDoorsToRemove()) {
				protRemovingStat.setString(1, shelterOwnerUUID.toString());
				protRemovingStat.setString(2, "door");
				protRemovingStat.setString(3, doorToRemove.getWorld().getName());
				protRemovingStat.setDouble(4, doorToRemove.getX());
				protRemovingStat.setDouble(5, doorToRemove.getY());
				protRemovingStat.setDouble(6, doorToRemove.getZ());
				protRemovingStat.execute();
			}

			for (Block trapdoorToRemove : shelter.getTrapdoorsToRemove()) {
				protRemovingStat.setString(1, shelterOwnerUUID.toString());
				protRemovingStat.setString(2, "trapdoor");
				protRemovingStat.setString(3, trapdoorToRemove.getWorld().getName());
				protRemovingStat.setDouble(4, trapdoorToRemove.getX());
				protRemovingStat.setDouble(5, trapdoorToRemove.getY());
				protRemovingStat.setDouble(6, trapdoorToRemove.getZ());
				protRemovingStat.execute();
			}

			for (Block barrierToRemove : shelter.getBarriersToRemove()) {
				protRemovingStat.setString(1, shelterOwnerUUID.toString());
				protRemovingStat.setString(2, "barrier");
				protRemovingStat.setString(3, barrierToRemove.getWorld().getName());
				protRemovingStat.setDouble(4, barrierToRemove.getX());
				protRemovingStat.setDouble(5, barrierToRemove.getY());
				protRemovingStat.setDouble(6, barrierToRemove.getZ());
				protRemovingStat.execute();
			}

			PreparedStatement floorStat = connection
					.prepareStatement("UPDATE " + tablePrefix + "_shelters SET floor = ? WHERE uuid = ?");
			floorStat.setInt(1, shelter.getBuiltFloors());
			floorStat.setString(2, shelterOwnerUUID.toString());
			floorStat.executeUpdate();
			PreparedStatement bunkerStat = connection
					.prepareStatement("UPDATE " + tablePrefix + "_shelters SET bunker = ? WHERE uuid = ?");
			bunkerStat.setBoolean(1, shelter.hasBuiltBunker());
			bunkerStat.setString(2, shelterOwnerUUID.toString());
			bunkerStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getLanguage(Survivor survivor) {

		try {
			PreparedStatement langStat = connection
					.prepareStatement("SELECT lang FROM " + tablePrefix + "_survivors WHERE uuid = ?");
			langStat.setString(1, survivor.getUniqueId().toString());
			ResultSet result = langStat.executeQuery();

			if (result.next()) {
				String lang = result.getString("lang");
				if (lang != null) {
					return lang;
				}
			} else if (!result.next()) {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public void setLanguage(Survivor survivor, String language) {

		try {
			PreparedStatement langStat = connection
					.prepareStatement("UPDATE " + tablePrefix + "_survivors SET lang = ? WHERE uuid = ?");
			langStat.setString(1, language);
			langStat.setString(2, survivor.getUniqueId().toString());
			langStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Survivor> getSurvivors() {

		ArrayList<Survivor> survivors = new ArrayList<Survivor>();

		try {
			PreparedStatement stat = connection.prepareStatement("SELECT * FROM " + tablePrefix + "_survivors");
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				survivors.add(new Survivor(Bukkit.getOfflinePlayer(UUID.fromString(result.getString("uuid")))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return survivors;
	}

}