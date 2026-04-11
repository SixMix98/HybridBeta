package io.github.SixMix98.hybridbeta.mixin;

import io.github.SixMix98.hybridbeta.events.init.AchievementListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SkeletonEntity.class)
public class SkeletonEntityMixin extends MonsterEntity {
    public SkeletonEntityMixin(World world) { super(world); }

    public boolean damage(Entity damageSource, int amount) {
        if (damageSource instanceof PlayerEntity && getDistance(damageSource) >= 25 && this.health - amount <= 0) {
            ((PlayerEntity) damageSource).incrementStat(AchievementListener.SNIPE_SKELETON);
        }
        if (super.damage(damageSource, amount)) {
            if (this.passenger == damageSource || this.vehicle == damageSource) {
                return true;
            }
            if (damageSource != this) {
                this.target = damageSource;
            }
            return true;
        }
        return false;
    }

}
