package me.mimja.wet.storageSystem;
import me.mimja.wet.storageSystem.models.PlayerDeathModel;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class StorageTools {
    public static class PlayerDeath {
        private static ArrayList<PlayerDeathModel> playerDeathLocalStored = new ArrayList<>();

        public static PlayerDeathModel generate(Player player, Integer deaths) {
            Location playerLocation = player.getLocation();
            return new PlayerDeathModel(player.getUniqueId(), deaths, playerLocation.getX(), playerLocation.getY(), playerLocation.getZ());
        }

        public static PlayerDeathModel create(PlayerDeathModel newDeath){
            playerDeathLocalStored.add(newDeath);
            return newDeath;
        }

        public static PlayerDeathModel get(UUID playerId){
            for (PlayerDeathModel currentDeath : playerDeathLocalStored) {
                if (currentDeath.getPlayerId().equals(playerId)) {
                    return currentDeath;
                }
            }
            return null;
        }

        public static PlayerDeathModel update(PlayerDeathModel newDeath){
            for (PlayerDeathModel currentDeath : playerDeathLocalStored) {
                if (currentDeath.getPlayerId().equals(newDeath.getPlayerId())) {
                    currentDeath.setDeaths(currentDeath.getDeaths() + 1);
                    currentDeath.setDeathX(newDeath.getDeathX());
                    currentDeath.setDeathY(newDeath.getDeathY());
                    currentDeath.setDeathZ(newDeath.getDeathZ());
                    return newDeath;
                }
            }
            return null;
        }

        private static void save(){

        }
    }
}
