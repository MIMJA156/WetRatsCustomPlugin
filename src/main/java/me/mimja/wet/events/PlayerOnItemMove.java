package me.mimja.wet.events;

import me.mimja.wet.Wet;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;

public class PlayerOnItemMove implements Listener {
    public PlayerOnItemMove(Wet wet) {}

    @EventHandler
    public void inventoryMoveItemEvent(InventoryMoveItemEvent event){
        if(event.getItem().getType().equals(Material.PLAYER_HEAD)){
            if(!event.getDestination().getType().equals(InventoryType.CHEST)
                    &&  event.getDestination().getType().equals(InventoryType.BARREL)){
                event.setCancelled(true);
            }
        }
    }
}
