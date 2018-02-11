package top.karpvp.normalpvp.killstats;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.karpvp.normalpvp.pvpmode.Main3;

public class Function{
    
    public static void modeChoose(Player p){
        if (!Main3.inGame(p)){
            Inventory inv = Bukkit.createInventory(null,27, "§e§l选择模式");
            ItemStack gapple = new ItemStack(322,1);
            ItemStack potion = new ItemStack(373,1,(short)16421);
            ItemStack fulldia = new ItemStack(311,1);
            ItemStack combo = new ItemStack(349,1,(short)3);
//            ItemStack ironUHC;
//            ItemStack bowShot;
//            ItemStack fishingRod;
//            ItemStack survivalFFA;
            ItemStack rule = new ItemStack(323);
            ItemStack more = new ItemStack(340);
            ItemMeta gapple_ = gapple.getItemMeta();
            gapple_.setDisplayName("§e§l金苹果模式§8(点击加入)");
            List gappleLore = new ArrayList();
            int kill = Main.getGappleAPI().getGappleKill(getPlayer(p.getName()).getUniqueId().toString());
            int death = Main.getGappleAPI().getGappleDeath(getPlayer(p.getName()).getUniqueId().toString());
            gappleLore.add("§8Gapple");gappleLore.add("§a开放中...");gappleLore.add("§7目前人数:§f§l"+Main3.ga.size());
            if(Main3.ga.size()>=1){
                gappleLore.add("§8"+Main3.ga.get(0));
            }if(Main3.ga.size()>=2){
                gappleLore.add("§8"+Main3.ga.get(1));
            }if(Main3.ga.size()>=3){
                gappleLore.add("§8"+Main3.ga.get(2));
            }if(Main3.ga.size()>3){
                gappleLore.add("§8...");
            }
            gappleLore.add("§8左键/右键加入");gappleLore.add("§c你的战绩:");gappleLore.add("§7§l* §f§l杀敌:§f§l"+kill);gappleLore.add("§7§l* §f§l死亡:§f§l"+death);gappleLore.add("§7§l* §f§lKDR:§f§l"+CommandListener.getKDR(kill, death));
            gapple_.setLore(gappleLore);gapple.setItemMeta(gapple_);
            ItemMeta potion_ = potion.getItemMeta();
            potion_.setDisplayName("§c§l药水模式§8(点击加入)");
            List potionLore = new ArrayList();
            int kill2 = Main.getPotionAPI().getPotionKill(getPlayer(p.getName()).getUniqueId().toString());
            int death2 = Main.getPotionAPI().getPotionDeath(getPlayer(p.getName()).getUniqueId().toString());
            potionLore.add("§8Potion");potionLore.add("§a开放中...");potionLore.add("§7目前人数:§f§l"+Main3.po.size());
            if(Main3.po.size()>=1){
                potionLore.add("§8"+Main3.po.get(0));
            }if(Main3.po.size()>=2){
                potionLore.add("§8"+Main3.po.get(1));
            }if(Main3.po.size()>=3){
                potionLore.add("§8"+Main3.po.get(2));
            }if(Main3.po.size()>3){
                potionLore.add("§8...");
            }
            potionLore.add("§8左键/右键加入");potionLore.add("§c你的战绩:");potionLore.add("§7§l* §f§l杀敌:§f§l"+kill2);potionLore.add("§7§l* §f§l死亡:§f§l"+death2);potionLore.add("§7§l* §f§lKDR:§f§l"+CommandListener.getKDR(kill2, death2));
            potion_.setLore(potionLore);potion.setItemMeta(potion_);
            ItemMeta fulldia_ = fulldia.getItemMeta();
            fulldia_.setDisplayName("§5§l钻套模式§8(点击加入)");
            List fulldiaLore = new ArrayList();
            int kill3 = Main.getFullDiaAPI().getFullDiaKill(getPlayer(p.getName()).getUniqueId().toString());
            int death3 = Main.getFullDiaAPI().getFullDiaDeath(getPlayer(p.getName()).getUniqueId().toString());
            fulldiaLore.add("§8Full Diamond");fulldiaLore.add("§a开放中...");fulldiaLore.add("§7目前人数:§f§l"+Main3.fd.size());fulldiaLore.add("§8左键/右键加入");
            if(Main3.fd.size()>=1){
                fulldiaLore.add("§8"+Main3.fd.get(0));
            }if(Main3.fd.size()>=2){
                fulldiaLore.add("§8"+Main3.fd.get(1));
            }if(Main3.fd.size()>=3){
                fulldiaLore.add("§8"+Main3.fd.get(2));
            }if(Main3.fd.size()>3){
                fulldiaLore.add("§8...");
            }
            fulldiaLore.add("§c你的战绩:");fulldiaLore.add("§7§l* §f§l杀敌:§f§l"+kill3);fulldiaLore.add("§7§l* §f§l死亡:§f§l"+death3);fulldiaLore.add("§7§l* §f§lKDR:§f§l"+CommandListener.getKDR(kill3, death3));
            fulldia_.setLore(fulldiaLore);fulldia.setItemMeta(fulldia_);
            ItemMeta combo_ = combo.getItemMeta();
            combo_.setDisplayName("§5§l连击模式§8(点击加入)");
            List comboLore = new ArrayList();
            int kill4 = Main.getComboAPI().getComboKill(getPlayer(p.getName()).getUniqueId().toString());
            int death4 = Main.getComboAPI().getComboDeath(getPlayer(p.getName()).getUniqueId().toString());
            comboLore.add("§8Combo");comboLore.add("§a开放中...");comboLore.add("§7目前人数:§f§l"+Main3.cb.size());
            if(Main3.cb.size()>=1){
                comboLore.add("§8"+Main3.cb.get(0));
            }if(Main3.cb.size()>=2){
                comboLore.add("§8"+Main3.cb.get(1));
            }if(Main3.cb.size()>=3){
                comboLore.add("§8"+Main3.cb.get(2));
            }if(Main3.cb.size()>3){
                comboLore.add("§8...");
            }
            comboLore.add("§8左键/右键加入");comboLore.add("§c你的战绩:");comboLore.add("§7§l* §f§l杀敌:§f§l"+kill4);comboLore.add("§7§l* §f§l死亡:§f§l"+death4);comboLore.add("§7§l* §f§lKDR:§f§l"+CommandListener.getKDR(kill4, death4));
            combo_.setLore(comboLore);combo.setItemMeta(combo_);
            ItemMeta rule_ = rule.getItemMeta();
            rule_.setDisplayName("§b§lFFA规则");
            List ruleLore = new ArrayList();
            ruleLore.add("§7严禁Solo Break(除特殊模式外)");ruleLore.add("§7严禁Clean UP(除特殊模式外)");ruleLore.add("§7严禁hax(被发现者承认会减少惩罚)");ruleLore.add("§7严禁鼠标连点(特别是combo模式)");ruleLore.add("§7有不懂的问别人,服务器十个有九个是大触");
            rule_.setLore(ruleLore);rule.setItemMeta(rule_);
            ItemMeta more_ = more.getItemMeta();
            more_.setDisplayName("§b§l还有更多...");
            List moreLore = new ArrayList();
            moreLore.add("§7你也可以给我们提建议");moreLore.add("§7加QQ群545768962并告诉卡姆吧");
            more_.setLore(moreLore);more.setItemMeta(more_);
            inv.setItem(1,gapple);
            inv.setItem(3,potion);
            inv.setItem(5,fulldia);
            inv.setItem(7,combo);
            inv.setItem(20,rule);
            inv.setItem(22,more);
            inv.setItem(24,Main3.function);
            p.closeInventory();
            p.openInventory(inv);
        }else{
            p.sendMessage("§4你已经在游戏中了");
        }
    }
    
}
