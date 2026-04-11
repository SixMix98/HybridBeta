package io.github.SixMix98.hybridbeta.mixin;

import io.github.SixMix98.hybridbeta.events.init.AchievementListener;
import io.github.SixMix98.hybridbeta.events.init.ItemListener;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin {
    @Shadow
    private PlayerEntity player;

    @Inject(method = "onTakeItem", at = @At("TAIL"))
    private void addAchievement(ItemStack stack, CallbackInfo ci) {
        if (stack.itemId == Block.BOOKSHELF.id) {
            this.player.incrementStat(AchievementListener.CRAFT_BOOKSHELF);
        }
        else if (stack.itemId == ItemListener.QUIVER.id) {
            this.player.incrementStat(AchievementListener.CRAFT_QUIVER);
        }
        else if (stack.itemId == Item.BOW.id) {
            this.player.incrementStat(AchievementListener.CRAFT_BOW);
        }
    }
}
