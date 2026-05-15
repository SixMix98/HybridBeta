package io.github.SixMix98.hybridbeta.screen;

import io.github.SixMix98.hybridbeta.block.entity.SmokerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;

public class SmokerScreenHandler extends ScreenHandler {
    private SmokerBlockEntity furnaceBlockEntity;

    public SmokerScreenHandler(PlayerInventory playerInventory, SmokerBlockEntity smokerBlockEntity) {
        int n;
        this.furnaceBlockEntity = smokerBlockEntity;
        this.addSlot(new Slot(furnaceBlockEntity, 0, 28, 17));
        this.addSlot(new Slot(furnaceBlockEntity, 1, 28, 53));
        for (n = 0; n < 4; ++n) {
            this.addSlot(new FurnaceOutputSlot(playerInventory.player, furnaceBlockEntity, n + 2, 83 + n*20, 25));
        }
        for (n = 0; n < 4; ++n) {
            this.addSlot(new FurnaceOutputSlot(playerInventory.player, furnaceBlockEntity, n + 6, 83 + n*20, 45));
        }
        for (n = 0; n < 3; ++n) {
            for (int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(playerInventory, i + n * 9 + 9, 8 + i * 18, 84 + n * 18));
            }
        }
        for (n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInventory, n, 8 + n * 18, 142));
        }
    }

    @Override
    public ItemStack onSlotClick(int index, int button, boolean shift, PlayerEntity player) {
        ItemStack itemStack = null;
        if (button == 0 || button == 1) {
            PlayerInventory playerInventory = player.inventory;
            // Dropping item outside of inventory
            if (index == -999) {
                if (playerInventory.getCursorStack() != null) {
                    if (button == 0) {
                        player.dropItem(playerInventory.getCursorStack());
                        playerInventory.setCursorStack(null);
                    }
                    if (button == 1) {
                        player.dropItem(playerInventory.getCursorStack().split(1));
                        if (playerInventory.getCursorStack().count == 0) {
                            playerInventory.setCursorStack(null);
                        }
                    }
                }
                // Shift clicking on something
            } else if (shift) {
                ItemStack itemStack2 = this.quickMove(index);
                if (itemStack2 != null) {
                    int n;
                    int n2 = itemStack2.count;
                    itemStack = itemStack2.copy();
                    Slot slot = (Slot)this.slots.get(index);
                    if (slot != null && slot.getStack() != null && (n = slot.getStack().count) < n2) {
                        this.onSlotClick(index, button, shift, player);
                    }
                }
            } else {
                Slot slot = (Slot)this.slots.get(index);
                // If slot is not empty
                if (slot != null) {
                    int n;
                    slot.markDirty();
                    ItemStack itemStack3 = slot.getStack();
                    ItemStack itemStack4 = playerInventory.getCursorStack();
                    // Makes it so you can only put raw pork and fish in slot 0
                    if (index == 0 && itemStack4 != null && (itemStack4.itemId != Item.RAW_PORKCHOP.id && itemStack4.itemId != Item.RAW_FISH.id)) {
                        return itemStack;
                    }
                    // Can't put raw fish in slot 0 when raw pork is already in, or vice versa
                    else if (itemStack4 != null && itemStack3 != null && (itemStack4.itemId != itemStack3.itemId)) {
                        return itemStack;
                    }
                    if (itemStack3 != null) {
                        itemStack = itemStack3.copy();
                    }
                    if (itemStack3 == null) {
                        if (itemStack4 != null && slot.canInsert(itemStack4)) {
                            int n3 = button == 0 ? itemStack4.count : 1;
                            if (n3 > slot.getMaxItemCount()) {
                                n3 = slot.getMaxItemCount();
                            }
                            slot.setStack(itemStack4.split(n3));
                            if (itemStack4.count == 0) {
                                playerInventory.setCursorStack(null);
                            }
                        }
                    } else if (itemStack4 == null) {
                        int n5 = button == 0 ? itemStack3.count : (itemStack3.count + 1) / 2;
                        ItemStack itemStack5 = slot.takeStack(n5);
                        playerInventory.setCursorStack(itemStack5);
                        if (itemStack3.count == 0) {
                            slot.setStack(null);
                        }
                        slot.onTakeItem(playerInventory.getCursorStack());
                    } else if (slot.canInsert(itemStack4)) {
                        if (itemStack3.itemId != itemStack4.itemId || itemStack3.hasSubtypes() && itemStack3.getDamage() != itemStack4.getDamage()) {
                            if (itemStack4.count <= slot.getMaxItemCount()) {
                                slot.setStack(itemStack4);
                                playerInventory.setCursorStack(itemStack3);
                            }
                        } else {
                            int n6;
                            int n7 = n6 = button == 0 ? itemStack4.count : 1;
                            if (n6 > slot.getMaxItemCount() - itemStack3.count) {
                                n6 = slot.getMaxItemCount() - itemStack3.count;
                            }
                            if (n6 > itemStack4.getMaxCount() - itemStack3.count && index != 0) {
                                n6 = itemStack4.getMaxCount() - itemStack3.count;
                            }
                            itemStack4.split(n6);
                            if (itemStack4.count == 0) {
                                playerInventory.setCursorStack(null);
                            }
                            itemStack3.count += n6;
                        }
                    } else if (!(itemStack3.itemId != itemStack4.itemId || itemStack4.getMaxCount() <= 1 || itemStack3.hasSubtypes() && itemStack3.getDamage() != itemStack4.getDamage() || (n = itemStack3.count) <= 0 || n + itemStack4.count > itemStack4.getMaxCount())) {
                        itemStack4.count += n;
                        itemStack3.split(n);
                        if (itemStack3.count == 0) {
                            slot.setStack(null);
                        }
                        slot.onTakeItem(playerInventory.getCursorStack());
                    }
                }
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack quickMove(int slot) {
        ItemStack itemStack = null;
        Slot slot2 = (Slot)this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot > 1 && slot < 10) {
                this.insertItem(itemStack2, 10, 46, true);
            } else if (slot >= 10 && slot < 37) {
                this.insertItem(itemStack2, 37, 46, false);
            } else if (slot >= 36 && slot < 46) {
                this.insertItem(itemStack2, 10, 37, false);
            } else if (slot != 0) {
                this.insertItem(itemStack2, 10, 46, false);
            }
            if (itemStack2.count == 0) {
                slot2.setStack(null);
            } else {
                slot2.markDirty();
            }
            if (itemStack2.count != itemStack.count) {
                slot2.onTakeItem(itemStack2);
            } else {
                return null;
            }
        }
        return itemStack;
    }

    public boolean canUse(PlayerEntity player) {
        return this.furnaceBlockEntity.canPlayerUse(player);
    }
}
