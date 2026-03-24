package io.github.SixMix98.hybridbeta.client.render.entity;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class HerobrineEntityRenderer extends LivingEntityRenderer {
    public HerobrineEntityRenderer() {
        super(new BipedEntityModel(), 1.0f);
        this.setDecorationModel(new BipedEntityModel());
    }

    // Enables glowing eyes
    protected boolean bindTexture(LivingEntity livingEntity, int i, float f) {
        if (i != 0) {
            return false;
        }
        this.bindTexture("/assets/hybridbeta/stationapi/textures/mob/herobrine_eyes.png");
        float f2 = (1.0f - livingEntity.getBrightnessAtEyes(1.0f)) * 0.2f;
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, f2);
        return true;
    }
}
