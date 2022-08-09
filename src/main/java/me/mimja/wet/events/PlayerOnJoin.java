package me.mimja.wet.events;

import me.mimja.wet.Wet;
import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerOnJoin implements Listener {
    public PlayerOnJoin(Wet wet) {}

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){
        PlayerDeathModel data = StorageTools.PlayerDeath.get(event.getPlayer().getUniqueId());
        if(data != null && event.getPlayer().getGameMode().equals(GameMode.SPECTATOR) && data.getDeaths() <= 0){
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }
}
