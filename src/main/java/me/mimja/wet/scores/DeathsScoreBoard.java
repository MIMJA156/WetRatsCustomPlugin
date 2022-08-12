package me.mimja.wet.scores;

import me.mimja.wet.storage.StorageTools;
import me.mimja.wet.storage.models.PlayerDeathModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class DeathsScoreBoard {
    public static void init(){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("wet_lives_counter", "dummy", "lives");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);

        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            PlayerDeathModel playerDeathModel = StorageTools.PlayerDeath.get(player.getUniqueId());
            int playerDeaths;

            if(playerDeathModel != null){
                playerDeaths = playerDeathModel.getPlayerDeaths() - 10;
            }else{
                playerDeaths = 10;
            }

            objective.getScore(player.getName()).setScore(playerDeaths);
            player.setScoreboard(scoreboard);
        });
    }

    public static void update(Player player, Integer newValue){
        player.getScoreboard().getObjective("wet_lives_counter").getScore(player.getName()).setScore(newValue);
    }
}
