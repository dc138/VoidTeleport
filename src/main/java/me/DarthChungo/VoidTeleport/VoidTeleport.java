package me.DarthChungo.VoidTeleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class VoidTeleport extends JavaPlugin {
  public VoidTeleportCommand void_teleport_command;

  public Location spawn_location;

  public boolean teleportPlayer(Player player) {
    if (spawn_location == null) {
      return false;
    }

    player.teleport(spawn_location);
    return true;
  }

  public void onEnable() {
    void_teleport_command = new VoidTeleportCommand(this);
  }

  @Override
  public void onDisable() {}
}
