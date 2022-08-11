package me.mimja.wet.storage.models;

import java.util.UUID;

public class PlayerDeathModel extends BasicDataModel {
    Integer playerDeaths;
    Double x;
    Double y;
    Double z;
    String world;

    public PlayerDeathModel(UUID playerId, Integer playerDeaths, Double x, Double y, Double z, String world) {
        super(playerId);
        this.playerDeaths = playerDeaths;
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public Integer getPlayerDeaths() {
        return playerDeaths;
    }

    public void setPlayerDeaths(Integer playerDeaths) {
        this.playerDeaths = playerDeaths;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
