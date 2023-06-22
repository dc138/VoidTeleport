package me.DarthChungo.VoidTeleport;

import org.bukkit.Location;
import org.bukkit.World;

class VoidTeleportConfig {
  private final VoidTeleport plugin;

  private static final String VOID_HEIGHT_PATH = "void_height";
  private static final String SPAWN_LOCATION_PATH = "spawn";

  private static final String SEPARATOR = ";";

  VoidTeleportConfig(VoidTeleport p) {
    plugin = p;
    plugin.saveDefaultConfig();
  }

  void save() {
    plugin.saveConfig();
  }

  public int getVoidHeight() {
    return plugin.getConfig().getInt(VOID_HEIGHT_PATH, 0);
  }

  public Location getSpawnLocation() {
    String str = plugin.getConfig().getString(SPAWN_LOCATION_PATH, "null");
    String[] fields = str.split(SEPARATOR);

    if (str.equals("null") || fields.length != 6) {
      return null;
    }

    World world;
    Double x, y, z;
    Float yaw, pitch;

    if ((world = plugin.getServer().getWorld(fields[0])) == null ||
        (x = Double.parseDouble(fields[1])) == null ||
        (y = Double.parseDouble(fields[2])) == null ||
        (z = Double.parseDouble(fields[3])) == null ||
        (yaw = Float.parseFloat(fields[4])) == null ||
        (pitch = Float.parseFloat(fields[5])) == null) {
      return null;
    }

    return new Location(world, x, y, z, yaw, pitch);
  }

  public void setSpawnLocation(Location location) {
    String serialized = location.getWorld().getName()
                       + SEPARATOR + location.getX()
                       + SEPARATOR + location.getY()
                       + SEPARATOR + location.getZ()
                       + SEPARATOR + location.getYaw()
                       + SEPARATOR + location.getPitch();
    plugin.getConfig().set(SPAWN_LOCATION_PATH, serialized);
  }
}
