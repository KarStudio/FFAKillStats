package top.karpvp.normalpvp.killstats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class PotionAPI
{
  public int getPotionKill(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("potionkill");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addPotionKill(String name, int amount,String pn) {
    return setPotionKill(name, getPotionKill(name) + amount,pn);
  }

  public boolean removePotionKill(String name, int amount,String pn) {
    return setPotionKill(name, getPotionKill(name) - amount,pn);
  }

  public boolean setPotionKill(String name, int amount,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    PotionChangeEvent event = new PotionChangeEvent(name, getPotionKill(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("potionkill", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
  
  
  public int getPotionDeath(String name)
  {
    ResultSet query = Utils.SQLquery("uuid", name.toLowerCase());
    if (query != null) {
      try {
        int result = 0;
        if (query.next()) {
          result = query.getInt("potiondeath");
        }
        query.close();
        return result;
      } catch (SQLException ex) {
        Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
      }
    }
    return 0;
  }

  public boolean addPotionDeath(String name, int amount,String pn) {
    return setPotionDeath(name, getPotionDeath(name) + amount,pn);
  }

  public boolean removePotionDeath(String name, int amount,String pn) {
    return setPotionDeath(name, getPotionDeath(name) - amount,pn);
  }

  public boolean setPotionDeath(String name, int amount,String pn) {
    if ((amount > 2147483647) || (amount < 0)) {
      return false;
    }
    PotionChangeEvent event = new PotionChangeEvent(name, getPotionKill(name), amount);
    Bukkit.getPluginManager().callEvent(event);
    Utils.updateSQLdata("potiondeath", Integer.valueOf(amount), "uuid", name.toLowerCase());
    Utils.updateSQLdata("playername", pn, "uuid", name.toLowerCase());
    return true;
  }
}