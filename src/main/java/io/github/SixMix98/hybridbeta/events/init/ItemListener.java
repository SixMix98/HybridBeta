package io.github.SixMix98.hybridbeta.events.init;

import io.github.SixMix98.hybridbeta.item.QuiverItem;
import io.github.SixMix98.hybridbeta.item.StuddedArmorItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;

public class ItemListener {

    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    public static Item QUIVER;
    public static Item STUDDED_HELMET;
    public static Item STUDDED_CHESTPLATE;
    public static Item STUDDED_LEGGINGS;
    public static Item STUDDED_BOOTS;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        QUIVER = new QuiverItem(NAMESPACE.id("quiver")).setTranslationKey(NAMESPACE, "quiver");
        STUDDED_HELMET = new StuddedArmorItem(InitListener.NAMESPACE.id("studded_helmet"), 2, 3, 0).setTexturePosition(3,0).setTranslationKey(NAMESPACE, "studded_helmet").setMaxDamage(99);
        STUDDED_CHESTPLATE = new StuddedArmorItem(InitListener.NAMESPACE.id("studded_chestplate"), 2, 3, 1).setTexturePosition(3,1).setTranslationKey(NAMESPACE, "studded_chestplate").setMaxDamage(144);
        STUDDED_LEGGINGS = new StuddedArmorItem(InitListener.NAMESPACE.id("studded_leggings"), 2, 3, 2).setTexturePosition(3,2).setTranslationKey(NAMESPACE, "studded_leggings").setMaxDamage(135);
        STUDDED_BOOTS = new StuddedArmorItem(InitListener.NAMESPACE.id("studded_boots"), 2, 3, 3).setTexturePosition(3,3).setTranslationKey(NAMESPACE, "studded_boots").setMaxDamage(117);


    }
}
