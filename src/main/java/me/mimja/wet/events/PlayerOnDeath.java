package me.mimja.wet.events;

import me.mimja.wet.Wet;
import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerOnDeath implements Listener {
    public PlayerOnDeath(Wet wet) {}

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();
        assert player != null;

        PlayerInventory drops = player.getInventory();

        if(drops.contains(Material.PLAYER_HEAD)){
            Bukkit.broadcastMessage("cool");
            for (int i = 0; i < drops.getSize(); i++) {
                ItemStack itemInSlot = drops.getItem(i);
                if(itemInSlot != null && itemInSlot.getType().equals(Material.PLAYER_HEAD)){
                    Item itemDropped = Bukkit.getWorld(player.getWorld().getUID()).dropItemNaturally(player.getLocation(), itemInSlot);
                    itemDropped.setInvulnerable(true);
                    itemDropped.setUnlimitedLifetime(true);
                    event.getDrops().remove(i);
                }
            }
        }

        PlayerDeathModel playerData = StorageTools.PlayerDeath.get(player.getUniqueId());
        if(playerData != null){
            PlayerDeathModel playerDeath = StorageTools.PlayerDeath.generate(player, playerData.getPlayerDeaths() + 1);
            StorageTools.PlayerDeath.update(playerDeath);
        }else{
            PlayerDeathModel playerDeath = StorageTools.PlayerDeath.generate(player, 1);
            StorageTools.PlayerDeath.create(playerDeath);
        }

        if(playerData != null && !player.getGameMode().equals(GameMode.SPECTATOR) && playerData.getPlayerDeaths() >= 10){
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