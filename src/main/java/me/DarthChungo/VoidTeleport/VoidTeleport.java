package me.DarthChungo.VoidTeleport;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.java.JavaPlugin;

public class VoidTeleport extends JavaPlugin {
  public VoidTeleportCommand command;
  public VoidTeleportEvent event;
  public VoidTeleportConfig config;

  public boolean teleportPlayer(Player player) {
    if (config.getSpawnLocation() == null) {
      return false;
    }

    player.teleport(config.getSpawnLocation(), TeleportCause.PLUGIN);
    player.setFallDistance(0.f);

    return true;
  }

  public void onEnable() {
    command = new VoidTeleportCommand(this);
    event = new VoidTeleportEvent(this);
    config = new VoidTeleportConfig(this);
  }

  @Override
  public void onDisable() {
    config.save();
  }

  public void reload() {
    config.load();
  }
}
