package me.mimja.wet.areas;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

public class AreaMapRenderer {
    public AreasModel structureReader(Material[][][] structure, int XOffset, int YOffset, int ZOffset, Location headLocation){
        ArrayList<Location> validLocations = new ArrayList<Location>();
        ArrayList<Material> validMaterials = new ArrayList<Material>();

        if(YOffset < 0) YOffset = YOffset * -1;

        for (int i = 0; i < structure.length; i++) {
            Location location_a = headLocation.clone();
            location_a.add(XOffset, (i + YOffset) * -1, ZOffset);
            Location location_b = location_a.clone();

            for (int j = 0; j < structure[i].length; j++) {
                int secondLoopXValue = 0;
                if(j != 0) secondLoopXValue = 1;

                location_b.add(secondLoopXValue, 0, 0);
                Location location_c = location_b.clone();

                validLocations.add(location_b.clone());
                validMaterials.add(structure[i][j][0]);
                for (int k = 0; k < structure[i][j].length - 1; k++) {
                    location_c.add(0, 0, -1);

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
