package me.mimja.wet.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
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
        Player player = (Player)sender;
        PlayerDeathModel data = StorageTools.PlayerDeath.get(player.getUniqueId());

        if (data == null){
            player.sendMessage("You have 10 lives left!");
        }else {
            player.sendMessage("You have " + (10 - data.getDeaths()) + " lives left!");
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
