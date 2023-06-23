package me.DarthChungo.VoidTeleport;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
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

    String command = config.getCommand();

    if (command != null) {
      CommandSender sender;

      if (config.getCaller().equals("console")) {
        sender = getServer().getConsoleSender();

      } else {
        sender = config.getHideOutput() ? new NoFeedbackPlayerWrapper(player) : player;
      }

      try {
        Bukkit.dispatchCommand(sender, command.replace("%player%", player.getName()));

      } catch (Exception e) {
        getLogger().warning(
            "[!]\n"
            + "        An error ocurred trying to hide the output of the provided command.\n"
            + "        Note that trying to hide the output of vanilla commands run by the player is unsupported.\n"
            + "        It is possible that other plugins do not work either.\n"
            + "        Here is a detailed stack trace of the error:"
            );
        e.printStackTrace();
      }
    }

    return true;
  }

  public void onEnable() {
    command = new VoidTeleportCommand(this);
    event = new VoidTeleportEvent(this);
    config = new VoidTeleportConfig(this);

    config.load();
  }

  @Override
  public void onDisable() {
    config.save();
  }

  public void reload() {
    config.load();
    config.save();
  }
}
