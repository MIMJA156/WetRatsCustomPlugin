package me.mimja.wet.events;

import me.mimja.wet.Wet;
import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class onHeadDestroy implements Listener {
    public onHeadDestroy(Wet wet) {}
    ArrayList<Item> droppedItems = new ArrayList<Item>();

    @EventHandler
    public void itemDropped(PlayerDropItemEvent event){
        Item item = event.getItemDrop();
        Player player = event.getPlayer();
        if(item.getItemStack().getType().equals(Material.PLAYER_HEAD)){
            droppedItems.add(item);

            item.setInvulnerable(true);

            Timer itemDroppedTimer = new Timer();
            TimerTask itemDroppedTask = new TimerTask() {
                @Override
                public void run() {
                    if(droppedItems.contains(item)){
                        item.setTicksLived(5 * 1000 * 60 + 2);

                        Bukkit.broadcastMessage(hasAvailableSlot(player) + "");
                        if(hasAvailableSlot(player)){
                            player.getInventory().addItem(item.getItemStack());
                            player.sendMessage(ChatColor.GREEN + "Player head returned!");
                        }else{
                            PlayerDeathModel playerDeathModel = StorageTools.PlayerDeath.get(player.getUniqueId());
                            World world = Bukkit.getWorld(playerDeathModel.getWorldId());
                            Location deathLocation = new Location(world, playerDeathModel.getX(), playerDeathModel.getY(), playerDeathModel.getZ());

                            deathLocation.getBlock().setType(Material.PLAYER_HEAD);
                        }
                    }
                }
            };

            itemDroppedTimer.schedule(itemDroppedTask, 5000);
        }
    }

    @EventHandler
    public void playerPickUpItem(EntityPickupItemEvent event){
        if(event.getEntity().getType().equals(EntityType.PLAYER)){
            Item item = event.getItem();
            droppedItems.remove(item);
        }
    }

    @EventHandler
    public void playerHeadBurnt(BlockFromToEvent event){
        if(event.getToBlock().getType().equals(Material.PLAYER_HEAD)){
            event.setCancelled(true);
        }
    }

    public boolean hasAvailableSlot(Player player){
        Inventory inv = player.getInventory();
        for (ItemStack item: inv.getStorageContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }
}
