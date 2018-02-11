package top.karpvp.normalpvp.killstats;

import java.text.NumberFormat;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("killstats")) {
            if (args.length == 0){
                if (!(sender instanceof Player)){
                    sender.sendMessage(Main.info("&4抱歉,控制台不能查询自己的战绩,尝试&e/"+label+" <玩家名> [页数]"));
                    return true;
                }
                int kill = Main.getFFAAPI().getKill(getPlayer(sender.getName()).getUniqueId().toString());
                int death = Main.getFFAAPI().getDeath(getPlayer(sender.getName()).getUniqueId().toString());
                String kdr = getKDR(kill,death);
                sender.sendMessage(Main.superinfo("&7&l* &f&l总杀敌:&f&l"+kill+" \n"
                    + "&7&l* &f总死亡:&f&l"+death+" \n"
                    + "&7&l* &f总KDR:&f&l"+kdr+" \n"
                    + "&7&l* &e&l使用&e/"+label+" [页数] &e&l来查看详细信息 \n"
                    + "&7&l* &e&l输入&e/"+label+" 0 &e&l查看模式-页数对应表 \n"
                    + "&7&l* &e&l输入&e/"+label+" <玩家名> [页数] &e&l查看其它玩家的战绩 \n"
                    + "&f&l---------------------"
                ));
                return true;
            }if (args.length > 0){
                if (args.length == 1){
                    switch (args[0]){
                        case "0":
                            sender.sendMessage(Main.superinfo("&7&l* &f&l1 &e&lGapple \n"
                            + "&7&l* &f&l2 &c&lPotion \n"
                            + "&7&l* &f&l3 &b&lFullDia \n"
                            + "&7&l* &f&l4 &6&lCombo \n"
                            + "&7&l* 输入&e/"+label+" [页数] &e&l来查看详细信息 \n"
                            + "&f&l---------------------"
                            ));break;
                        case "1":{
                            int kill = Main.getGappleAPI().getGappleKill(getPlayer(sender.getName()).getUniqueId().toString());
                            int death = Main.getGappleAPI().getGappleDeath(getPlayer(sender.getName()).getUniqueId().toString());
                            String kdr = getKDR(kill,death);
                            sender.sendMessage(Main.normalinfo("&f&l------&e&lGapple&f&l------ \n"
                            + "&7&l* &f&l杀敌:&f&l"+kill+" \n"
                            + "&7&l* &f死亡:&f&l"+death+" \n"
                            + "&7&l* &fKDR:&f&l"+kdr+" \n"
                            + "&f&l------------------"
                            ));break;}
                        case "2":{
                            int kill = Main.getPotionAPI().getPotionKill(getPlayer(sender.getName()).getUniqueId().toString());
                            int death = Main.getPotionAPI().getPotionDeath(getPlayer(sender.getName()).getUniqueId().toString());
                            String kdr = getKDR(kill,death);
                            sender.sendMessage(Main.normalinfo("&f&l------&c&lPotion&f&l------ \n"
                            + "&7&l* &f&l杀敌:&f&l"+kill+" \n"
                            + "&7&l* &f死亡:&f&l"+death+" \n"
                            + "&7&l* &fKDR:&f&l"+kdr+" \n"
                            + "&f&l------------------"
                            ));break;
                        }
                        case "3":{
                            int kill = Main.getFullDiaAPI().getFullDiaKill(getPlayer(sender.getName()).getUniqueId().toString());
                            int death = Main.getFullDiaAPI().getFullDiaDeath(getPlayer(sender.getName()).getUniqueId().toString());
                            String kdr = getKDR(kill,death);
                            sender.sendMessage(Main.normalinfo("&f&l------&b&lFullDia&f&l------ \n"
                            + "&7&l* &f&l杀敌:&f&l"+kill+" \n"
                            + "&7&l* &f死亡:&f&l"+death+" \n"
                            + "&7&l* &fKDR:&f&l"+kdr+" \n"
                            + "&f&l--------------------"
                            ));break;
                        }
                        case "4":{
                            int kill = Main.getComboAPI().getComboKill(getPlayer(sender.getName()).getUniqueId().toString());
                            int death = Main.getComboAPI().getComboDeath(getPlayer(sender.getName()).getUniqueId().toString());
                            String kdr = getKDR(kill,death);
                            sender.sendMessage(Main.normalinfo("&f&l------&6&lCombo&f&l------ \n"
                            + "&7&l* &f&l杀敌:&f&l"+kill+" \n"
                            + "&7&l* &f死亡:&f&l"+death+" \n"
                            + "&7&l* &fKDR:&f&l"+kdr+" \n"
                            + "&f&l--------------------"
                            ));break;
                        }
                        default:
                            try{
                                int kill = Main.getFFAAPI().getKill(getPlayer(args[0]).getUniqueId().toString());
                                int death = Main.getFFAAPI().getDeath(getPlayer(args[0]).getUniqueId().toString());
                                String kdr = getKDR(kill,death);
                                sender.sendMessage(Main.superinfo("&e&l"+args[0]+" &7&l的 \n"
                                + "&7&l* &f&l总杀敌:&f&l"+kill+" \n"
                                + "&7&l* &f总死亡:&f&l"+death+" \n"
                                + "&7&l* &f总KDR:&f&l"+kdr+" \n"
                                + "&7&l* &e&l输入&e/"+label+" "+args[0]+" [页数] &e&l查看详细信息 \n"
                                + "&f&l---------------------"
                                ));
                            }catch(Exception e){
                                sender.sendMessage(Main.info("&4&l玩家不存在"));
                            }
                            break;
                    }
                    return true;
                }
                if (args.length == 2){
                    switch (args[1]){
                        case "1":{
                            try{
                                int kill = Main.getGappleAPI().getGappleKill(getPlayer(args[0]).getUniqueId().toString());
                                int death = Main.getGappleAPI().getGappleDeath(getPlayer(args[0]).getUniqueId().toString());
                                String kdr = getKDR(kill,death);
                                sender.sendMessage(Main.normalinfo("&f&l------&e&lGapple&f&l------ \n"
                                + "&e&l"+args[0]+" &7&l的 \n"
                                + "&7&l* &f&l杀敌:&f&l"+kill+" \n"
                                + "&7&l* &f死亡:&f&l"+death+" \n"
                                + "&7&l* &fKDR:&f&l"+kdr+" \n"
                                + "&f&l------------------"
                                ));
                            }catch(Exception e){
                                sender.sendMessage(Main.info("&4&l玩家不存在"));
                            }
                            break;}
                        case "2":{
                            try{
                                int kill = Main.getPotionAPI().getPotionKill(getPlayer(args[0]).getUniqueId().toString());
                                int death = Main.getPotionAPI().getPotionDeath(getPlayer(args[0]).getUniqueId().toString());
                                String kdr = getKDR(kill,death);
                                sender.sendMessage(Main.normalinfo("&f&l------&c&lPotion&f&l------ \n"
                                + "&e&l"+args[0]+" &7&l的 \n"
                                + "&7&l* &f&l杀敌:&f&l"+kill+" \n"
                                + "&7&l* &f死亡:&f&l"+death+" \n"
                                + "&7&l* &fKDR:&f&l"+kdr+" \n"
                                + "&f&l------------------"
                                ));
                            }catch(Exception e){
                                sender.sendMessage(Main.info("&4&l玩家不存在"));
                            }
                            break;}
                        case "3":{
                            try{
                                int kill = Main.getFullDiaAPI().getFullDiaKill(getPlayer(args[0]).getUniqueId().toString());
                                int death = Main.getFullDiaAPI().getFullDiaDeath(getPlayer(args[0]).getUniqueId().toString());
                                String kdr = getKDR(kill,death);
                                sender.sendMessage(Main.normalinfo("&f&l------&b&lFullDia&f&l------ \n"
                                + "&e&l"+args[0]+" &7&l的 \n"
                                + "&7&l* &f&l杀敌:&f&l"+kill+" \n"
                                + "&7&l* &f死亡:&f&l"+death+" \n"
                                + "&7&l* &fKDR:&f&l"+kdr+" \n"
                                + "&f&l--------------------"
                                ));
                            }catch(Exception e){
                                sender.sendMessage(Main.info("&4&l玩家不存在"));
                            }
                            break;}
                        case "4":{
                            try{
                                int kill = Main.getComboAPI().getComboKill(getPlayer(args[0]).getUniqueId().toString());
                                int death = Main.getComboAPI().getComboDeath(getPlayer(args[0]).getUniqueId().toString());
                                String kdr = getKDR(kill,death);
                                sender.sendMessage(Main.normalinfo("&f&l------&6&lCombo&f&l------ \n"
                                + "&e&l"+args[0]+" &7&l的 \n"
                                + "&7&l* &f&l杀敌:&f&l"+kill+" \n"
                                + "&7&l* &f死亡:&f&l"+death+" \n"
                                + "&7&l* &fKDR:&f&l"+kdr+" \n"
                                + "&f&l--------------------"
                                ));
                            }catch(Exception e){
                                sender.sendMessage(Main.info("&4&l玩家不存在"));
                            }
                            break;}
                        default:
                            try{
                                int kill = Main.getFFAAPI().getKill(args[0]);
                                int death = Main.getFFAAPI().getDeath(args[0]);
                                String kdr = getKDR(kill,death);
                                sender.sendMessage(Main.superinfo("&e&l"+args[0]+" &7&l的 \n"
                                + "&7&l* &f&l总杀敌:&f&l"+kill+" \n"
                                + "&7&l* &f总死亡:&f&l"+death+" \n"
                                + "&7&l* &f总KDR:&f&l"+kdr+" \n"
                                + "&7&l* &e&l输入&e/"+label+" "+args[0]+" [页数] &e&l查看详细信息 \n"
                                + "&f&l---------------------"
                                ));
                            }catch(Exception e){
                                sender.sendMessage(Main.info("&4&l玩家不存在"));
                            }
                            break;
                    }
                    return true;
                }
                sender.sendMessage(Main.info("&4参数过长"));
                return true;
            }
            return true;
        }if(cmd.getName().equalsIgnoreCase("killstatsadmin")) {
            if (args.length>0){
                if (args[0].equalsIgnoreCase("set")){
                    if (args.length==4){
                        if (args[2].equalsIgnoreCase("ffakill")){
                            try{
                                int amount = Integer.parseInt(args[3]);
                                Main.getFFAAPI().setKill(getPlayer(args[1]).getUniqueId().toString(),amount ,args[1]);
                                sender.sendMessage(Main.info("&e成功设置"+args[1]+"的总击杀数为"+Main.getFFAAPI().getKill(getPlayer(args[1]).getUniqueId().toString())));
                            } catch (NumberFormatException ex) {
                                sender.sendMessage(Main.info("&4必须是数字"));
                            }return true;
                        }if (args[2].equalsIgnoreCase("ffadeath")){
                            try{
                                int amount = Integer.parseInt(args[3]);
                                Main.getFFAAPI().setDeath(getPlayer(args[1]).getUniqueId().toString(),amount ,args[1]);
                                sender.sendMessage(Main.info("&e成功设置"+args[1]+"的总死亡数为"+Main.getFFAAPI().getDeath(getPlayer(args[1]).getUniqueId().toString())));
                            } catch (NumberFormatException ex) {
                                sender.sendMessage(Main.info("&4必须是数字"));
                            }return true;
                        }
                        sender.sendMessage(Main.info("&e/"+label+" set "+args[1]+" <type> <amount>"));
                        return true;
                    }
                    sender.sendMessage(Main.info("&e/"+label+" set <name> <type> <amount>"));
                    return true;
                }
                return true;
            }
            return true;
        }
    return true;
      
      
      
      
      
      
      
