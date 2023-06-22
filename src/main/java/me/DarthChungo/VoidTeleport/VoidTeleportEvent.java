package me.DarthChungo.VoidTeleport;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

class VoidTeleportEvent implements Listener {
  private final VoidTeleport plugin;

  VoidTeleportEvent(VoidTeleport p) {
    plugin = p;
    p.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler(priority = EventPriority.NORMAL)
  public void onMove(PlayerMoveEvent e) {
    Player player = e.getPlayer();

    if (player.getLocation().getY() < plugin.void_height) {
      plugin.teleportPlayer(player);
    }
  }
}
