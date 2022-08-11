package me.mimja.wet.scores;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

public class DeathsScoreBoard {
    public static void init(){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("wet_player_lives", "dummy", "lol");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            Score score = objective.getScore(objective.getDisplayName());
            score.setScore(1);
            player.setScoreboard(scoreboard);
        });
    }

    public void update(Integer newValue){

    }
}
