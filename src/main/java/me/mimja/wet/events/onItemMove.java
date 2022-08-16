package me.mimja.wet.events;

import me.mimja.wet.Wet;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class onItemMove implements Listener {
    public onItemMove(Wet wet) {}

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event){
        if(event.getCurrentItem() != null){
            if(event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
                if(event.getInventory().getType().equals(InventoryType.ENDER_CHEST) || event.getInventory().getType().equals(InventoryType.SHULKER_BOX)){
                    event.setCancelled(true);
                }
            }
        }
    }
}

//                ItemStack currentItem = event.getCurrentItem();
//                if(currentItem.getItemMeta() instanceof BlockStateMeta){
//                    BlockStateMeta im = (BlockStateMeta) currentItem.getItemMeta();
//                    if(im.getBlockState() instanceof ShulkerBox){
//                        ShulkerBox shulker = (ShulkerBox) im.getBlockState();
//                        if(shulker.getInventory().contains(Material.PLAYER_HEAD)){
//                            if(event.getInventory().getType().equals(InventoryType.ENDER_CHEST)){
//                                event.setCancelled(true);
//                            }
//                        }
//                    }
//                }
