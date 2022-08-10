package me.mimja.wet.storage.models;

import java.util.UUID;

public class PlayerDeathModel extends BasicDataModel {
    Integer playerDeaths;

    public PlayerDeathModel(UUID playerId, Integer playerDeaths) {
        super(playerId);
        this.playerDeaths = playerDeaths;
    }

    public Integer getPlayerDeaths() {
        return playerDeaths;
    }

    public void setPlayerDeaths(Integer playerDeaths) {
        this.playerDeaths = playerDeaths;
    }
}
