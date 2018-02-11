package top.karpvp.normalpvp.killstats;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.Bukkit;

public class ComboAPI {
    public int getComboKill(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("combokill");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addComboKill(String name, int amount,String pn) {
    return setComboKill(name, getComboKill(name) + amount,pn);
  }

  public boolean removeComboKill(String name, int amount,String pn) {
    return setComboKill(name, getComboKill(name) - amount,pn);
  }

  public boolean setComboKill(String name, int amount,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    GappleChangeEvent event = new GappleChangeEvent(name, getComboKill(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("combokill", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
  
  
  public int getComboDeath(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("combodeath");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addComboDeath(String name, int amount,String pn) {
    return setComboDeath(name, getComboDeath(name) + amount,pn);
  }

  public boolean removeComboDeath(String name, int amount,String pn) {
    return setComboDeath(name, getComboDeath(name) - amount,pn);
  }

  public boolean setComboDeath(String name, int amount,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    GappleChangeEvent event = new GappleChangeEvent(name, getComboDeath(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("combodeath", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
}
