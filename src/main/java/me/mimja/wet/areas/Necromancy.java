package me.mimja.wet.areas;

import org.bukkit.Location;
import org.bukkit.Material;

public class Necromancy extends BasicAreasStructure{
    static Material[][][] structure = {
            {
                    {null, null, null},
                    {null, Material.LAPIS_BLOCK, null},
                    {null, null, null}

            },
            {
                    {Material.GOLD_BLOCK, Material.DIAMOND, Material.GOLD_BLOCK},
                    {Material.DIAMOND, Material.IRON_BLOCK, Material.DIAMOND},
                    {Material.GOLD_BLOCK, Material.DIAMOND, Material.GOLD_BLOCK}
            }
    };

    public static Material[][][] getStructure() {
        return structure;
    }

    public AreasModel render(Location location){
        return structureReader(structure, location);
    }
}
