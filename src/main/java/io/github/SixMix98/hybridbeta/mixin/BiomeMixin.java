package io.github.SixMix98.hybridbeta.mixin;

import io.github.SixMix98.hybridbeta.entity.HerobrineEntity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.EntitySpawnGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Biome.class)
public class BiomeMixin {

    @Shadow
    protected List spawnableMonsters;


    @Inject(method = "<init>", at=@At("TAIL"))
    private void onInit(CallbackInfo ci) {
        this.spawnableMonsters.add(new EntitySpawnGroup(GiantEntity.class, 1));
        this.spawnableMonsters.add(new EntitySpawnGroup(HerobrineEntity.class, 1));
    }
}
