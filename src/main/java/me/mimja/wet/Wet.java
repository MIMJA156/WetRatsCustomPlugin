package me.mimja.wet;

import me.mimja.wet.logic.PlayerOnDeath;
import me.mimja.wet.storageSystem.StorageTools;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.io.IOException;

public final class Wet extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        try {
            StorageTools.PlayerDeath.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PluginManager pm = getServer().getPluginManager();
        PlayerOnDeath listener = new PlayerOnDeath(this);
        pm.registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
