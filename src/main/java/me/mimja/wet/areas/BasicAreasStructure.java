package me.mimja.wet.areas;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Objects;

public class BasicAreasStructure {
    public AreasModel structureReader(Material[][][] structure, Location headLocation){
        ArrayList<Location> validLocations = new ArrayList<Location>();
        ArrayList<Material> validMaterials = new ArrayList<Material>();

        for (int i = 0; i < structure.length; i++) {
            Location location_a = headLocation.clone();
            location_a.add(-1, (i + 1) * -1, 1);
            Location location_b = location_a.clone();

            for (int j = 0; j < structure[i].length; j++) {
                int lockedForJ = 0;
                if(j != 0) lockedForJ = 1;

                location_b.add(lockedForJ, 0, 0);
                Location location_c = location_b.clone();

//                if(structure[i][j][0] != null) Bukkit.broadcastMessage(getCords(location_c) + "-" + structure[i][j][0].name());
                validMaterials.add(structure[i][j][0]);
                validLocations.add(location_b);
                for (int k = 0; k < Objects.requireNonNull(structure[i][j]).length - 1; k++) {
                    location_c.add(0, 0, -1);

//                    if(structure[i][j][k+1] != null) Bukkit.broadcastMessage(getCords(location_c) + "-" + structure[i][j][k+1].name());
                    validMaterials.add(structure[i][j][k+1]);
                    validLocations.add(location_c);
                }
            }
        }

        return new AreasModel(validLocations, validMaterials);
    }

    private String getCords(Location loc){
        return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ();
    }
}
