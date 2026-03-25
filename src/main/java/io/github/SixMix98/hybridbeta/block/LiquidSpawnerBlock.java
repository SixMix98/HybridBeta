package io.github.SixMix98.hybridbeta.block;

import net.minecraft.block.LiquidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateLiquidBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class LiquidSpawnerBlock extends TemplateLiquidBlock {
    public LiquidSpawnerBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    public void onTick(World world, int x, int y, int z, Random random) {
        int[] offsets = {-1, 1};
        for (int dx : offsets) {
            for (int dz : offsets) {
                if (world.getBlockId(x + dx, y, z + dz) == 0) {
                    world.setBlock(x + dx, y, z + dz, (this.material == Material.WATER ? FLOWING_WATER.id : FLOWING_LAVA.id));
                }
            }
        }
    }
}
