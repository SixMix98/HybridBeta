package io.github.SixMix98.hybridbeta.entity;

import io.github.SixMix98.hybridbeta.events.init.InitListener;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class HerobrineEntity extends MonsterEntity {

    PlayerEntity player = this.world.getClosestPlayer(this, 30.0F);
    protected int lookDuration = 0;

    public HerobrineEntity(World world) {
        super(world);
        this.maxHealth = 40;
        this.health = 40;
        this.movementSpeed = 0.95F;
        this.attackDamage = 6;
        this.texture = "/assets/hybridbeta/stationapi/textures/mob/herobrine.png";

    }


    // Can spawn as long as difficulty is normal or hard.  Odds of spawning are very low
    public boolean canSpawn() {
        return this.world.difficulty > 1 && this.world.canSpawnEntity(this.boundingBox) && this.world.getEntityCollisions(this, this.boundingBox).size() == 0 && !this.world.isBoxSubmergedInFluid(this.boundingBox) && this.world.random.nextInt(100) == 0;
    }

    // Will attack the player if they are within 8 blocks
    protected Entity getTargetInRange() {
        if (player != null) {
            if (this.getDistance(player) <= 8.0F) {
                return player;
            }
        }
        return null;
    }

    // Occasionally stops to look at player for 5 seconds
    public void tick() {
        super.tick();
        player = this.world.getClosestPlayer(this, 30.0F);
        if (player != null) {
            float distance = (this.getDistance(player));
            if (distance > 8.0F) {
                if (this.lookDuration > 0) {
                    this.lookAt(player, 10.0F, 10.0F);
                    this.lookDuration -= 1;
                }
                else if (this.world.random.nextInt(600) == 0) {
                    this.lookDuration = 100;
                }
            }
        }
        else {
            int var1 = MathHelper.floor(this.x);
            int var2 = MathHelper.floor(this.boundingBox.minY);
            int var3 = MathHelper.floor(this.z);
            if (this.world.getLightLevel(var1, var2, var3) < 5 && this.world.getBlockId(var1, var2, var3) == 0 && this.world.random.nextInt(1000) == 0 && this.world.hasSkyLight(var1, var2, var3)) {
                this.world.setBlock(var1, var2, var3, Block.REDSTONE_TORCH.id);
            }
        }
    }


    protected void attack(Entity other, float distance) {
        if (this.attackCooldown <= 0 && distance < 4.0F && other.boundingBox.maxY > this.boundingBox.minY && other.boundingBox.minY < this.boundingBox.maxY) {
            this.attackCooldown = 1;
            other.damage(this, this.attackDamage);
        }

    }

    // Will teleport to the target if attacked from far away (such as with bow)
    public boolean damage(Entity damageSource, int amount) {
        if (super.damage(damageSource, amount)) {
            if (this.passenger == damageSource || this.vehicle == damageSource) {
                return true;
            }
            if (damageSource != this) {
                this.target = damageSource;
            }
            if (getDistance(this.target) > 8.0F) {
                this.setPosition(target.x, target.y, target.z);
            }
            return true;
        }
        return false;
    }

    public Identifier getHandlerIdentifier() {
        return InitListener.NAMESPACE.id("Herobrine");
    }

}