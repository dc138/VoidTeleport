package me.DarthChungo.VoidTeleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.java.JavaPlugin;

public class VoidTeleport extends JavaPlugin {
  public VoidTeleportCommand void_teleport_command;
  public VoidTeleportEvent void_teleport_event;

  public Location spawn_location;
  public int void_height;

  public boolean teleportPlayer(Player player) {
    if (spawn_location == null) {
      return false;
    }

    player.teleport(spawn_location, TeleportCause.PLUGIN);
    return true;
  }

  public void onEnable() {
    void_teleport_command = new VoidTeleportCommand(this);
    void_teleport_event = new VoidTeleportEvent(this);

    void_height = 0;
  }

  @Override
  public void onDisable() {}
}
