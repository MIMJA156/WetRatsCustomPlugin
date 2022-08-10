package me.mimja.wet.events;

import me.mimja.wet.Wet;
import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerOnSpawn implements Listener {
    public PlayerOnSpawn(Wet wet) {}

    @EventHandler
    public void playerReSpawnEvent(PlayerRespawnEvent event){
        checkIfShouldBeInSurvival(event.getPlayer());
        checkIfShouldBeInSpectator(event.getPlayer());
        event.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "You have " + StorageTools.PlayerDeath.get(event.getPlayer().getUniqueId()) + " lives left.");
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){
        checkIfShouldBeInSurvival(event.getPlayer());
        checkIfShouldBeInSpectator(event.getPlayer());
    }

    public static void checkIfShouldBeInSurvival(Player player){
        PlayerDeathModel data = StorageTools.PlayerDeath.get(player.getUniqueId());
        if(data != null && player.getGameMode().equals(GameMode.SPECTATOR) && data.getPlayerDeaths() <= 0){
            player.setGameMode(GameMode.SURVIVAL);
        }
    }

    public static void checkIfShouldBeInSpectator(Player player){
        PlayerDeathModel data = StorageTools.PlayerDeath.get(player.getUniqueId());
        if(data != null && player.getGameMode().equals(GameMode.SURVIVAL) && data.getPlayerDeaths() >= 10){
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}
