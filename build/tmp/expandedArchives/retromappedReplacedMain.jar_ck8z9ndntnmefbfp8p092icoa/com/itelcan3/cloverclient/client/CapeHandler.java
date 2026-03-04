package com.itelcan3.cloverclient.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import java.lang.reflect.Field;

public class CapeHandler {

    // Percorso interno al JAR: assets/clovercape/textures/cape.png
    private static final ResourceLocation INTERNAL_CAPE = new ResourceLocation("cloverclient", "textures/cape.png");

    public static void initFolder() {
        // Metodo mantenuto per compatibilità con MyCapeMod.java, 
        // ma ora non deve più creare cartelle esterne.
        System.out.println("CloverClient > Cape Initialization complete.");
    }

    public static void apply() {
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71439_g == null || mc.func_147114_u() == null) return;

        AbstractClientPlayer player = mc.field_71439_g;
        NetworkPlayerInfo info = mc.func_147114_u().func_175102_a(player.func_110124_au());

        if (info != null) {
            try {
                // 1. Impostiamo la texture puntando al file interno al JAR
                setField(info, "field_178862_f", "locationCape", INTERNAL_CAPE);
                
                // 2. Forza il layer del mantello a essere visibile (Compatibilità Optifine)
                player.func_70096_w().func_75692_b(10, (byte) 127);

            } catch (Exception e) {
                System.out.println("CloverClient > An error occured during load the cape.");
            }
        }
    }

    private static void setField(Object obj, String srgName, String devName, Object value) {
        try {
            Field f;
            try {
                f = obj.getClass().getDeclaredField(srgName);
            } catch (NoSuchFieldException e) {
                f = obj.getClass().getDeclaredField(devName);
            }
            f.setAccessible(true);
            f.set(obj, value);
        } catch (Exception ignored) {}
    }
}