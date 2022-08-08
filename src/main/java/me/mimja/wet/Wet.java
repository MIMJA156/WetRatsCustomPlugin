package me.mimja.wet;

import me.mimja.wet.logic.PlayerOnDeath;
import me.mimja.wet.storageSystem.StorageTools;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class Wet extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new PlayerOnDeath();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
