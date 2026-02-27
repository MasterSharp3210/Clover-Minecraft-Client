package com.itelcan3.cloverclient.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientEventHandler {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        // Applichiamo il mantello alla fine di ogni tick del client
        if (event.phase == TickEvent.Phase.END) {
            CapeHandler.apply();
        }
    }
}