package me.mimja.wet.events;

import me.mimja.wet.Wet;
import me.mimja.wet.storage.StorageTools;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerOnBlockPlace implements Listener {
    public PlayerOnBlockPlace(Wet wet) {}

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event){
        Block block = event.getBlock();

        if(block.getType().equals(Material.PLAYER_HEAD)){
            Skull skull = (Skull) block.getState();
            OfflinePlayer revivedPlayer = skull.getOwningPlayer();

            if(revivedPlayer == null) return;
            if(StorageTools.PlayerDeath.get(revivedPlayer.getPlayer().getUniqueId()).getPlayerDeaths() < 10) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                event.getPlayer().getInventory().remove(event.getPlayer().getInventory().getItemInMainHand());
                Bukkit.broadcastMessage(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().toString());
            }

            Location headLocation = block.getLocation();
            if(headLocation.clone().add(0, -1, 0).getBlock().getType().equals(Material.LAPIS_BLOCK)
                    && headLocation.clone().add(0, -2, 0).getBlock().getType().equals(Material.IRON_BLOCK)
                    && headLocation.clone().add(1, -2, 0).getBlock().getType().equals(Material.DIAMOND_BLOCK)
                    && headLocation.clone().add(-1, -2, 0).getBlock().getType().equals(Material.DIAMOND_BLOCK)
                    && headLocation.clone().add(0, -2, 1).getBlock().getType().equals(Material.DIAMOND_BLOCK)
                    && headLocation.clone().add(0, -2, -1).getBlock().getType().equals(Material.DIAMOND_BLOCK)
                    && headLocation.clone().add(-1, -2, 1).getBlock().getType().equals(Material.GOLD_BLOCK)
                    && headLocation.clone().add(1, -2, -1).getBlock().getType().equals(Material.GOLD_BLOCK)
                    && headLocation.clone().add(-1, -2, -1).getBlock().getType().equals(Material.GOLD_BLOCK)
                    && headLocation.clone().add(1, -2, 1).getBlock().getType().equals(Material.GOLD_BLOCK)){

                StorageTools.PlayerDeath.update(StorageTools.PlayerDeath.generate(revivedPlayer.getPlayer(), 0));

                headLocation.getWorld().strikeLightningEffect(headLocation.add(.5,0,.5));
                headLocation.getBlock().setType(Material.AIR);
                headLocation.clone().add(0, -1, 0).getBlock().setType(Material.AIR);
                headLocation.clone().add(0, -2, 0).getBlock().setType(Material.AIR);
                headLocation.clone().add(1, -2, 0).getBlock().setType(Material.AIR);
                headLocation.clone().add(-1, -2, 0).getBlock().setType(Material.AIR);
                headLocation.clone().add(0, -2, 1).getBlock().setType(Material.AIR);
                headLocation.clone().add(0, -2, -1).getBlock().setType(Material.AIR);
                headLocation.clone().add(1, -2, 1).getBlock().setType(Material.AIR);
                headLocation.clone().add(-1, -2, -1).getBlock().setType(Material.AIR);
                headLocation.clone().add(-1, -2, 1).getBlock().setType(Material.AIR);
                headLocation.clone().add(1, -2, -1).getBlock().setType(Material.AIR);

                revivedPlayer.getPlayer().teleport(headLocation);
                PlayerOnSpawn.checkIfShouldBeInSurvival(revivedPlayer.getPlayer());
            }else{
                event.getPlayer().sendMessage(ChatColor.RED + "Invalid summon area!");
            }
        }
    }
}
