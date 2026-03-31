package io.github.SixMix98.hybridbeta.mixin;

import io.github.SixMix98.hybridbeta.events.init.BlockListener;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockRenderManager.class)
public class BlockRenderManagerMixin {

    @Redirect(method = "renderFence", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/BlockView;getBlockId(III)I"))
    private int RedirectGetBlockId(BlockView blockView, int x, int y, int z) {
        int id = blockView.getBlockId(x, y, z);
        if (id == BlockListener.FENCE_GATE.id || id == BlockListener.OPEN_FENCE_GATE.id) {
            return Block.FENCE.id;
        }
        return id;
    }

}