//    if (cmd.getName().equalsIgnoreCase("KarCoinsAdmin")) {
//      if (args.length > 0) {
//        if (args[0].equalsIgnoreCase("get")) {
//          if (args.length > 1) {
//            int coins = Main.getFFAAPI().getKill(args[1]);
//            sender.sendMessage(Main.info("&a&o" + args[1] + "&7的&7&lKarCoins&7： &f&l" + coins));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " get <玩家名>"));
//          return true;
//        }
//        if (args[0].equalsIgnoreCase("reload")) {
//          Utils.loadFiles(sender);
//        }
//        if (args[0].equalsIgnoreCase("set")) {
//          if (args.length > 2) {
//            try {
//              int coins = Integer.parseInt(args[2]);
//              if (Main.getFFAAPI().setKill(args[1], coins)) {
//                sender.sendMessage(Main.info("&7将&a&o" + args[1] + "&7的&7&lKarCoins&7设为 &f&l" + coins));
//                return true;
//              }
//            } catch (NumberFormatException ex) {
//              sender.sendMessage(Main.info("&4必须是数字"));
//              return true;
//            }
//            sender.sendMessage(Main.info("&4数字过小或过大"));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " set <玩家名> <acount>"));
//          return true;
//        }
//        if (args[0].equalsIgnoreCase("add")) {
//          if (args.length > 2) {
//            try {
//              int coins = Integer.parseInt(args[2]);
//              if (Main.getFFAAPI().addKill(args[1], coins)) {
//                sender.sendMessage(Main.info("&7将&a&o" + args[1] + "&7的&7&lKarCoins&7设为 &f&l" + Main.getFFAAPI().getKill(args[1])));
//                return true;
//              }
//            } catch (NumberFormatException ex) {
//              sender.sendMessage(Main.info("&4必须是数字"));
//              return true;
//            }
//            sender.sendMessage(Main.info("&4数字过小或过大"));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " add <玩家名> <acount>"));
//          return true;
//        }
//        if ((args.length == 3) && (args[0].equalsIgnoreCase("remove"))) {
//          if (args.length > 2) {
//            try {
//              int coins = Integer.parseInt(args[2]);
//              if (Main.getFFAAPI().removeKill(args[1], coins)) {
//                sender.sendMessage(Main.info("&7将&a&o" + args[1] + "&7的&7&lKarCoins&7设为 &f&l" + Main.getFFAAPI().getKill(args[1])));
//                return true;
//              }
//            } catch (NumberFormatException ex) {
//              sender.sendMessage(Main.info("必须是数字"));
//              return true;
//            }
//            sender.sendMessage(Main.info("数字过小或过大"));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " take <玩家名> <acount>"));
//          return true;
//        }
//      }
//      sender.sendMessage(Main.info("&e/" + label + " get <玩家名>"));
//      sender.sendMessage(Main.info("&e/" + label + " set <玩家名> <acount>"));
//      sender.sendMessage(Main.info("&e/" + label + " add <玩家名> <acount>"));
//      sender.sendMessage(Main.info("&e/" + label + " remove <玩家名> <acount>"));
//      sender.sendMessage(Main.info("&e/" + label + " reload"));
//      return true;
//    }
//    if (cmd.getName().equalsIgnoreCase("KarCoins")) {
//      if ((sender instanceof Player)) {
//        sender.sendMessage(Main.info("&7剩余 &f&l" + Main.getFFAAPI().getKill(sender.getName()))+" §7§lKarCoins");
//        return true;
//      }
//      sender.sendMessage(Main.info("&4必须是玩家,管理命令请输入/KarCoinsAdmin或/KCA"));
//      return true;
//    }
//    
//    if (cmd.getName().equalsIgnoreCase("KarCreditAdmin")) {
//      if (args.length > 0) {
//        if (args[0].equalsIgnoreCase("get")) {
//          if (args.length > 1) {
//            int credit = Main.getGappleAPI().getGappleKill(args[1]);
//            sender.sendMessage(Main.info("&a&o" + args[1] + "&7的&7&lKarCredit&7： &f&l" + credit));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " get <玩家名>"));
//          return true;
//        }
//        if (args[0].equalsIgnoreCase("reload")) {
//          Utils.loadFiles(sender);
//        }
//        if (args[0].equalsIgnoreCase("set")) {
//          if (args.length > 2) {
//            try {
//              int credit = Integer.parseInt(args[2]);
//              if (Main.getGappleAPI().setGappleKill(args[1], credit)) {
//                sender.sendMessage(Main.info("&7将&a&o" + args[1] + "&7的&7&lKarCredit&7设为 &f&l" + credit));
//                return true;
//              }
//            } catch (NumberFormatException ex) {
//              sender.sendMessage(Main.info("&4必须是数字"));
//              return true;
//            }
//            sender.sendMessage(Main.info("&4数字过小或过大"));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " set <玩家名> <acount>"));
//          return true;
//        }
//        if (args[0].equalsIgnoreCase("add")) {
//          if (args.length > 2) {
//            try {
//              int credit = Integer.parseInt(args[2]);
//              if (Main.getGappleAPI().addGappleKill(args[1], credit)) {
//                sender.sendMessage(Main.info("&7将&a&o" + args[1] + "&7的&7&lKarCredit&7设为 &f&l" + Main.getGappleAPI().getGappleKill(args[1])));
//                return true;
//              }
//            } catch (NumberFormatException ex) {
//              sender.sendMessage(Main.info("&4必须是数字"));
//              return true;
//            }
//            sender.sendMessage(Main.info("&4数字过小或过大"));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " add <玩家名> <acount>"));
//          return true;
//        }
//        if ((args.length == 3) && (args[0].equalsIgnoreCase("remove"))) {
//          if (args.length > 2) {
//            try {
//              int credit = Integer.parseInt(args[2]);
//              if (Main.getGappleAPI().removeGappleKill(args[1], credit)) {
//                sender.sendMessage(Main.info("&7将&a&o" + args[1] + "&7的&7&lKarCredit&7设为 &f&l" + Main.getGappleAPI().getGappleKill(args[1])));
//                return true;
//              }
//            } catch (NumberFormatException ex) {
//              sender.sendMessage(Main.info("必须是数字"));
//              return true;
//            }
//            sender.sendMessage(Main.info("数字过小或过大"));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " take <玩家名> <acount>"));
//          return true;
//        }
//      }
//      sender.sendMessage(Main.info("&e/" + label + " get <玩家名>"));
//      sender.sendMessage(Main.info("&e/" + label + " set <玩家名> <acount>"));
//      sender.sendMessage(Main.info("&e/" + label + " add <玩家名> <acount>"));
//      sender.sendMessage(Main.info("&e/" + label + " remove <玩家名> <acount>"));
//      sender.sendMessage(Main.info("&e/" + label + " reload"));
//      return true;
//    }
//    if (cmd.getName().equalsIgnoreCase("KarCredit")) {
//      if ((sender instanceof Player)) {
//        sender.sendMessage(Main.info("&7剩余 &f&l" + Main.getGappleAPI().getGappleKill(sender.getName()))+" §7§lKarCredit");
//        return true;
//      }
//      sender.sendMessage(Main.info("&4必须是玩家,管理命令请输入/KarCreditAdmin或/KCTA"));
//      return true;
//    }
//    
//    if (cmd.getName().equalsIgnoreCase("KarKarmaAdmin")) {
//      if (args.length > 0) {
//        if (args[0].equalsIgnoreCase("get")) {
//          if (args.length > 1) {
//            int karma = Main.getKarmaAPI().getKarma(args[1]);
//            sender.sendMessage(Main.info("&a&o" + args[1] + "&7的&7&lKarma&7： &f&l" + karma));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " get <玩家名>"));
//          return true;
//        }
//        if (args[0].equalsIgnoreCase("reload")) {
//          Utils.loadFiles(sender);
//        }
//        if (args[0].equalsIgnoreCase("set")) {
//          if (args.length > 2) {
//            try {
//              int karma = Integer.parseInt(args[2]);
//              if (Main.getKarmaAPI().setKarma(args[1], karma)) {
//                sender.sendMessage(Main.info("&7将&a&o" + args[1] + "&7的&7&lKarma&7设为 &f&l" + karma));
//                return true;
//              }
//            } catch (NumberFormatException ex) {
//              sender.sendMessage(Main.info("&4必须是数字"));
//              return true;
//            }
//            sender.sendMessage(Main.info("&4数字过小或过大"));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " set <玩家名> <acount>"));
//          return true;
//        }
//        if (args[0].equalsIgnoreCase("add")) {
//          if (args.length > 2) {
//            try {
//              int karma = Integer.parseInt(args[2]);
//              if (Main.getKarmaAPI().addKarma(args[1], karma)) {
//                sender.sendMessage(Main.info("&7将&a&o" + args[1] + "&7的&7&lKarma&7设为 &f&l" + Main.getKarmaAPI().getKarma(args[1])));
//                return true;
//              }
//            } catch (NumberFormatException ex) {
//              sender.sendMessage(Main.info("&4必须是数字"));
//              return true;
//            }
//            sender.sendMessage(Main.info("&4数字过小或过大"));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " add <玩家名> <acount>"));
//          return true;
//        }
//        if ((args.length == 3) && (args[0].equalsIgnoreCase("remove"))) {
//          if (args.length > 2) {
//            try {
//              int karma = Integer.parseInt(args[2]);
//              if (Main.getKarmaAPI().takeKarma(args[1], karma)) {
//                sender.sendMessage(Main.info("&7将&a&o" + args[1] + "&7的&7&lKarma&7设为 &f&l" + Main.getKarmaAPI().getKarma(args[1])));
//                return true;
//              }
//            } catch (NumberFormatException ex) {
//              sender.sendMessage(Main.info("必须是数字"));
//              return true;
//            }
//            sender.sendMessage(Main.info("数字过小或过大"));
//            return true;
//          }
//          sender.sendMessage(Main.info("&e/" + label + " take <玩家名> <acount>"));
//          return true;
//        }
//      }
//      sender.sendMessage(Main.info("&e/" + label + " get <玩家名>"));
//      sender.sendMessage(Main.info("&e/" + label + " set <玩家名> <acount>"));
//      sender.sendMessage(Main.info("&e/" + label + " add <玩家名> <acount>"));
//      sender.sendMessage(Main.info("&e/" + label + " remove <玩家名> <acount>"));
//      sender.sendMessage(Main.info("&e/" + label + " reload"));
//      return true;
//    }
//    if (cmd.getName().equalsIgnoreCase("KarKarma")) {
//      if ((sender instanceof Player)) {
//        sender.sendMessage(Main.info("&7剩余 &f&l" + Main.getKarmaAPI().getKarma(sender.getName()))+" §7§lKarma");
//        return true;
//      }
//      sender.sendMessage(Main.info("&4必须是玩家,管理命令请输入/KarKarmaAdmin或/KKA"));
//      return true;
//    }
//    return true;
  }
    public static String getKDR(int kill,int death){
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
}