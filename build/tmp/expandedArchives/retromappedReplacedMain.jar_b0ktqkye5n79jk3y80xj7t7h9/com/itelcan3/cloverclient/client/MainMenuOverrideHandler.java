package com.itelcan3.cloverclient.client;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority; // Importa questo
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MainMenuOverrideHandler {

    // Usiamo la priorità HIGHEST per assicurarci di intercettare il menu prima di altri
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.gui instanceof GuiMainMenu && !(event.gui instanceof CustomGuiMainMenu)) {
            event.gui = new CustomGuiMainMenu();
        }
    }
}