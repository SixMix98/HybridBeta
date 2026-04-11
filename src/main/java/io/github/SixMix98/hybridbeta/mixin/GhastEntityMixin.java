package io.github.SixMix98.hybridbeta.mixin;

import io.github.SixMix98.hybridbeta.events.init.AchievementListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(GhastEntity.class)
public class GhastEntityMixin extends FlyingEntity {
    public GhastEntityMixin(World world) { super(world); }

    // Redirected fireballs are an instant kill
    public boolean damage(Entity damageSource, int amount) {
        if (damageSource instanceof GhastEntity) {
            amount = 50;
            PlayerEntity player = this.world.getClosestPlayer(this, 100.0F);
            if (player != null) {
                player.increaseStat(AchievementListener.REDIRECT_GHAST, 1);
            }
        }
        return super.damage(damageSource, amount);
    }
}
