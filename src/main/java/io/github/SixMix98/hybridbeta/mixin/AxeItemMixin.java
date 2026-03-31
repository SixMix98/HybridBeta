package io.github.SixMix98.hybridbeta.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AxeItem.class)
public class AxeItemMixin extends ToolItem {
    private static Block[] axeEffectiveBlocks;

    public AxeItemMixin(int id, ToolMaterial toolMaterial) {
        super(id, 2, toolMaterial, axeEffectiveBlocks);
    }

    // Expands which blocks can be mined by axes
    static {
        axeEffectiveBlocks = new Block[]{Block.PLANKS, Block.BOOKSHELF, Block.LOG, Block.CHEST, Block.WOODEN_STAIRS, Block.CRAFTING_TABLE, Block.FENCE, Block.SIGN, Block.DOOR, Block.TRAPDOOR, Block.FENCE};
    }
}
