package me.mimja.wet.storage.models;

import java.util.UUID;

public class BasicDataModel {
    private UUID playerId;

    public BasicDataModel(UUID playerId) {
        this.playerId = playerId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}
