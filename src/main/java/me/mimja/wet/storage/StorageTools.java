package me.mimja.wet.storage;
import com.google.gson.Gson;
import me.mimja.wet.Wet;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class StorageTools {
    public static class PlayerDeath {
        private static ArrayList<PlayerDeathModel> playerDeathLocalStored = new ArrayList<>();

        public static PlayerDeathModel generate(Player player, Integer deaths) {
            Location loc = player.getLocation();
            return new PlayerDeathModel(player.getUniqueId(), deaths, loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().toString());
        }

        public static PlayerDeathModel create(PlayerDeathModel newDeath){
            playerDeathLocalStored.add(newDeath);

            try {
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newDeath;
        }

        public static PlayerDeathModel get(UUID playerId){
            for (PlayerDeathModel currentDeathModel : playerDeathLocalStored) {
                if (currentDeathModel.getPlayerId().equals(playerId)) {
                    return currentDeathModel;
                }
            }
            return null;
        }

        public static PlayerDeathModel update(PlayerDeathModel newDeath){
            for (PlayerDeathModel currentDeath : playerDeathLocalStored) {
                if (currentDeath.getPlayerId().equals(newDeath.getPlayerId())) {
                    currentDeath.setPlayerDeaths(newDeath.getPlayerDeaths());

                    try {
                        save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return newDeath;
                }
            }
            return null;
        }

        public static void load() throws IOException {
            Gson gson = new Gson();
            File file = new File(Wet.getPlugin().getDataFolder().getAbsolutePath() + "/PlayerDeathModelDB.json");
            if (file.exists()){
                Reader reader = new FileReader(file);
                PlayerDeathModel[] n = gson.fromJson(reader, PlayerDeathModel[].class);
                if(n != null){
                    playerDeathLocalStored = new ArrayList<>(Arrays.asList(n));
                }
                reader.close();
            }else{
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        }

        private static void save() throws IOException {
            Gson gson = new Gson();
            File file = new File(Wet.getPlugin().getDataFolder().getAbsolutePath() + "/PlayerDeathModelDB.json");

            Writer writer = new FileWriter(file, false);
            gson.toJson(playerDeathLocalStored, writer);
            writer.flush();
            writer.close();
        }
    }
}
