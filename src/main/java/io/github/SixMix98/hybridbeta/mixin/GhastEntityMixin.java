package io.github.SixMix98.hybridbeta.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(GhastEntity.class)
public class GhastEntityMixin extends FlyingEntity {
    public GhastEntityMixin(World world) { super(world); }

    public boolean damage(Entity damageSource, int amount) {
        if (damageSource instanceof GhastEntity) {
            amount = 50;
        }
        return super.damage(damageSource, amount);
    }
}
