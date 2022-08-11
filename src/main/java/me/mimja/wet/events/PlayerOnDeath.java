package me.mimja.wet.events;

import me.mimja.wet.Wet;
import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerOnDeath implements Listener {
    public PlayerOnDeath(Wet wet) {}

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();
        assert player != null;
        PlayerDeathModel playerData = StorageTools.PlayerDeath.get(player.getUniqueId());
        if(playerData != null){
            PlayerDeathModel playerDeath = StorageTools.PlayerDeath.generate(player, playerData.getPlayerDeaths() + 1);
            StorageTools.PlayerDeath.update(playerDeath);
        }else{
            PlayerDeathModel playerDeath = StorageTools.PlayerDeath.generate(player, 1);
            StorageTools.PlayerDeath.create(playerDeath);
        }

        PlayerDeathModel data = StorageTools.PlayerDeath.get(player.getUniqueId());
        if(data != null && !player.getGameMode().equals(GameMode.SPECTATOR) && data.getPlayerDeaths() >= 10){
            player.setGameMode(GameMode.SPECTATOR);

            Block block = player.getLocation().getBlock();
            block.setType(Material.PLAYER_HEAD);

            Skull skull = (Skull) block.getState();
            skull.setOwningPlayer(event.getEntity().getPlayer());
            skull.update(true);

            block.getWorld().strikeLightningEffect(block.getLocation().add(.5,0,.5));

            event.setDeathMessage(event.getDeathMessage() + ChatColor.GOLD + " and is now a ghost.");
        }
    }
}
