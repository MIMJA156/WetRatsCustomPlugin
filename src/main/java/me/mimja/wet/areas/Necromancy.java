package me.mimja.wet.areas;

import org.bukkit.Location;
import org.bukkit.Material;

public class Necromancy extends AreaMapRenderer {
    static Integer XOffset = -1;
    static Integer YOffset = -1;
    static Integer ZOffset = 1;

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

    public AreasModel render(Location location){
        return structureReader(structure, XOffset, YOffset, ZOffset, location);
    }
}
