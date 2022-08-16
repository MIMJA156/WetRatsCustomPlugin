package me.mimja.wet.events;

import me.mimja.wet.Wet;
import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class onHeadDestroy implements Listener {
    public onHeadDestroy(Wet wet) {
    }

    static ArrayList<Item> droppedItems = new ArrayList<Item>();
    static ArrayList<Player> pendingPlayers = new ArrayList<Player>();
    static ArrayList<Player> pendingInventories = new ArrayList<Player>();

    @EventHandler
    public void itemDropped(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        Player player = event.getPlayer();

        if (item.getItemStack().getType().equals(Material.PLAYER_HEAD)) {
            SkullMeta theCurrentSkull = (SkullMeta) item.getItemStack().getItemMeta();
            OfflinePlayer owningPlayer = theCurrentSkull.getOwningPlayer();

            droppedItems.add(item);
            pendingPlayers.add(player);
            item.setInvulnerable(true);

            BukkitRunnable itemDroppedRunnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if(droppedItems.contains(item)){
                        item.setTicksLived(5 * 1000 * 60 + 2);
                        if(hasAvailableSlot(player)){
                            player.getInventory().addItem(item.getItemStack());
                            player.sendMessage(ChatColor.GREEN + "Player head returned!");
                            droppedItems.remove(item);
                        }else{
                            pendingInventories.add(player);
                            player.sendMessage(ChatColor.RED + "Please open an inventory slot in the next 15 seconds!");

                            BukkitRunnable pendingPlayerRunnable = new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if(pendingInventories.contains(player)){
                                        pendingInventories.remove(player);
                                        PlayerDeathModel playerDeathModel = StorageTools.PlayerDeath.get(owningPlayer.getUniqueId());
                                        if(playerDeathModel == null) return;

                                        World world = Bukkit.getWorld(playerDeathModel.getWorldId());
                                        Location deathLocation = new Location(world, playerDeathModel.getX(), playerDeathModel.getY(), playerDeathModel.getZ());

                                        Block block = deathLocation.getBlock();

                                        block.setType(Material.PLAYER_HEAD);
                                        Skull skull = (Skull) block.getState();
                                        skull.setOwningPlayer(owningPlayer);
                                        skull.update(true);

                                        player.sendMessage(ChatColor.RED + "Times Up!");
                                        Bukkit.broadcastMessage(ChatColor.AQUA + owningPlayer.getPlayer().getDisplayName() + "'s head has returned to " + deathLocation.getBlockX() + " " + deathLocation.getBlockY() + " " + deathLocation.getBlockZ());
                                        pendingPlayers.remove(player);
                                    }else{
                                        player.getInventory().addItem(item.getItemStack());
                                        player.sendMessage(ChatColor.GREEN + "Player head returned!");
                                        droppedItems.remove(item);
                                        pendingPlayers.remove(player);
                                    }
                                }
                            };

                            pendingPlayerRunnable.runTaskLater(Wet.getPlugin(), 20 * 15);
                        }
                    }
                }
            };

            itemDroppedRunnable.runTaskLater(Wet.getPlugin(), 20 * 30);
        } else {
            if (pendingPlayers.contains(player)) {
                if (hasAvailableSlot(player)) {
                    pendingInventories.remove(player);
                } else {
                    pendingInventories.add(player);
                }
            }
        }
    }

    @EventHandler
    public void playerPickUpItem(EntityPickupItemEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            Item item = event.getItem();
            droppedItems.remove(item);
        }
    }

    @EventHandler
    public void playerHeadBurnt(BlockFromToEvent event) {
        if (event.getToBlock().getType().equals(Material.PLAYER_HEAD) || event.getToBlock().getType().equals(Material.PLAYER_WALL_HEAD)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void tntExplosion(EntityExplodeEvent event) {
        event.blockList().forEach(block -> {
            if (block.getType().equals(Material.PLAYER_HEAD) || block.getType().equals(Material.PLAYER_WALL_HEAD)) {
                event.blockList().remove(block);
            }

            if (block.getState() instanceof Container) {
                Container container = (Container) block.getState();

                if (container.getInventory().contains(Material.PLAYER_HEAD)) {
                    for (int i = 0; i < container.getInventory().getContents().length; i++) {
                        ItemStack contents = container.getInventory().getContents()[i];
                        if (contents != null && contents.getType().equals(Material.PLAYER_HEAD)) {
                            Item summonedItem = Bukkit.getWorld(container.getWorld().getUID()).dropItemNaturally(container.getLocation(), contents);
                            summonedItem.setInvulnerable(true);

                            droppedItems.add(summonedItem);

                            BukkitRunnable itemDroppedRunnable = new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if(droppedItems.contains(summonedItem)) {
                                        SkullMeta theCurrentSkull = (SkullMeta) summonedItem.getItemStack().getItemMeta();
                                        OfflinePlayer owningPlayer = theCurrentSkull.getOwningPlayer();
                                        PlayerDeathModel playerDeathModel = StorageTools.PlayerDeath.get(owningPlayer.getUniqueId());
                                        if(playerDeathModel == null) return;

                                        World world = Bukkit.getWorld(playerDeathModel.getWorldId());
                                        Location deathLocation = new Location(world, playerDeathModel.getX(), playerDeathModel.getY(), playerDeathModel.getZ());
                                        summonedItem.setTicksLived(5 * 1000 * 60 + 2);

                                        Block block = deathLocation.getBlock();

                                        block.setType(Material.PLAYER_HEAD);
                                        Skull skull = (Skull) block.getState();
                                        skull.setOwningPlayer(owningPlayer);
                                        skull.update(true);

                                        Bukkit.broadcastMessage(ChatColor.AQUA + owningPlayer.getPlayer().getDisplayName() + "'s head has returned to " + deathLocation.getBlockX() + " " + deathLocation.getBlockY() + " " + deathLocation.getBlockZ());
                                    }
                                }
                            };

                            container.getInventory().remove(Material.PLAYER_HEAD);
                            itemDroppedRunnable.runTaskLater(Wet.getPlugin(), 20 * 30);
                        }
                    }
                    container.getInventory().remove(Material.PLAYER_HEAD);
                }
            }
        });
    }

    @EventHandler
    public void playerBreakBlock(BlockBreakEvent event){
        if(event.getBlock().getState() instanceof Container){
            Container container = (Container) event.getBlock().getState();
            if (container.getInventory().contains(Material.PLAYER_HEAD)) {
                for (int i = 0; i < container.getInventory().getContents().length; i++) {
                    ItemStack contents = container.getInventory().getContents()[i];
                    if (contents != null && contents.getType().equals(Material.PLAYER_HEAD)) {
                        Item summonedItem = Bukkit.getWorld(container.getWorld().getUID()).dropItemNaturally(container.getLocation(), contents);
                        summonedItem.setInvulnerable(true);

                        droppedItems.add(summonedItem);

                        BukkitRunnable itemDroppedRunnable = new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (droppedItems.contains(summonedItem)) {
                                    SkullMeta theCurrentSkull = (SkullMeta) summonedItem.getItemStack().getItemMeta();
                                    OfflinePlayer owningPlayer = theCurrentSkull.getOwningPlayer();
                                    PlayerDeathModel playerDeathModel = StorageTools.PlayerDeath.get(owningPlayer.getUniqueId());
                                    if(playerDeathModel == null) return;

                                    World world = Bukkit.getWorld(playerDeathModel.getWorldId());
                                    Location deathLocation = new Location(world, playerDeathModel.getX(), playerDeathModel.getY(), playerDeathModel.getZ());
                                    summonedItem.setTicksLived(5 * 1000 * 60 + 2);

                                    Block block = deathLocation.getBlock();

                                    block.setType(Material.PLAYER_HEAD);
                                    Skull skull = (Skull) block.getState();
                                    skull.setOwningPlayer(owningPlayer);
                                    skull.update(true);

                                    Bukkit.broadcastMessage(ChatColor.AQUA + owningPlayer.getPlayer().getDisplayName() + "'s head has returned to " + deathLocation.getBlockX() + " " + deathLocation.getBlockY() + " " + deathLocation.getBlockZ());
                                }
                            }
                        };

                        container.getInventory().remove(Material.PLAYER_HEAD);
                        itemDroppedRunnable.runTaskLater(Wet.getPlugin(), 20 * 30);
                    }
                }
            }
        }
    }

    private boolean hasAvailableSlot(Player player) {
        Inventory inv = player.getInventory();
        for (ItemStack item : inv.getStorageContents()) {
            if (item == null) {
                return true;
            }
        }
        return false;
    }
}
