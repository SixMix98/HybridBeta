package io.github.SixMix98.hybridbeta.events.init;

import io.github.SixMix98.hybridbeta.item.QuiverItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;

public class ItemListener {

    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    public static Item QUIVER;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        QUIVER = new QuiverItem(NAMESPACE.id("quiver")).setTranslationKey(NAMESPACE, "quiver");

    }
}
