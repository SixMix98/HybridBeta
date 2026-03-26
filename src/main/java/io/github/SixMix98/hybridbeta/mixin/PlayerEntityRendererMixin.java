package io.github.SixMix98.hybridbeta.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.SixMix98.hybridbeta.item.StuddedArmorItem;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @WrapOperation(method = "bindTexture(Lnet/minecraft/entity/player/PlayerEntity;IF)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;bindTexture(Ljava/lang/String;)V"))
    private void setArmorTexture(PlayerEntityRenderer renderer, String texture, Operation<Void> operation, @Local(ordinal = 0) int type, @Local ArmorItem item) {
        if (item instanceof StuddedArmorItem studdedArmor) {
            operation.call(renderer, studdedArmor.texturePath);
        }
        else {
            operation.call(renderer, texture);
        }
    }
}
