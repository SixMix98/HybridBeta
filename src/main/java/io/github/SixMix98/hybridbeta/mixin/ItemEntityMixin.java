package io.github.SixMix98.hybridbeta.mixin;

import io.github.SixMix98.hybridbeta.events.init.AchievementListener;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Shadow
    public ItemStack stack;

    @Inject(method = "onPlayerInteraction", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;addStack(Lnet/minecraft/item/ItemStack;)Z"))
    private void afterStackCount(PlayerEntity player, CallbackInfo ci) {
        if (this.stack.itemId == Item.DIAMOND.id) {
            player.incrementStat(AchievementListener.DIAMONDS);
        }
    }
}
