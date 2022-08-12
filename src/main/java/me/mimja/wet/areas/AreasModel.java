package me.mimja.wet.areas;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

public class AreasModel {
    private ArrayList<Location> validLocations;
    private ArrayList<Material> validMaterials;

    public AreasModel(ArrayList<Location> validLocations, ArrayList<Material> validMaterials) {
        this.validLocations = validLocations;
        this.validMaterials = validMaterials;
    }

    public ArrayList<Location> getValidLocations() {
        return validLocations;
    }

    public void setValidLocations(ArrayList<Location> validLocations) {
        this.validLocations = validLocations;
    }

    public ArrayList<Material> getValidMaterials() {
        return validMaterials;
    }

    public void setValidMaterials(ArrayList<Material> validMaterials) {
        this.validMaterials = validMaterials;
    }
}
