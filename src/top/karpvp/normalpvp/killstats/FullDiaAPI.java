package top.karpvp.normalpvp.killstats;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.Bukkit;

public class FullDiaAPI {
    public int getFullDiaKill(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("fulldiakill");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addFullDiaKill(String name, int amount,String pn) {
    return setFullDiaKill(name, getFullDiaKill(name) + amount,pn);
  }

  public boolean removeFullDiaKill(String name, int amount,String pn) {
    return setFullDiaKill(name, getFullDiaKill(name) - amount,pn);
  }

  public boolean setFullDiaKill(String name, int amount,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    GappleChangeEvent event = new GappleChangeEvent(name, getFullDiaKill(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("fulldiakill", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
  
  
  public int getFullDiaDeath(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("fulldiadeath");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addFullDiaDeath(String name, int amount,String pn) {
    return setFullDiaDeath(name, getFullDiaDeath(name) + amount,pn);
  }

  public boolean removeFullDiaDeath(String name, int amount,String pn) {
    return setFullDiaDeath(name, getFullDiaDeath(name) - amount,pn);
  }

  public boolean setFullDiaDeath(String name, int amount,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    GappleChangeEvent event = new GappleChangeEvent(name, getFullDiaDeath(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("fulldiadeath", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
}
