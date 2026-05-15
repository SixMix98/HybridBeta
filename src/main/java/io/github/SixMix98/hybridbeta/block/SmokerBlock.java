package io.github.SixMix98.hybridbeta.block;

import io.github.SixMix98.hybridbeta.block.entity.SmokerBlockEntity;
import io.github.SixMix98.hybridbeta.events.init.BlockListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.template.block.TemplateFurnaceBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;
import static net.modificationstation.stationapi.api.state.property.Properties.HORIZONTAL_FACING;

import java.util.Random;

public class SmokerBlock extends TemplateFurnaceBlock {
    private final boolean lit;
    private Random random = new Random();
    private static boolean ignoreBlockRemoval = false;
    private static final Direction[] DIRECTIONS = new Direction[] { Direction.WEST, Direction.NORTH, Direction.EAST, Direction.SOUTH };

    public SmokerBlock(Identifier identifier, boolean lit) {
        super(identifier, lit);
        this.lit = lit;
    }

    public int getDroppedItemId(int blockMeta, Random random) {
        return BlockListener.SMOKER.asItem().id;
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (!this.lit) {
            return;
        }
        Direction facing = world.getBlockState(x, y, z).get(HORIZONTAL_FACING);
        float f = (float)x + 0.5f;
        float f2 = (float)y + 0.0f + random.nextFloat() * 6.0f / 16.0f;
        float f3 = (float)z + 0.5f;
        float f4 = 0.52f;
        float f5 = random.nextFloat() * 0.6f - 0.3f;
        if (facing.getName().equals("south")) {
            world.addParticle("smoke", f - f4, f2, f3 + f5, 0.0, 0.0, 0.0);
            world.addParticle("flame", f - f4, f2, f3 + f5, 0.0, 0.0, 0.0);
        } else if (facing.getName().equals("north")) {
            world.addParticle("smoke", f + f4, f2, f3 + f5, 0.0, 0.0, 0.0);
            world.addParticle("flame", f + f4, f2, f3 + f5, 0.0, 0.0, 0.0);
        } else if (facing.getName().equals("west")) {
            world.addParticle("smoke", f + f5, f2, f3 - f4, 0.0, 0.0, 0.0);
            world.addParticle("flame", f + f5, f2, f3 - f4, 0.0, 0.0, 0.0);
        } else if (facing.getName().equals("east")) {
            world.addParticle("smoke", f + f5, f2, f3 + f4, 0.0, 0.0, 0.0);
            world.addParticle("flame", f + f5, f2, f3 + f4, 0.0, 0.0, 0.0);
        }
    }

    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if (world.isRemote) {
            return true;
        }
        SmokerBlockEntity smokerBlockEntity = (SmokerBlockEntity) world.getBlockEntity(x, y, z);
        player.openFurnaceScreen(smokerBlockEntity);
        return true;
    }

    public static void updateLitState(boolean lit, World world, int x, int y, int z) {
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        ignoreBlockRemoval = true;
        Direction facing = world.getBlockState(x, y, z).get(HORIZONTAL_FACING);
        if (lit) {
            world.setBlockState(x, y, z, BlockListener.LIT_SMOKER.getDefaultState().with(HORIZONTAL_FACING, facing));
        } else {
            world.setBlockState(x, y, z, BlockListener.SMOKER.getDefaultState().with(HORIZONTAL_FACING, facing));
        }
        ignoreBlockRemoval = false;
        blockEntity.cancelRemoval();
        world.setBlockEntity(x, y, z, blockEntity);
    }

    protected BlockEntity createBlockEntity() {
        return new SmokerBlockEntity();
    }

    @Override
    public void onPlaced(World level, int x, int y, int z, LivingEntity living) {
        level.setBlockState(x, y, z, getDefaultState().with(HORIZONTAL_FACING, DIRECTIONS[MathHelper.floor((double)(living.yaw * 4.0F / 360.0F) + 0.5D) & 3]));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        if (!ignoreBlockRemoval) {
            SmokerBlockEntity smokerBlockEntity = (SmokerBlockEntity)world.getBlockEntity(x, y, z);
            for (int i = 0; i < smokerBlockEntity.size(); ++i) {
                ItemStack itemStack = smokerBlockEntity.getStack(i);
                if (itemStack == null) continue;
                float f = this.random.nextFloat() * 0.8f + 0.1f;
                float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                float f3 = this.random.nextFloat() * 0.8f + 0.1f;
                while (itemStack.count > 0) {
                    int n = this.random.nextInt(21) + 10;
                    if (n > itemStack.count) {
                        n = itemStack.count;
                    }
                    itemStack.count -= n;
                    ItemEntity itemEntity = new ItemEntity(world, (float)x + f, (float)y + f2, (float)z + f3, new ItemStack(itemStack.itemId, n, itemStack.getDamage()));
                    float f4 = 0.05f;
                    itemEntity.velocityX = (float)this.random.nextGaussian() * f4;
                    itemEntity.velocityY = (float)this.random.nextGaussian() * f4 + 0.2f;
                    itemEntity.velocityZ = (float)this.random.nextGaussian() * f4;
                    world.spawnEntity(itemEntity);
                }
            }
        }
        world.removeBlockEntity(x, y, z);
    }


}
