package me.mimja.wet.events;

import me.mimja.wet.Wet;
import me.mimja.wet.areas.AreasModel;
import me.mimja.wet.areas.Necromancy;
import me.mimja.wet.scores.DeathsScoreBoard;
import me.mimja.wet.storage.StorageTools;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class PlayerOnBlockPlace implements Listener {
    public PlayerOnBlockPlace(Wet wet) {}

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event){
        Block block = event.getBlock();

        if(block.getType().equals(Material.PLAYER_HEAD)){
            Skull skull = (Skull) block.getState();
            OfflinePlayer revivedPlayer = skull.getOwningPlayer();

            if(revivedPlayer == null) return;
            Integer playerDeaths = StorageTools.PlayerDeath.get(revivedPlayer.getUniqueId()).getPlayerDeaths();
            if(playerDeaths < 10) return;

            Location headLocation = block.getLocation();

            boolean validSummonArea = false;
            AreasModel areasModel = new Necromancy().render(headLocation);

            ArrayList<Location> validLocations = areasModel.getValidLocations();
            ArrayList<Material> validMaterials = areasModel.getValidMaterials();

            for (int i = 0; i < validLocations.size(); i++) {
                validSummonArea = validLocations.get(i).getBlock().getType().equals(validMaterials.get(i));
            }

            if(validSummonArea){
                StorageTools.PlayerDeath.update(StorageTools.PlayerDeath.generate(revivedPlayer.getPlayer(), 0));
                headLocation.getWorld().strikeLightningEffect(headLocation.add(.5,0,.5));

                headLocation.getBlock().setType(Material.AIR);
                validLocations.forEach(location -> {
                    if(!location.getBlock().getType().equals(Material.AIR)) location.getBlock().setType(Material.AIR);
                });

                revivedPlayer.getPlayer().teleport(headLocation);
                PlayerOnSpawn.checkIfShouldBeInSurvival(revivedPlayer.getPlayer());

                try {
                    Thread.sleep(100);
                    DeathsScoreBoard.update(revivedPlayer.getPlayer(), 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                event.getPlayer().sendMessage(ChatColor.RED + "Invalid summon area!");
            }
        }
    }
}
