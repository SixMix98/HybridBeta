package io.github.SixMix98.hybridbeta.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin extends TransparentBlock {

    public LeavesBlockMixin(int id, int textureId) {
        super(id, textureId, Material.LEAVES, false);
        this.setTickRandomly(true);
    }

    // Leaves can sometimes drop apples
    public int getDroppedItemId(int blockMeta, Random random) {
        return random.nextInt(11) == 0 ? Item.APPLE.id : Block.SAPLING.id;
    }
}
