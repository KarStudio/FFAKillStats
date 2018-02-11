package top.karpvp.normalpvp.killstats;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GappleChangeEvent extends Event
{
  private static final HandlerList handlers = new HandlerList();
  private final Player player;
  private int newCredit;
  private int oldCredit;

  public GappleChangeEvent(String playername, int oldCredit, int newCredit)
  {
    this.player = Bukkit.getServer().getPlayer(playername);
    this.newCredit = newCredit;
    this.oldCredit = oldCredit;
  }

  public Player getPlayer() {
    return this.player;
  }

  public int getNewCredit() {
    return this.newCredit;
  }

  public int getOldCredit() {
    return this.oldCredit;
  }

  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}