package io.github.SixMix98.hybridbeta.block;

import io.github.SixMix98.hybridbeta.events.init.BlockListener;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.BooleanProperty;
import net.modificationstation.stationapi.api.template.block.TemplateFenceBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.Random;

public class FenceGateBlock extends TemplateFenceBlock {
    public static final BooleanProperty DIRECTION = BooleanProperty.of("direction");

    public FenceGateBlock(Identifier identifier, int textureId) {
        super(identifier, textureId);
    }

    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        boolean direction = world.getBlockState(x, y, z).get(DIRECTION);
        if (this.id == BlockListener.OPEN_FENCE_GATE.id) {
            world.setBlock(x, y, z, BlockListener.FENCE_GATE.id);
            world.setBlockState(x, y, z, BlockListener.FENCE_GATE.getDefaultState().with(DIRECTION, direction));
            world.playSound(x, y, z, "random.door_close", 1.0f, world.random.nextFloat() * 0.1f + 0.9f);
        }
        else {
            world.setBlock(x, y, z, BlockListener.OPEN_FENCE_GATE.id);
            world.playSound(x, y, z, "random.door_open", 1.0f, world.random.nextFloat() * 0.1f + 0.9f);
            world.setBlockState(x, y, z, BlockListener.OPEN_FENCE_GATE.getDefaultState().with(DIRECTION, direction));
        }
        return true;
    }

    public Box getCollisionShape(World world, int x, int y, int z) {
        return (this.id == BlockListener.FENCE_GATE.id ? Box.createCached(x, y, z, x + 1, (float)y + 1.5f, z + 1) : null);
    }

    public int getDroppedItemId(int blockMeta, Random random) {
        return Block.PLANKS.id;
    }

    public int getDroppedItemCount(Random random) { return 4; }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DIRECTION);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        if (context.getPlayerLookDirection() == Direction.NORTH || context.getPlayerLookDirection() == Direction.SOUTH) {
            return getDefaultState().with(DIRECTION, false);
        }
        return getDefaultState().with(DIRECTION, true);
    }

}
