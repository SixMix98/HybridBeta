package io.github.SixMix98.hybridbeta.events.init;

import io.github.SixMix98.hybridbeta.entity.HerobrineEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;

import javax.swing.text.html.parser.Entity;
import java.lang.invoke.MethodHandles;

public class EntityListener {

    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    @EventListener
    public void registerEntities(EntityRegister event) {
        event.register(HerobrineEntity.class, "Herobrine");
    }



}