package me.DarthChungo.VoidTeleport;

import org.bukkit.plugin.java.JavaPlugin;

public class VoidTeleport extends JavaPlugin {
  public VoidTeleportCommand void_teleport_command;

  public void onEnable() {
    void_teleport_command = new VoidTeleportCommand(this);
  }

  @Override
  public void onDisable() {}
}
