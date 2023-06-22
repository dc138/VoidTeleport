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
  private static final String COMMAND = "voidteleport";

  private final VoidTeleport plugin;

  public VoidTeleportCommand(VoidTeleport p) {
    plugin = p;

    plugin.getCommand(COMMAND).setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
      return false;
    }

    Player player = (Player) sender;
    player.sendMessage(ChatColor.GREEN + "TODO!");

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
