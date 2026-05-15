package io.github.SixMix98.hybridbeta.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin extends LivingEntity {

    public PlayerEntityMixin(World world) { super(world); }

}
