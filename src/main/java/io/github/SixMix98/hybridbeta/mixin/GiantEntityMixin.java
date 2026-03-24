package io.github.SixMix98.hybridbeta.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GiantEntity.class)
public class GiantEntityMixin extends MonsterEntity {

    public GiantEntityMixin(World world) {
        super(world);
    }


    protected String getRandomSound() {
        return "mob.zombie";
    }

    protected String getHurtSound() {
        return "mob.zombiehurt";
    }

    protected String getDeathSound() {
        return "mob.zombiedeath";
    }

    // Fixes reach so giants can actually hit their targets
    protected void attack(Entity other, float distance) {
        if (this.attackCooldown <= 0 && distance < 4.0F && other.boundingBox.maxY > this.boundingBox.minY && other.boundingBox.minY < this.boundingBox.maxY) {
            this.attackCooldown = 80;
            other.damage(this, this.attackDamage);
        }

    }

    @Inject(method = "<init>", at=@At("TAIL"))
    private void onInit(CallbackInfo ci) {
        this.health = 100;
        this.attackDamage = 14;
    }

    // Spawns rarely at night/dark areas
    public boolean canSpawn() {
        int n;
        int n2;
        int n3 = MathHelper.floor(this.x);
        if (this.world.getBrightness(LightType.SKY, n3, n2 = MathHelper.floor(this.boundingBox.minY), n = MathHelper.floor(this.z)) > this.random.nextInt(32)) {
            return false;
        }
        int n4 = this.world.getLightLevel(n3, n2, n);
        if (this.world.isThundering()) {
            int n5 = this.world.ambientDarkness;
            this.world.ambientDarkness = 10;
            n4 = this.world.getLightLevel(n3, n2, n);
            this.world.ambientDarkness = n5;
        }
        return n4 <= this.random.nextInt(8) && this.world.difficulty > 1 && this.world.canSpawnEntity(this.boundingBox) && this.world.getEntityCollisions(this, this.boundingBox).size() == 0 && !this.world.isBoxSubmergedInFluid(this.boundingBox) && this.world.random.nextInt(4) == 0;
    }
}
