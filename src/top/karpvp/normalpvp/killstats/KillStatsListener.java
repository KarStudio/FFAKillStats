package top.karpvp.normalpvp.killstats;

import io.karfunction.karcoins.coins.VaultAPI;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.karpvp.normalpvp.pvpmode.Main3;

public class KillStatsListener implements Listener{
    
    @EventHandler
    public void onPlayerClicktheItem(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if((p.getItemInHand().getTypeId() == 388||p.getItemInHand().getTypeId() == 267)&&(event.getAction() == Action.RIGHT_CLICK_BLOCK||event.getAction() == Action.RIGHT_CLICK_AIR)){
            if (p.getItemInHand().getItemMeta().getDisplayName()=="§a§l功能§8(右键打开)"){
                function(p);
            }else if (p.getItemInHand().getItemMeta().getDisplayName()=="§6§l模式选择§8(右键打开)"){
                Function.modeChoose(p);
            }
        }
    }
    
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)) { return;} 
        Player p =(Player)event.getWhoClicked();
        if (event.getInventory().getTitle().equalsIgnoreCase("§e§lFunction")){
            p.updateInventory(); 
            event.setCancelled(true);
            if(event.getRawSlot() == 10){
                Inventory inv = Bukkit.createInventory(null,54, "§c§l战绩");
                ItemStack main = new ItemStack(399,1);
                ItemMeta mainim = main.getItemMeta();
                mainim.setDisplayName("§6§l总战绩");
                List mainimlores = new ArrayList<String>();
                int kill = Main.getFFAAPI().getKill(p.getUniqueId().toString());
                int death = Main.getFFAAPI().getDeath(p.getUniqueId().toString());
                mainimlores.add("§f§l总击杀§7:§f§l"+kill);
                mainimlores.add("§f§l总死亡§7:§f§l"+death);
                mainimlores.add("§f§lKDR§7:§f§l"+getKDR(kill,death));
                mainim.setLore(mainimlores);
                main.setItemMeta(mainim);
                inv.setItem(49, main);
                p.closeInventory();
                p.openInventory(inv);
            }
        }else if (event.getInventory().getTitle().equalsIgnoreCase("§c§l战绩")){
            p.updateInventory(); 
            event.setCancelled(true);
        }else if (event.getInventory().getTitle().equalsIgnoreCase("§e§l选择模式")){
            p.updateInventory(); 
            event.setCancelled(true);
            if(event.getRawSlot() == 1){
                Main3.joinGapple(p);
            }else if (event.getRawSlot() == 3){
                Main3.joinPotion(p);
            }else if (event.getRawSlot() == 5){
                Main3.joinFullDia(p);
            }else if (event.getRawSlot() == 7){
                Main3.joinCombo(p);
            }else if (event.getRawSlot() == 24){
                function(p);
            }
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        p.sendMessage("§4§l本次重大更新:");
        p.sendMessage("§f* §4§lCpsTest");
        p.sendMessage("§f* §4§l战绩系统\"/ks\"");
        p.sendMessage("§4§l本次细节更新:");
        p.sendMessage("§f* §4§l选择模式菜单GUI");
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player p = event.getEntity();
        Main.getFFAAPI().addDeath(p.getUniqueId().toString(), 1, p.getName());
        if (Main3.inGapple(p)){
            Main.getGappleAPI().addGappleDeath(p.getUniqueId().toString(), 1, p.getName());
        }else if (Main3.inPotion(p)){
            Main.getPotionAPI().addPotionDeath(p.getUniqueId().toString(), 1, p.getName());
        }else if (Main3.inFullDia(p)){
            Main.getFullDiaAPI().addFullDiaDeath(p.getUniqueId().toString(), 1, p.getName());
        }else if (Main3.inCombo(p)){
            Main.getComboAPI().addComboDeath(p.getUniqueId().toString(), 1, p.getName());
        }
        if (p.getKiller()!=null){
            Player killer = p.getKiller();
            Main.getFFAAPI().addKill(killer.getUniqueId().toString(), 1, killer.getName());
            if (Main3.inGapple(killer)){
                Main.getGappleAPI().addGappleKill(killer.getUniqueId().toString(), 1, killer.getName());
            }else if (Main3.inPotion(killer)){
                Main.getPotionAPI().addPotionKill(killer.getUniqueId().toString(), 1, killer.getName());
            }else if (Main3.inFullDia(killer)){
                Main.getFullDiaAPI().addFullDiaKill(killer.getUniqueId().toString(), 1, killer.getName());
            }else if (Main3.inCombo(killer)){
                Main.getComboAPI().addComboKill(killer.getUniqueId().toString(), 1, killer.getName());
            }
            int kill = Main.getFFAAPI().getKill(killer.getUniqueId().toString());
            if (kill > 100){
                if (kill >1000){
                    if (kill > 10000){
                        if (kill > 50000){
                            if (kill == 100000){
                                getServer().broadcastMessage("§5"+killer.getName()+"§5§l完成了10w的累计杀敌");
                                for (Player p1 : Bukkit.getOnlinePlayers()){
                                    try{
                                        ActionBar.send(p1, "§6"+killer.getName()+"§6§l完成了10w的累计杀敌");
                                    }catch(Exception e){}
                                }
                                VaultAPI.addCoins(killer.getName(), 8000);
                            }else if (kill == 300000){
                                getServer().broadcastMessage("§4"+killer.getName()+"§4§l完成了30w的累计杀敌");
                                for (Player p1 : Bukkit.getOnlinePlayers()){
                                   p1.sendTitle("§5"+killer.getName(), "§5§l完成了30w的累计杀敌");
                                }
                                VaultAPI.addCoins(killer.getName(), 10000);
                            }else if (kill == 500000){
                                getServer().broadcastMessage("§4"+killer.getName()+"§4§l完成了50w的累计杀敌");
                                for (Player p1 : Bukkit.getOnlinePlayers()){
                                   p1.sendTitle("§4"+killer.getName(), "§4§l完成了50w的累计杀敌");
                                }
                                VaultAPI.addCoins(killer.getName(), 20000);
                            }else if (kill == 1000000){
                                getServer().broadcastMessage("§6"+killer.getName()+"§6§l完成了100w的累计杀敌");
                                for (Player p1 : Bukkit.getOnlinePlayers()){
                                   p1.sendTitle("§e"+killer.getName(), "§e§l完成了100w的累计杀敌");
                                }
                                VaultAPI.addCoins(killer.getName(), 50000);
                            }else if (kill == 3000000){
                                getServer().broadcastMessage("§6"+killer.getName()+"§6§l完成了300w的累计杀敌");
                                for (Player p1 : Bukkit.getOnlinePlayers()){
                                   p1.sendTitle("§6"+killer.getName(), "§6§l完成了300w的累计杀敌");
                                }
                                VaultAPI.addCoins(killer.getName(), 100000);
                            }
                        }else if (kill == 20000){
                            getServer().broadcastMessage("§1"+killer.getName()+"§1§l完成了2w的累计杀敌");
                            for (Player p1 : Bukkit.getOnlinePlayers()){
                                try{
                                    ActionBar.send(p1, "§a"+killer.getName()+"§a§l完成了2w的累计杀敌");
                                }catch(Exception e){}
                            }
                            VaultAPI.addCoins(killer.getName(), 2500);
                        }else if (kill == 50000){
                            getServer().broadcastMessage("§5"+killer.getName()+"§5§l完成了5w的累计杀敌");
                            for (Player p1 : Bukkit.getOnlinePlayers()){
                                try{
                                    ActionBar.send(p, "§2"+killer.getName()+"§2§l完成了5w的累计杀敌");
                                }catch(Exception e){}
                            }
                            VaultAPI.addCoins(killer.getName(), 5000);
                        }
                    }else if (kill == 2000){
                        getServer().broadcastMessage("§a"+killer.getName()+"§a§l完成了2000的累计杀敌");
                        VaultAPI.addCoins(killer.getName(), 1000);
                    }else if (kill == 5000){
                        getServer().broadcastMessage("§2"+killer.getName()+"§2§l完成了5000的累计杀敌");
                        VaultAPI.addCoins(killer.getName(), 1500);
                    }else if (kill == 10000){
                        getServer().broadcastMessage("§b"+killer.getName()+"§b§l完成了1w的累计杀敌");
                        for (Player p1 : Bukkit.getOnlinePlayers()){
                            try{
                                ActionBar.send(p1, "§7"+killer.getName()+"§7§l完成了1w的累计杀敌");
                            }catch(Exception e){}
                        }
                        VaultAPI.addCoins(killer.getName(), 2000);
                    }
                }else if (kill == 100){
                    getServer().broadcastMessage("§7"+killer.getName()+"§7§l完成了100的累计杀敌");
                    VaultAPI.addCoins(killer.getName(), 100);
                }else if (kill == 500){
                    getServer().broadcastMessage("§7"+killer.getName()+"§7§l完成了500的累计杀敌");
                    VaultAPI.addCoins(killer.getName(), 200);
                }else if (kill == 1000){
                    getServer().broadcastMessage("§a"+killer.getName()+"§a§l完成了1000的累计杀敌");
                    VaultAPI.addCoins(killer.getName(), 500);
                }
            }
//            switch (kill){
//                case 100:
//                    getServer().broadcastMessage("§7"+killer.getName()+"§7§l完成了100的累计杀敌");
//                    VaultAPI.addCoins(killer.getName(), 100);
//                    break;
//                case 500:
//                    getServer().broadcastMessage("§7"+killer.getName()+"§7§l完成了500的累计杀敌");
//                    VaultAPI.addCoins(killer.getName(), 200);
//                    break;
//                case 1000:
//                    getServer().broadcastMessage("§a"+killer.getName()+"§a§l完成了1000的累计杀敌");
//                    VaultAPI.addCoins(killer.getName(), 500);
//                    break;
//                case 2000:
//                    getServer().broadcastMessage("§a"+killer.getName()+"§a§l完成了2000的累计杀敌");
//                    VaultAPI.addCoins(killer.getName(), 1000);
//                    break;
//                case 5000:
//                    getServer().broadcastMessage("§2"+killer.getName()+"§2§l完成了5000的累计杀敌");
//                    VaultAPI.addCoins(killer.getName(), 1500);
//                    break;
//                case 10000:
//                    getServer().broadcastMessage("§b"+killer.getName()+"§b§l完成了1w的累计杀敌");
//                    for (Player p1 : Bukkit.getOnlinePlayers()){
//                        try{
//                            ActionBar.send(p1, "§7"+killer.getName()+"§7§l完成了1w的累计杀敌");
//                        }catch(Exception e){}
//                    }
//                    VaultAPI.addCoins(killer.getName(), 2000);
//                    break;
//                case 20000:
//                    getServer().broadcastMessage("§1"+killer.getName()+"§1§l完成了2w的累计杀敌");
//                    for (Player p1 : Bukkit.getOnlinePlayers()){
//                        try{
//                            ActionBar.send(p1, "§a"+killer.getName()+"§a§l完成了2w的累计杀敌");
//                        }catch(Exception e){}
//                    }
//                    VaultAPI.addCoins(killer.getName(), 2500);
//                    break;
//                case 50000:
//                    getServer().broadcastMessage("§5"+killer.getName()+"§5§l完成了5w的累计杀敌");
//                    for (Player p1 : Bukkit.getOnlinePlayers()){
//                        try{
//                            ActionBar.send(p, "§2"+killer.getName()+"§2§l完成了5w的累计杀敌");
//                        }catch(Exception e){}
//                    }
//                    VaultAPI.addCoins(killer.getName(), 5000);
//                    break;
//                case 100000:
//                    getServer().broadcastMessage("§5"+killer.getName()+"§5§l完成了10w的累计杀敌");
//                    for (Player p1 : Bukkit.getOnlinePlayers()){
//                        try{
//                            ActionBar.send(p1, "§6"+killer.getName()+"§6§l完成了10w的累计杀敌");
//                        }catch(Exception e){}
//                    }
//                    VaultAPI.addCoins(killer.getName(), 8000);
//                    break;
//                case 300000:
//                    getServer().broadcastMessage("§4"+killer.getName()+"§4§l完成了30w的累计杀敌");
//                    for (Player p1 : Bukkit.getOnlinePlayers()){
//                       p1.sendTitle("§5"+killer.getName(), "§5§l完成了30w的累计杀敌");
//                    }
//                    VaultAPI.addCoins(killer.getName(), 10000);
//                    break;
//                case 500000:
//                    getServer().broadcastMessage("§4"+killer.getName()+"§4§l完成了50w的累计杀敌");
//                    for (Player p1 : Bukkit.getOnlinePlayers()){
//                       p1.sendTitle("§4"+killer.getName(), "§4§l完成了50w的累计杀敌");
//                    }
//                    VaultAPI.addCoins(killer.getName(), 20000);
//                    break;
//                case 1000000:
//                    getServer().broadcastMessage("§6"+killer.getName()+"§6§l完成了100w的累计杀敌");
//                    for (Player p1 : Bukkit.getOnlinePlayers()){
//                       p1.sendTitle("§e"+killer.getName(), "§e§l完成了100w的累计杀敌");
//                    }
//                    VaultAPI.addCoins(killer.getName(), 50000);
//                    break;
//                case 3000000:
//                    getServer().broadcastMessage("§6"+killer.getName()+"§6§l完成了300w的累计杀敌");
//                    for (Player p1 : Bukkit.getOnlinePlayers()){
//                       p1.sendTitle("§6"+killer.getName(), "§6§l完成了300w的累计杀敌");
//                    }
//                    VaultAPI.addCoins(killer.getName(), 100000);
//                    break;
//            }
        }
    }
    
    public String getKDR(int kill,int death){
        double kdr=0;
        if (death == 0){
            if (kill==0){
                kdr=0;
            }else{
                kdr=1;
            }
        }else if (kill==0){
            if (death==0){
                kdr=0;
            }else{
                kdr=-1;
            }
        }else{
            kdr = (double)kill/death;
        }
        NumberFormat nf = NumberFormat.getInstance();nf.setMaximumFractionDigits(3);
        return nf.format(kdr);
    }
    
    public void function(Player p){
        Inventory inv = Bukkit.createInventory(null,27, "§e§lFunction");
        ItemStack stats = new ItemStack(399,1);
        ItemMeta statsim = stats.getItemMeta();
        statsim.setDisplayName("§c§l战绩");
        stats.setItemMeta(statsim);
        ItemStack level = new ItemStack(384,1);
        ItemMeta levelim = level.getItemMeta();
        levelim.setDisplayName("§a§l等级(尚未开放)");
        level.setItemMeta(levelim);
        ItemStack rw = new ItemStack(358,1);
        ItemMeta rwim = rw.getItemMeta();
        rwim.setDisplayName("§6§l任务");
        rw.setItemMeta(rwim);
        ItemStack vault = new ItemStack(266,1);
        ItemMeta vaultim = vault.getItemMeta();
        vaultim.setDisplayName("§e经济");
        List vaultlores = new ArrayList<String>();
        vaultlores.add("§e§lKarCoins§7:§f§l"+VaultAPI.getCoins(p.getName()));
        vaultlores.add("§c§lKarCredit§7:§f§l"+VaultAPI.getCredit(p.getName()));
        vaultlores.add("§6§lKarma§7:§f§l"+VaultAPI.getKarma(p.getName()));
        vaultim.setLore(vaultlores);
        vault.setItemMeta(vaultim);
        inv.setItem(10, stats);
        inv.setItem(12, level);
        inv.setItem(14, rw);
        inv.setItem(16, vault);
        p.closeInventory();
        p.openInventory(inv);
    }
}
