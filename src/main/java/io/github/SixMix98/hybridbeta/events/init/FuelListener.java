package io.github.SixMix98.hybridbeta.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;

public class FuelListener {

    @EventListener
    public void registerFuel(RecipeRegisterEvent event) {
        if (event.recipeId == RecipeRegisterEvent.Vanilla.SMELTING.type()) {
            FuelRegistry.addFuelItem(BlockListener.COAL_BLOCK.asItem(), 14400);
        }
    }
}
