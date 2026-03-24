package io.github.SixMix98.hybridbeta.mixin;

import net.minecraft.block.LockedChestBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(LockedChestBlock.class)
public class LockedChestBlockMixin {
    // Locked chests do not automatically despawn
    public void onTick(World world, int x, int y, int z, Random random) {

    }

}
