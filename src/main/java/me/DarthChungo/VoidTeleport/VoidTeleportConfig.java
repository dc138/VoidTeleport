package me.DarthChungo.VoidTeleport;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

class VoidTeleportConfig {
  private final VoidTeleport plugin;

  private final File file;
  private final FileConfiguration config;

  private static final String VOID_HEIGHT_PATH = "void_height";
  private static final String SPAWN_LOCATION_PATH = "spawn";
  private static final String DAMAGE_PATH = "damage";

  private static final String SEPARATOR = ";";

  VoidTeleportConfig(VoidTeleport p) {
    plugin = p;

    file = new File(plugin.getDataFolder(), "config.yml");
    config = new YamlConfiguration();
  }

  public void load() {
    if (!file.exists()) {
      plugin.getLogger().info("Creating a new default config.yml");
      plugin.saveDefaultConfig();
    }

    try {
      config.load(file);

    } catch (Exception e) {
      plugin.getLogger().log(Level.SEVERE, "Cannot load config file: ", e);
      return;
    }

    Location location = getSpawnLocation();
    Integer height = getVoidHeight();
    Boolean damage = getDamage();

    if (location != null && height != null && damage != null) {
      return;
    }

    plugin.getLogger().warning("Old / invalid config detected, migrating to a new one");

    file.delete();
    plugin.saveDefaultConfig();

    try {
      config.load(file);

    } catch (Exception e) {
      plugin.getLogger().log(Level.SEVERE, "Cannot load config file: ", e);
      return;
    }

    setSpawnLocation(location == null ? null : location);
    setVoidHeight(height == null ? 0 : height);
    setDamage(damage == null ? false : damage);

    save();
  }

  public void save() {
    try {
      config.save(file);

    } catch (Exception e) {
      plugin.getLogger().log(Level.SEVERE, "Cannot save config file: ", e);
      return;
    }
  }

  public Boolean getDamage() {
    return config.isBoolean(DAMAGE_PATH) ? config.getBoolean(DAMAGE_PATH) : null;
  }

  public void setDamage(Boolean value) {
    config.set(DAMAGE_PATH, value);
  }

  public Integer getVoidHeight() {
    return config.isInt(VOID_HEIGHT_PATH) ? config.getInt(VOID_HEIGHT_PATH, 0) : null;
  }

  public void setVoidHeight(Integer height) {
    config.set(VOID_HEIGHT_PATH, height);
  }

  public Location getSpawnLocation() {
    String str = config.getString(SPAWN_LOCATION_PATH, "null");
    String[] fields = str.split(SEPARATOR);

    if (str.equals("null") || fields.length != 6) {
      return null;
    }

    World world;
    Double x, y, z;
    Float yaw, pitch;

    if ((world = plugin.getServer().getWorld(fields[0])) == null
        || (x = Double.parseDouble(fields[1])) == null
        || (y = Double.parseDouble(fields[2])) == null
        || (z = Double.parseDouble(fields[3])) == null
        || (yaw = Float.parseFloat(fields[4])) == null
        || (pitch = Float.parseFloat(fields[5])) == null) {
      return null;
    }

    return new Location(world, x, y, z, yaw, pitch);
  }

  public void setSpawnLocation(Location location) {
    if (location == null) {
      config.set(SPAWN_LOCATION_PATH, "null");
      return;
    }

    String serialized = location.getWorld().getName()
                       + SEPARATOR + location.getX()
                       + SEPARATOR + location.getY()
                       + SEPARATOR + location.getZ()
                       + SEPARATOR + location.getYaw()
                       + SEPARATOR + location.getPitch();
    config.set(SPAWN_LOCATION_PATH, serialized);
  }
}
