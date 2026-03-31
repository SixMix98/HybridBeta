package io.github.SixMix98.hybridbeta.events.init;

import io.github.SixMix98.hybridbeta.block.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.template.block.TemplateStairsBlock;

public class BlockListener {
    public static Block[] blocks;
    public static Block REDSTONE_BLOCK;
    public static Block COAL_BLOCK;
    public static Block BRICK_STAIRS;
    public static Block SANDSTONE_STAIRS;
    public static Block BRICK_SLAB;
    public static Block BRICK_DOUBLE_SLAB;
    public static Block WATER_SPAWNER;
    public static Block LAVA_SPAWNER;
    public static Block HAY_BALE;
    public static Block FENCE_GATE;
    public static Block OPEN_FENCE_GATE;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        REDSTONE_BLOCK = new RedstoneBlock(InitListener.NAMESPACE.id("redstone_block"), Material.STONE).setHardness(3.0f).setResistance(5.0f).setSoundGroup(Block.STONE_SOUND_GROUP).setTranslationKey(InitListener.NAMESPACE, "redstone_block");
        COAL_BLOCK = new CoalBlock(InitListener.NAMESPACE.id("coal_block"), Material.STONE).setHardness(3.0f).setResistance(5.0f).setSoundGroup(Block.STONE_SOUND_GROUP).setTranslationKey(InitListener.NAMESPACE, "coal_block");
        BRICK_STAIRS = new TemplateStairsBlock(InitListener.NAMESPACE.id("brick_stairs"), Block.BRICKS).ignoreMetaUpdates().setTranslationKey(InitListener.NAMESPACE, "brickStairs");
        SANDSTONE_STAIRS = new TemplateStairsBlock(InitListener.NAMESPACE.id("sandstone_stairs"), Block.SANDSTONE).ignoreMetaUpdates().setTranslationKey(InitListener.NAMESPACE, "sandstoneStairs");
        WATER_SPAWNER = new LiquidSpawnerBlock(InitListener.NAMESPACE.id("water_spawner"), Material.WATER).setHardness(100.0f).setOpacity(3).setTranslationKey(InitListener.NAMESPACE, "waterSpawner").disableTrackingStatistics().ignoreMetaUpdates();
        LAVA_SPAWNER = new LiquidSpawnerBlock(InitListener.NAMESPACE.id("lava_spawner"), Material.LAVA).setHardness(0.0f).setLuminance(1.0f).setOpacity(255).setTranslationKey(InitListener.NAMESPACE, "lavaSpawner").disableTrackingStatistics().ignoreMetaUpdates();
        BRICK_SLAB = new BrickSlabBlock(InitListener.NAMESPACE.id("brick_slab"), false).setHardness(2.0f).setResistance(10.0f).setSoundGroup(Block.STONE_SOUND_GROUP).setTranslationKey(InitListener.NAMESPACE, "brickSlab");
        BRICK_DOUBLE_SLAB = new BrickSlabBlock(InitListener.NAMESPACE.id("brick_double_slab"), true).setHardness(2.0f).setResistance(10.0f).setSoundGroup(Block.STONE_SOUND_GROUP).setTranslationKey(InitListener.NAMESPACE, "brickDoubleSlab").disableAutoItemRegistration();
        HAY_BALE = new HayBaleBlock(InitListener.NAMESPACE.id("hay_bale"), Material.PLANT).setHardness(0.5f).setResistance(0.5f).setSoundGroup(Block.DIRT_SOUND_GROUP).setTranslationKey(InitListener.NAMESPACE, "hayBaleBlock");
        FENCE_GATE = new FenceGateBlock(InitListener.NAMESPACE.id("fence_gate"), Block.PLANKS.id).setHardness(2.0f).setResistance(3.0f).setSoundGroup(Block.WOOD_SOUND_GROUP).setTranslationKey(InitListener.NAMESPACE, "fenceGateBlock").disableTrackingStatistics().ignoreMetaUpdates();
        OPEN_FENCE_GATE = new FenceGateBlock(InitListener.NAMESPACE.id("open_fence_gate"), Block.PLANKS.id).setHardness(2.0f).setResistance(3.0f).setSoundGroup(Block.WOOD_SOUND_GROUP).setTranslationKey(InitListener.NAMESPACE, "fenceGateBlockOpen").disableTrackingStatistics().ignoreMetaUpdates().disableAutoItemRegistration();

        blocks = new Block[] {
                REDSTONE_BLOCK,
                COAL_BLOCK,
                BRICK_STAIRS,
                SANDSTONE_STAIRS,
                WATER_SPAWNER,
                LAVA_SPAWNER,
                BRICK_SLAB,
                BRICK_DOUBLE_SLAB,
                HAY_BALE,
                FENCE_GATE,
                OPEN_FENCE_GATE
        };
    }
}
