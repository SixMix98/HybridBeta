package io.github.SixMix98.hybridbeta.mixin;

import io.github.SixMix98.hybridbeta.events.init.ItemListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BowItem.class)
public class BowItemMixin extends Item {
    public BowItemMixin(int i) {
        super(i);
        this.maxCount = 1;
    }

    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        for (int i = 0; i < user.inventory.main.length; i++) {
            if (user.inventory.main[i] != null && user.inventory.main[i].itemId == ItemListener.QUIVER.id) {
                int n = user.inventory.main[i].getDamage();
                if (n < 512) {
                    n += 1;
                    user.inventory.main[i].setDamage(n);
                    world.playSound(user, "random.bow", 1.0F, 1.0F / (random.nextFloat() * 0.4F + 0.8F));
                    if (!world.isRemote) {
                        world.spawnEntity(new ArrowEntity(world, user));
                    }
                    return stack;
                }
            }
        }
        if (user.inventory.remove(Item.ARROW.id)) {
            world.playSound(user, "random.bow", 1.0F, 1.0F / (random.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                world.spawnEntity(new ArrowEntity(world, user));
            }
        }

        return stack;
    }
}
