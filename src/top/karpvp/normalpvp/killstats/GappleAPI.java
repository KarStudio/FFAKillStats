package top.karpvp.normalpvp.killstats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getLogger;
import org.bukkit.plugin.PluginManager;

public class GappleAPI
{
  public int getGappleKill(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("gapplekill");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addGappleKill(String name, int amount,String pn) {
    return setGappleKill(name, getGappleKill(name) + amount,pn);
  }

  public boolean removeGappleKill(String name, int amount,String pn) {
    return setGappleKill(name, getGappleKill(name) - amount,pn);
  }

  public boolean setGappleKill(String name, int amount,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    GappleChangeEvent event = new GappleChangeEvent(name, getGappleKill(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("gapplekill", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
  
  
  public int getGappleDeath(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("gappledeath");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addGappleDeath(String name, int amount,String pn) {
    return setGappleDeath(name, getGappleDeath(name) + amount,pn);
  }

  public boolean removeGappleDeath(String name, int amount,String pn) {
    return setGappleDeath(name, getGappleDeath(name) - amount,pn);
  }

  public boolean setGappleDeath(String name, int amount,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    GappleChangeEvent event = new GappleChangeEvent(name, getGappleDeath(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("gappledeath", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
}