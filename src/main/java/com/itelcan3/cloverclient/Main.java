package com.itelcan3.cloverclient;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent; // Import mancante
import net.minecraftforge.common.MinecraftForge; // Import mancante

import com.itelcan3.cloverclient.client.ClientEventHandler;
import com.itelcan3.cloverclient.client.CapeHandler;
import com.itelcan3.cloverclient.client.MainMenuOverrideHandler;

// Clover Minecraft Client by Itelcan3

@Mod(modid = "cloverclient", name = "Clover Client", version = "1.0")
public class Main {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Registra l'handler per sovrascrivere il menu principale
        // Evento salvato sul bus di Forge
        MinecraftForge.EVENT_BUS.register(new MainMenuOverrideHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Inizializzazione esistente per il mantello
        CapeHandler.initFolder();
        
        // Registra l'handler sul bus FML (necessario per ClientTickEvent)
        FMLCommonHandler.instance().bus().register(new ClientEventHandler());
    }
}