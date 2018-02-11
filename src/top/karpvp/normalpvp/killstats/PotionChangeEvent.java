package top.karpvp.normalpvp.killstats;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PotionChangeEvent extends Event
{
  private static final HandlerList handlers = new HandlerList();
  private final Player player;
  private int newKarma;
  private int oldKarma;

  public PotionChangeEvent(String playername, int oldKarma, int newKarma)
  {
    this.player = Bukkit.getServer().getPlayer(playername);
    this.newKarma = newKarma;
    this.oldKarma = oldKarma;
  }

  public Player getPlayer() {
    return this.player;
  }

  public int getNewKarma() {
    return this.newKarma;
  }

  public int getOldKarma() {
    return this.oldKarma;
  }

  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}