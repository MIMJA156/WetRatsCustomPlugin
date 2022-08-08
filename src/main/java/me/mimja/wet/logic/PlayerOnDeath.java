package me.mimja.wet.logic;

import me.mimja.wet.storageSystem.StorageTools;
import me.mimja.wet.storageSystem.models.PlayerDeathModel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerOnDeath implements Listener {
    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();
        assert player != null;

        event.setDeathMessage("oof, your kind bad ngl");
        event.setNewLevel(100);

        PlayerDeathModel playerData = StorageTools.PlayerDeath.get(player.getUniqueId());

        if(playerData != null){
            PlayerDeathModel playerDeath = StorageTools.PlayerDeath.generate(player, playerData.getDeaths() + 1);
            StorageTools.PlayerDeath.update(playerDeath);
        }else{
            PlayerDeathModel playerDeath = StorageTools.PlayerDeath.generate(player, 0);
            StorageTools.PlayerDeath.create(playerDeath);
        }
    }
}
