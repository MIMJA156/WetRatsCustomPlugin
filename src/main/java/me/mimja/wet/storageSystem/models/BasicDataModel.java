package me.mimja.wet.storageSystem.models;

public class BasicDataModel {
    private String playerId;

    public BasicDataModel(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
