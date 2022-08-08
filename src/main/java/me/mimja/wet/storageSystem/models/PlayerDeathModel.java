package me.mimja.wet.storageSystem.models;

import java.util.UUID;

public class PlayerDeathModel {
    UUID playerId;
    Integer deaths;
    Double deathX;
    Double deathY;
    Double deathZ;

    public PlayerDeathModel(UUID playerId, Integer deaths, Double deathX, Double deathY, Double deathZ) {
        this.playerId = playerId;
        this.deaths = deaths;
        this.deathX = deathX;
        this.deathY = deathY;
        this.deathZ = deathZ;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Double getDeathX() {
        return deathX;
    }

    public void setDeathX(Double deathX) {
        this.deathX = deathX;
    }

    public Double getDeathY() {
        return deathY;
    }

    public void setDeathY(Double deathY) {
        this.deathY = deathY;
    }

    public Double getDeathZ() {
        return deathZ;
    }

    public void setDeathZ(Double deathZ) {
        this.deathZ = deathZ;
    }
}
