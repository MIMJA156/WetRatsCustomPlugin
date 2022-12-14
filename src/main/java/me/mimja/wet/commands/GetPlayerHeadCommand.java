package me.mimja.wet.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GetPlayerHeadCommand extends SubCommand {
    @Override
    public String getName() {
        return "get";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Gets the players head in case of emergency.";
    }

    @Override
    public String getSyntax() {
        return null;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player1 = (Player) sender;

        if(player1.isOp()){
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();

            Player player2 = (Player) sender;
            if(args[1] != null) player2 = Bukkit.getPlayer(args[1]);

            meta.setOwningPlayer(player2);
            skull.setItemMeta(meta);
            player1.getInventory().addItem(skull);
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
