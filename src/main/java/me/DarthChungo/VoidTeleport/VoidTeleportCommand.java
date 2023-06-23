package me.DarthChungo.VoidTeleport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class VoidTeleportCommand implements TabExecutor {
  private final VoidTeleport plugin;

  private static final String COMMAND = "voidteleport";

  public VoidTeleportCommand(VoidTeleport p) {
    plugin = p;
    plugin.getCommand(COMMAND).setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
      return true;
    }

    Player player = (Player) sender;

    if (args.length != 1) {
      return false;

    } else if (args[0].equals("reload")) {
      plugin.reload();
      player.sendMessage("Plugin configuration reloaded");
      return true;

    } else if (args[0].equals("set")) {
      plugin.config.setSpawnLocation(player.getLocation());
      plugin.config.save();
      player.sendMessage("Set spawn location to your current location");
      return true;

    } else if (args[0].equals("test")) {
      player.sendMessage(plugin.teleportPlayer(player) ? "Teleported you to spawn location" : ChatColor.RED + "Spawn location is not set");
      return true;
    }

    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 1) {
      return Arrays.asList("set", "test", "reload");
    }

    return new ArrayList<>();
  }
}
