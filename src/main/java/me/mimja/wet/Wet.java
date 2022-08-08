package me.mimja.wet;

import me.mimja.wet.logic.PlayerOnDeath;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class Wet extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pm = getServer().getPluginManager();
        PlayerOnDeath listener = new PlayerOnDeath(this);
        pm.registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
