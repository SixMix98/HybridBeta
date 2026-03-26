package io.github.SixMix98.hybridbeta.item;

import net.modificationstation.stationapi.api.template.item.ItemTemplate;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class StuddedArmorItem extends TemplateArmorItem implements ItemTemplate {

    public String texturePath = "/assets/hybridbeta/stationapi/textures/armor/studded_1.png";

    public StuddedArmorItem(Identifier identifier, int type, int textureIndex, int slot) {
        super(identifier, type, textureIndex, slot);
        // Uses studded_2.png for leggings
        if (slot == 2) {
            this.texturePath = "/assets/hybridbeta/stationapi/textures/armor/studded_2.png";
        }

    }

}
