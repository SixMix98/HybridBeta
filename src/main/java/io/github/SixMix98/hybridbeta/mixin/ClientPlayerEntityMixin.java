package io.github.SixMix98.hybridbeta.mixin;

import io.github.SixMix98.hybridbeta.block.entity.SmokerBlockEntity;
import io.github.SixMix98.hybridbeta.client.gui.screen.ingame.SmokerScreen;
import io.github.SixMix98.hybridbeta.events.init.AchievementListener;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ingame.FurnaceScreen;
import net.minecraft.client.util.Session;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends PlayerEntity {

    public ClientPlayerEntityMixin(Minecraft minecraft, World world, Session session, int dimensionId) {
        super(world);
    }

    @Shadow
    protected Minecraft minecraft;

    @Inject(method = "tickMovement", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/ClientPlayerEntity;screenDistortion:F", opcode = Opcodes.PUTFIELD, shift = At.Shift.AFTER))
    private void afterScreenDistortion(CallbackInfo ci) {
        this.minecraft.player.incrementStat(AchievementListener.REACH_NETHER);
    }

    public void openFurnaceScreen(FurnaceBlockEntity furnace) {
        if (furnace instanceof SmokerBlockEntity smoker) {
            this.minecraft.setScreen(new SmokerScreen(this.inventory, smoker));
            return;
        }
        this.minecraft.setScreen(new FurnaceScreen(this.inventory, furnace));
    }
}
