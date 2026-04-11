package io.github.SixMix98.hybridbeta.mixin;

import io.github.SixMix98.hybridbeta.events.init.AchievementListener;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

    @Shadow
    protected Minecraft minecraft;

    @Inject(method = "tickMovement", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/ClientPlayerEntity;screenDistortion:F", opcode = Opcodes.PUTFIELD, shift = At.Shift.AFTER))
    private void afterScreenDistortion(CallbackInfo ci) {
        this.minecraft.player.incrementStat(AchievementListener.REACH_NETHER);
    }
}
