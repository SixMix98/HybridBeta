package io.github.SixMix98.hybridbeta.events.init.client;

import io.github.SixMix98.hybridbeta.client.render.entity.HerobrineEntityRenderer;
import io.github.SixMix98.hybridbeta.entity.HerobrineEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;

import java.lang.invoke.MethodHandles;

public class EntityRenderers {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    @EventListener
    private static void registerEntityRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(HerobrineEntity.class, new HerobrineEntityRenderer());
    }
}
