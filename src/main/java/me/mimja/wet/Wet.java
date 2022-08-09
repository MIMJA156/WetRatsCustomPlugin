package me.mimja.wet;

import me.kodysimpson.simpapi.command.CommandManager;
import me.mimja.wet.commands.ShowLivesLeftCommand;
import me.mimja.wet.events.PlayerOnDeath;
import me.mimja.wet.events.PlayerOnJoin;
import me.mimja.wet.events.PlayerOnReSpawn;
import me.mimja.wet.storage.StorageTools;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Wet extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        //Load the stored death models
        try {
            StorageTools.PlayerDeath.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Register Death Listener
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerOnDeath(this), this);
        pm.registerEvents(new PlayerOnJoin(this), this);
        pm.registerEvents(new PlayerOnReSpawn(this), this);

        //Register Commands
        try {
            CommandManager.createCoreCommand(this, "wet", "A plugin custom plugin made by Mimja156", "/wet", null, ShowLivesLeftCommand.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
