package me.mimja.wet.areas;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

public class BasicAreasStructure {
    public AreasModel structureReader(Material[][][] structure, Location headLocation){
        ArrayList<Location> validLocations = new ArrayList<Location>();
        ArrayList<Material> validMaterials = new ArrayList<Material>();

        for (int i = 0; i < structure.length; i++) {
            Location location_a = headLocation.clone();
            location_a.add(-1, (i + 1) * -1, 1);
            Location location_b = location_a.clone();

            for (int j = 0; j < structure[i].length; j++) {
                int secondLoopXValue = 0;
                if(j != 0) secondLoopXValue = 1;

                location_b.add(secondLoopXValue, 0, 0);
                Location location_c = location_b.clone();

                Bukkit.broadcastMessage(getCords(location_b));
                validLocations.add(location_b.clone());
                validMaterials.add(structure[i][j][0]);
                for (int k = 0; k < structure[i][j].length - 1; k++) {
                    location_c.add(0, 0, -1);

                    Bukkit.broadcastMessage(getCords(location_c));
                    validLocations.add(location_c.clone());
                    validMaterials.add(structure[i][j][i + 1]);
                }
            }
        }

        return new AreasModel(validLocations, validMaterials);
    }

    private String getCords(Location loc){
        return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ();
    }
}
