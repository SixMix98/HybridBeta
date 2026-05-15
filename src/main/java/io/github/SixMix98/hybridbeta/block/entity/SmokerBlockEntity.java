package io.github.SixMix98.hybridbeta.block.entity;

import io.github.SixMix98.hybridbeta.block.SmokerBlock;
import io.github.SixMix98.hybridbeta.events.init.BlockListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.SmeltingRecipeManager;

public class SmokerBlockEntity extends FurnaceBlockEntity implements Inventory {
    private ItemStack[] inventory = new ItemStack[11];
    // How long this item has been burning
    public int burnTime = 0;
    // How long item can burn before expiring
    public int fuelTime = 0;
    // How long an item has been smelting
    public int cookTime = 0;

    public int size() {
        return this.inventory.length;
    }

    public ItemStack getStack(int slot) {
        return this.inventory[slot];
    }

    public ItemStack removeStack(int slot, int amount) {
        //takes the item from the smoker and returns it to player inventory
        if (this.inventory[slot] != null) {
            if (slot == 0) {
                ItemStack itemStack = this.inventory[slot].copy();
                this.inventory[slot].count -= 1;
                if (this.inventory[slot].count == 0) {
                    this.inventory[slot] = null;
                }
                itemStack.count = 1;
                return  itemStack;
            }
            // If I am taking the whole stack or just some
            if (this.inventory[slot].count <= amount) {
                ItemStack itemStack = this.inventory[slot];
                this.inventory[slot] = null;
                return itemStack;
            }
            ItemStack itemStack = this.inventory[slot].split(amount);
            if (this.inventory[slot].count == 0) {
                this.inventory[slot] = null;
            }
            return itemStack;
        }
        return null;
    }

    public void setStack(int slot, ItemStack stack) {
        if (slot == 0) {
            if (stack != null && this.inventory[slot] != null) {
                ++this.inventory[slot].count;
                return;
            }
        }
        this.inventory[slot] = stack;
        if (stack != null && stack.count > this.getMaxCountPerStack()) {
            stack.count = this.getMaxCountPerStack();
        }
    }

    public String getName() {
        return "Smoker";
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        NbtList nbtList = nbt.getList("Items");
        this.inventory = new ItemStack[this.size()];
        for (int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = (NbtCompound)nbtList.get(i);
            byte by = nbtCompound.getByte("Slot");
            if (by < 0 || by >= this.inventory.length) continue;
            this.inventory[by] = new ItemStack(nbtCompound);
        }
        this.burnTime = nbt.getShort("BurnTime");
        this.cookTime = nbt.getShort("CookTime");
        this.fuelTime = this.getFuelTime(this.inventory[1]);
    }

    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putShort("BurnTime", (short)this.burnTime);
        nbt.putShort("CookTime", (short)this.cookTime);
        NbtList nbtList = new NbtList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putByte("Slot", (byte)i);
            this.inventory[i].writeNbt(nbtCompound);
            nbtList.add(nbtCompound);
        }
        nbt.put("Items", nbtList);
    }

    public int getMaxCountPerStack() {
        return 64;
    }

    @Environment(value=EnvType.CLIENT)
    public int getCookTimeDelta(int multiplier) {
        return this.cookTime * multiplier / 200;
    }

    @Environment(value=EnvType.CLIENT)
    public int getFuelTimeDelta(int multiplier) {
        if (this.fuelTime == 0) {
            this.fuelTime = 200;
        }
        return this.burnTime * multiplier / this.fuelTime;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public void tick() {
        boolean bl = this.burnTime > 0;
        boolean bl2 = false;
        // If in process of smelting an item, decrease countdown to completion
        if (this.burnTime > 0) {
            --this.burnTime;
        }
        if (!this.world.isRemote) {
            // If smelting is done and there is room for an output
            if (this.burnTime == 0 && this.canAcceptRecipeOutput()) {
                // Start smelting the next item, if possible
                this.fuelTime = this.burnTime = this.getFuelTime(this.inventory[1]);
                // If a block/item is smelted, remove the raw component from input slot
                if (this.burnTime > 0) {
                    bl2 = true;
                    if (this.inventory[1] != null) {
                        --this.inventory[1].count;
                        // Remove the component if it's used up
                        if (this.inventory[1].count == 0) {
                            this.inventory[1] = null;
                        }
                    }
                }
            }
            //Progresses smelting process if there is room for it in output
            if (this.isBurning() && this.canAcceptRecipeOutput()) {
                ++this.cookTime;
                // Moves on to the next item to smelt after outputting item
                if (this.cookTime == 200) {
                    this.cookTime = 0;
                    this.craftRecipe();
                    bl2 = true;
                }
                // Won't progress until conditions are met
            } else {
                this.cookTime = 0;
            }
            // Updates the lit state
            if (bl != this.burnTime > 0) {
                bl2 = true;
                SmokerBlock.updateLitState(this.burnTime > 0, this.world, this.x, this.y, this.z);
            }
        }
        if (bl2) {
            this.markDirty();
        }
    }

    private boolean canAcceptRecipeOutput() {
        // Can't output an item if there is no item to smelt
        if (this.inventory[0] == null) {
            return false;
        }
        // The item it will smelt to
        ItemStack itemStack = SmeltingRecipeManager.getInstance().craft(this.inventory[0].getItem().id);
        // Can't output an item if the component can't be smelted
        if (itemStack == null) {
            return false;
        }
        for (int i = 2; i <= 9; ++i) {
            // Result item can be made if any output slot is empty
            if (this.inventory[i] == null) {
                return true;
            }
        }
        return false;
    }

    public void craftRecipe() {
        // Must be sure it can output the desired item
        if (!this.canAcceptRecipeOutput()) {
            return;
        }
        // The desired output item
        ItemStack itemStack = SmeltingRecipeManager.getInstance().craft(this.inventory[0].getItem().id);
        for (int i = 2; i <= 9; ++i) {
            // If an output slot is empty, fill it with desired output
            if (this.inventory[i] == null) {
                this.inventory[i] = itemStack.copy();
                break;
            }
        }
        // Remove the base material
        --this.inventory[0].count;
        // Empties slot if necessary
        if (this.inventory[0].count <= 0) {
            this.inventory[0] = null;
        }
    }

    private int getFuelTime(ItemStack itemStack) {
        // Return 0 if no fuel source
        if (itemStack == null) {
            return 0;
        }
        int n = itemStack.getItem().id;
        // wood has fuel value of 300
        if (n < 256 && Block.BLOCKS[n].material == Material.WOOD) {
            return 300;
        }
        // All item fuel values
        if (n == Item.STICK.id) {
            return 100;
        }
        if (n == Item.COAL.id) {
            return 1600;
        }
        if (n == Item.LAVA_BUCKET.id) {
            return 20000;
        }
        if (n == Block.SAPLING.id) {
            return 100;
        }
        if (n == BlockListener.COAL_BLOCK.asItem().id) {
            return 14400;
        }
        return 0;
    }

    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.x, this.y, this.z) != this) {
            return false;
        }
        return !(player.getSquaredDistance((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) > 64.0);
    }
}
