package io.github.SixMix98.hybridbeta.events.init;

import io.github.SixMix98.hybridbeta.block.entity.SmokerBlockEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;

public class BlockEntityListener {
    @EventListener
    public void registerBlockEntities(BlockEntityRegisterEvent event) {
        event.register(SmokerBlockEntity.class, "smokerEntity");
    }
}
