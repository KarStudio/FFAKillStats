package top.karpvp.normalpvp.killstats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class FFAKillAPI
{
  public int getKill(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("ffakill");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addKill(String name, int amount,String pn) {
    return setKill(name, getKill(name) + amount,pn);
  }

  public boolean removeKill(String name, int amount,String pn) {
    return setKill(name, getKill(name) - amount,pn);
  }

  public boolean setKill(String name, int amount,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    FFAKillChangeEvent event = new FFAKillChangeEvent(name, getKill(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("ffakill", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
  
  public int getDeath(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("ffadeath");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addDeath(String name, int amount,String pn) {
    return setDeath(name, getDeath(name) + amount,pn);
  }

  public boolean removeDeath(String name, int amount,String pn) {
    return setDeath(name, getDeath(name) - amount,pn);
  }

  public boolean setDeath(String name, int amount ,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    FFAKillChangeEvent event = new FFAKillChangeEvent(name, getDeath(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("ffadeath", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
}