package io.github.SixMix98.hybridbeta.events.init;

import io.github.SixMix98.hybridbeta.item.QuiverItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;

public class ItemListener {
    public static Item[] items;
    public static Item QUIVER;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        QUIVER = new QuiverItem(InitListener.NAMESPACE.id("quiver")).setTranslationKey(InitListener.NAMESPACE, "quiver");

        items = new Item[]{
                QUIVER,
        };
    }
}
