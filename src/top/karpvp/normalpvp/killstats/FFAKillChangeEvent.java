package top.karpvp.normalpvp.killstats;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FFAKillChangeEvent extends Event
{
  private static final HandlerList handlers = new HandlerList();
  private final Player player;
  private int newKill;
  private int oldKill;

  public FFAKillChangeEvent(String playername, int oldCoins, int newCoins)
  {
    this.player = Bukkit.getServer().getPlayer(playername);
    this.newKill = newCoins;
    this.oldKill = oldCoins;
  }

  public Player getPlayer() {
    return this.player;
  }

  public int getNewKill() {
    return this.newKill;
  }

  public int getOldKill() {
    return this.oldKill;
  }

  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}