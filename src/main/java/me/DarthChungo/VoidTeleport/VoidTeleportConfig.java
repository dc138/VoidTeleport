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
  private static final String COMMAND_PATH = "command";
  private static final String CALLER_PATH = "caller";
  private static final String HIDE_OUTPUT_PATH = "hide_output";

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
    String command = getCommand();
    String caller = getCaller();
    Boolean hide = getHideOutput();

    if (isSpawnLocationValid()
        && isCommandValid()
        && isCallerValid()
        && isVoidHeightValid()
        && isHideOutputValid()) {
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
    setCommand(command == null ? "" : command);
    setCaller(caller == null ? "console" : caller);
    setHideOutput(hide == null ? false : hide);

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

  public String getCommand() {
    String command = config.getString(COMMAND_PATH);
    return isCommandValid() && !command.equals("") ? command : null;
  }

  public void setCommand(String value) {
    config.set(COMMAND_PATH, value);
  }

  public boolean isCommandValid() {
    return config.isString(COMMAND_PATH);
  }

  public String getCaller() {
    return isCallerValid() ? config.getString(CALLER_PATH) : null;
  }

  public void setCaller(String caller) {
    config.set(CALLER_PATH, caller);
  }

  public boolean isCallerValid() {
    String caller = config.getString(CALLER_PATH);
    return config.isString(CALLER_PATH) && (caller.equals("player") || caller.equals("console"));
  }

  public Integer getVoidHeight() {
    return isVoidHeightValid() ? config.getInt(VOID_HEIGHT_PATH, 0) : null;
  }

  public void setVoidHeight(Integer height) {
    config.set(VOID_HEIGHT_PATH, height);
  }

  public boolean isVoidHeightValid() {
    return config.isInt(VOID_HEIGHT_PATH);
  }

  public Location getSpawnLocation() {
    String str = config.getString(SPAWN_LOCATION_PATH);
    String[] fields = str.split(SEPARATOR);

    if (str.equals("null") || fields.length != 6) return null;

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

  public boolean isSpawnLocationValid() {
    String location = config.getString(SPAWN_LOCATION_PATH);
    return location != null && (location.equals("null") || location.split(SEPARATOR).length == 6);
  }

  public Boolean getHideOutput() {
    return isHideOutputValid() ? config.getBoolean(HIDE_OUTPUT_PATH) : null;
  }

  public void setHideOutput(Boolean value) {
    config.set(HIDE_OUTPUT_PATH, value);
  }

  public boolean isHideOutputValid() {
    return config.isBoolean(HIDE_OUTPUT_PATH);
  }
}
