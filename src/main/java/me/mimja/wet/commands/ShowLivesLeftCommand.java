package me.mimja.wet.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ShowLivesLeftCommand extends SubCommand {

    @Override
    public String getName() {
        return "lives";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("li");
        return list;
    }

    @Override
    public String getDescription() {
        return "Gets the number of lives.";
    }

    @Override
    public String getSyntax() {
        return "/wet lives";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length >= 2){
            Player p;
            try {
                p = Bukkit.getPlayer(args[1]);
            }catch (Error e){
                player.sendMessage(ChatColor.RED + "Invalid Argument.");
                return;
            }

            PlayerDeathModel data = StorageTools.PlayerDeath.get(p.getUniqueId());

            if (data == null){
                player.sendMessage(ChatColor.AQUA + args[1] + " has 10 live(s) left!");
            }else {
                if(data.getPlayerDeaths() >= 10){
                    player.sendMessage(ChatColor.AQUA + args[1] + " is dead...");
                }else {
                    player.sendMessage(ChatColor.AQUA + args[1] + " has " + (10 - data.getPlayerDeaths()) + " live(s) left!");
                }
            }
        }else{
            PlayerDeathModel data = StorageTools.PlayerDeath.get(player.getUniqueId());

            if (data == null){
                player.sendMessage(ChatColor.AQUA + "You have 10 live(s) left!");
            }else {
                if(data.getPlayerDeaths() >= 10){
                    player.sendMessage(ChatColor.AQUA +"You have no more lives...");
                }else{
                    player.sendMessage(ChatColor.AQUA + "You have " + (10 - data.getPlayerDeaths()) + " live(s) left!");
                }
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        Bukkit.getServer().getOnlinePlayers().forEach(p -> {
            list.add(p.getName());
        });
        return list;
    }
}
