package me.DarthChungo.VoidTeleport;

import org.bukkit.plugin.java.JavaPlugin;

public class VoidTeleport extends JavaPlugin {
  public void onEnable() {
    getLogger().info("Enabled VoidTeleport");
  }

  @Override
  public void onDisable() {
    getLogger().info("Disabled VoidTeleport");
  }
}
