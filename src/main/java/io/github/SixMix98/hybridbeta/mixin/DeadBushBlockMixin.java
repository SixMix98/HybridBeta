package io.github.SixMix98.hybridbeta.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.PlantBlock;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(DeadBushBlock.class)
public class DeadBushBlockMixin extends PlantBlock {
    public DeadBushBlockMixin(int i, int j) {
        super(i, j);
        float var3 = 0.4F;
        this.setBoundingBox(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
    }

    // Dead bush is harvestable
    public int getDroppedItemId(int blockMeta, Random random) {

        return Block.DEAD_BUSH.id;
    }
}
