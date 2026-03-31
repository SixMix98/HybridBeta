package io.github.SixMix98.hybridbeta.block;

import io.github.SixMix98.hybridbeta.events.init.BlockListener;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.BlockTemplate;
import net.modificationstation.stationapi.api.template.block.TemplateSlabBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BrickSlabBlock extends TemplateSlabBlock implements BlockTemplate {

    public BrickSlabBlock(Identifier identifier, boolean flag) {
        super(identifier, flag);
    }

    public int getTexture(int side, int meta) {
        return 7;
    }

    public void onPlaced(World world, int x, int y, int z) {
        // Placing on top of another slab creates a double slab
        int n = world.getBlockId(x, y - 1, z);
        if (n == this.id) {
            world.setBlock(x, y, z, 0);
            world.setBlock(x, y-1, z, BlockListener.BRICK_DOUBLE_SLAB.id);
        }
    }

    public int getDroppedItemId(int blockMeta, Random random) {
        return Block.BRICKS.id;
    }

}
