package top.karpvp.normalpvp.killstats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
  private static Main instance;
  private static FFAKillAPI ffakillAPI;
  private static GappleAPI GappleAPI;
  private static PotionAPI PotionAPI;
  private static FullDiaAPI fulldiaAPI;
  private static ComboAPI comboAPI;

  @Override
  public void onEnable()
  {
    getLogger().info("成功载入萌萌哒的KarFunction");
    instance = this;
    Utils.loadFiles(getServer().getConsoleSender());
    ffakillAPI = new FFAKillAPI();
    GappleAPI = new GappleAPI();
    PotionAPI = new PotionAPI();
    fulldiaAPI = new FullDiaAPI();
    comboAPI = new ComboAPI();
    CommandListener cmd = new CommandListener();
    getCommand("killstats").setExecutor(cmd);
    getCommand("killstatsadmin").setExecutor(cmd);
    Bukkit.getPluginManager().registerEvents(new KillStatsListener(), this);
  }

  public static FFAKillAPI getFFAAPI() {
    return ffakillAPI;
  }
  
  public static GappleAPI getGappleAPI() {
    return GappleAPI;
  }
  public static PotionAPI getPotionAPI() {
    return PotionAPI;
  }
  public static FullDiaAPI getFullDiaAPI(){
      return fulldiaAPI;
  }
  public static ComboAPI getComboAPI(){
      return comboAPI;
  }

  public static String info(String text) {
    return ChatColor.translateAlternateColorCodes('&', "§6§lK§e§lar §7> " + text);
  }
  public static String superinfo(String text){
    return ChatColor.translateAlternateColorCodes('&', "§f------ §6§lK§e§lar§c§lStats §f------\n" + text);
  }
  public static String normalinfo(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }

      @Override
    public void onDisable() {
    getLogger().info("成功卸载萌萌哒的KarFunction");
    }
  
  public static Main getInstance() {
    return instance;
  }
}