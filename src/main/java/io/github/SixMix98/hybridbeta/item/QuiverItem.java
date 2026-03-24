package io.github.SixMix98.hybridbeta.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class QuiverItem extends TemplateItem {
    public QuiverItem(Identifier identifier) {
        super(identifier);
        this.maxCount = 1;
        this.setMaxDamage(512);
    }
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        for (int i = 0; i < user.inventory.main.length; i++) {
            if (user.inventory.main[i] instanceof ItemStack && user.inventory.main[i].itemId == ARROW.id) {
                stack.damage(-user.inventory.main[i].count, user);
                user.inventory.removeStack(i, user.inventory.main[i].count);
            }
        }

        return stack;
    }
}
