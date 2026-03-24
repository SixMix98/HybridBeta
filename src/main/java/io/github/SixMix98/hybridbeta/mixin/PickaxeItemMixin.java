package io.github.SixMix98.hybridbeta.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin extends ToolItem {
    private static Block[] pickaxeEffectiveBlocks;

    public PickaxeItemMixin(int id, ToolMaterial toolMaterial) {
        super(id, 2, toolMaterial, pickaxeEffectiveBlocks);
    }

    // Fixes issues with redstone blocks taking forever to mine
    static {
        pickaxeEffectiveBlocks = new Block[]{Block.COBBLESTONE, Block.DOUBLE_SLAB, Block.SLAB, Block.STONE, Block.SANDSTONE, Block.MOSSY_COBBLESTONE, Block.IRON_ORE, Block.IRON_BLOCK, Block.COAL_ORE, Block.GOLD_BLOCK, Block.GOLD_ORE, Block.DIAMOND_ORE, Block.REDSTONE_ORE, Block.LIT_REDSTONE_ORE, Block.DIAMOND_BLOCK, Block.ICE, Block.NETHERRACK, Block.LAPIS_ORE, Block.LAPIS_BLOCK, Block.COBBLESTONE_STAIRS};
    }

}
