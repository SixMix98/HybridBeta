package io.github.SixMix98.hybridbeta.client.gui.screen.ingame;

import io.github.SixMix98.hybridbeta.block.entity.SmokerBlockEntity;
import io.github.SixMix98.hybridbeta.screen.SmokerScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class SmokerScreen extends HandledScreen {
    private SmokerBlockEntity smokerBlockEntity;

    public SmokerScreen(PlayerInventory inventory, SmokerBlockEntity smokerBlockEntity) {
        super(new SmokerScreenHandler(inventory, smokerBlockEntity));
        this.smokerBlockEntity = smokerBlockEntity;
    }

    @Override
    protected void drawForeground() {
        this.textRenderer.draw("Smoker", 60, 6, 0x404040);
        this.textRenderer.draw("Inventory", 8, this.backgroundHeight - 96 + 2, 0x404040);
    }

    protected void drawBackground(float tickDelta) {
        int n;
        int n2 = this.minecraft.textureManager.getTextureId("/assets/hybridbeta/stationapi/textures/gui/smoker3.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textureManager.bindTexture(n2);
        int n3 = (this.width - this.backgroundWidth) / 2;
        int n4 = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(n3, n4, 0, 0, this.backgroundWidth, this.backgroundHeight);
        if (this.smokerBlockEntity.isBurning()) {
            n = this.smokerBlockEntity.getFuelTimeDelta(12);
            this.drawTexture(n3 + 28, n4 + 36 + 12 - n, 176, 12 - n, 14, n + 2);
        }
        n = this.smokerBlockEntity.getCookTimeDelta(24);
        this.drawTexture(n3 + 51, n4 + 34, 176, 14, n + 1, 16);
    }

}
