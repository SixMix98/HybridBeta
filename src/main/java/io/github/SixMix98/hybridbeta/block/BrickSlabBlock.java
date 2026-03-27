package io.github.SixMix98.hybridbeta.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
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
            Block fullSlab = BlockRegistry.INSTANCE.get(this.id+1);
            if (fullSlab != null) {
                world.setBlock(x, y, z, 0);
                world.setBlock(x, y-1, z, fullSlab.id);
            }
        }
    }

    public int getDroppedItemId(int blockMeta, Random random) {
        return Item.BRICK.id;
    }

}
