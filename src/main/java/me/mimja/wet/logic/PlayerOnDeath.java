package me.mimja.wet.logic;

import me.mimja.wet.Wet;
import me.mimja.wet.storageSystem.StorageTools;
import me.mimja.wet.storageSystem.models.PlayerDeathModel;
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
    }
}
