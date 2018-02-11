package top.karpvp.normalpvp.killstats;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import lib.PatPeter.SQLibrary.Database;
import lib.PatPeter.SQLibrary.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Utils
{
  private static Database sql;
  private static boolean databaseIsOpen;
  private static DatabaseConfig databaseConfig;

  public static void loadFiles(CommandSender sender)
  {
    reloadConf();
    databaseConfig = new DatabaseConfig(Main.getInstance().getConfig().getString("SQLhost", "localhost"), Main.getInstance().getConfig().getInt("SQLport", 3306), Main.getInstance().getConfig().getString("SQLdata", "minecraft"), Main.getInstance().getConfig().getString("user", "root"), Main.getInstance().getConfig().getString("password", "root"), Main.getInstance().getConfig().getString("SQLname", "coins"));
    SQLconnect();
    createTable();
    sender.sendMessage(Main.info("加载完毕"));
  }

  public static Database getSQL() {
    return sql;
  }

  public static void updateSQLdata(String columnName, Object value, String QueryName, Object QueryValue) {
    ResultSet query = SQLquery(QueryName, QueryValue);
    try {
      if (query != null)
      {
        String sqlQuery;
//        String sqlQuery;
        if (query.next())
          sqlQuery = "UPDATE IGNORE " + getDatabaseConfig().TABLE_NAME + " SET " + columnName + " = '" + value + "' WHERE " + QueryName + " = '" + QueryValue + "'";
        else {
          sqlQuery = "INSERT IGNORE INTO `" + getDatabaseConfig().TABLE_NAME + "`(`" + QueryName + "`, `" + columnName + "`) VALUES ('" + QueryValue + "', '" + value + "')";
        }
        query.close();
        SQLqueryWithNoResult(sqlQuery);
      } else {
        SQLcheck();
      }
    } catch (SQLException e) {
      info(e.getLocalizedMessage());
    }
  }

  public static void SQLqueryWithNoResult(String query) {
    SQLqueryWithNoResult(query, true);
  }

  public static void SQLqueryWithNoResult(String query, boolean output) {
    SQLcheck();
    try {
      getSQL().query(query);
    } catch (SQLException ex) {
      if (output)
        info(ex.getLocalizedMessage());
    }
  }

  public static ResultSet SQLquery(String QueryName, Object QueryValue)
  {
    SQLcheck();
    try {
      String sqlm = "SELECT * FROM " + getDatabaseConfig().TABLE_NAME + " WHERE `" + QueryName + "` = '" + QueryValue + "'";
      ResultSet query = getSQL().getConnection().prepareStatement(sqlm, 1004, 1008).executeQuery();
      return query;
    } catch (SQLException ex) {
      Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
    }return null;
  }
  
  public static ResultSet SQLquery2(String QueryName, Object QueryValue)
  {
    SQLcheck();
    try {
      String sqlm = "SELECT * FROM " + getDatabaseConfig().TABLE_NAME2 + " WHERE `" + QueryName + "` = '" + QueryValue + "'";
      ResultSet query = getSQL().getConnection().prepareStatement(sqlm, 1004, 1008).executeQuery();
      return query;
    } catch (SQLException ex) {
      Main.getInstance().getLogger().warning(ex.getLocalizedMessage());
    }return null;
  }

  public static DatabaseConfig getDatabaseConfig()
  {
    return databaseConfig;
  }

  private static void SQLconnect() {
    sql = new MySQL(Main.getInstance().getLogger(), Main.info(""), getDatabaseConfig().SQL_HOST, getDatabaseConfig().SQL_PORT, getDatabaseConfig().SQL_DATA, getDatabaseConfig().SQL_USER, getDatabaseConfig().SQL_PASS);
    SQLcheck();
    if (databaseIsOpen())
      info("数据库链接成功");
    else
      info("数据库链接失败, 请检查配置");
  }

  public static void SQLcheck()
  {
    if (!sql.isOpen()) {
      sql.open();
    }
    databaseIsOpen = sql.isOpen();
  }

  public static boolean databaseIsOpen() {
    return databaseIsOpen;
  }

  private static void createTable() {
    SQLqueryWithNoResult("CREATE TABLE IF NOT EXISTS `" + getDatabaseConfig().TABLE_NAME + "`(`id` INT(11) NOT NULL AUTO_INCREMENT, `uuid` varchar(100), `playername` varchar(100), `ffakill` int, `ffadeath` int, `gapplekill` int, `gappledeath` int, `potionkill` int, `potiondeath` int, `fulldiakill` int, `fulldiadeath` int, `combokill` int, `combodeath` int, PRIMARY KEY (`id`), UNIQUE KEY(`uuid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
//    SQLqueryWithNoResult("CREATE TABLE IF NOT EXISTS `" + getDatabaseConfig().TABLE_NAME + "`(`id` INT(11) NOT NULL AUTO_INCREMENT, `playername` varchar(100), `amount` int, PRIMARY KEY (`id`), UNIQUE KEY(`playername`)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
  }

  private static void reloadConf() {
    File file = new File(Main.getInstance().getDataFolder(), "config.yml");
    if (file.isDirectory()) {
      file.delete();
    }
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (Exception ex) {
      }
      Main.getInstance().getConfig().set("SQLhost", "localhost");
      Main.getInstance().getConfig().set("SQLport", Integer.valueOf(3306));
      Main.getInstance().getConfig().set("SQLdata", "minecraft");
      Main.getInstance().getConfig().set("user", "root");
      Main.getInstance().getConfig().set("password", "root");
      Main.getInstance().getConfig().set("SQLname", "coins");
      Main.getInstance().saveConfig();
    }
    Main.getInstance().reloadConfig();
  }

  public static void info(String msg) {
    info(Bukkit.getConsoleSender(), msg);
  }

  public static void info(CommandSender sender, String msg) {
    sender.sendMessage(Main.info(msg));
  }
}