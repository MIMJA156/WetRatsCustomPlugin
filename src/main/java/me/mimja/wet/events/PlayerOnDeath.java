package me.mimja.wet.events;

import me.mimja.wet.Wet;
import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.GameMode;
import org.bukkit.Location;
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
            PlayerDeathModel playerDeath = StorageTools.PlayerDeath.generate(player, playerData.getDeaths() + 1);
            StorageTools.PlayerDeath.update(playerDeath);
        }else{
            PlayerDeathModel playerDeath = StorageTools.PlayerDeath.generate(player, 1);
            StorageTools.PlayerDeath.create(playerDeath);
        }

        PlayerDeathModel data = StorageTools.PlayerDeath.get(player.getPlayer().getUniqueId());
        if(data != null && !player.getPlayer().getGameMode().equals(GameMode.SPECTATOR) && data.getDeaths() >= 10){
            player.getPlayer().setGameMode(GameMode.SPECTATOR);
            Location location = new Location(event.getEntity().getPlayer().getLastDeathLocation().getWorld(), data.getDeathX(), data.getDeathY(), data.getDeathZ());

            Block b = location.getBlock();
            b.setType(Material.PLAYER_HEAD);
            Skull skull = (Skull) b.getState();
            skull.setOwningPlayer(event.getEntity().getPlayer());
            skull.update(true);
        }
    }
}
