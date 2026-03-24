package io.github.SixMix98.hybridbeta.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin {

    @Redirect(method = "use", at= @At(value = "NEW", target = "net/minecraft/item/ItemStack"))
    private ItemStack remapFishItem(Item item) {
        return new Random().nextInt(8) == 0 ? new ItemStack(Block.SPONGE) : new ItemStack(Item.RAW_FISH);
    }

}
