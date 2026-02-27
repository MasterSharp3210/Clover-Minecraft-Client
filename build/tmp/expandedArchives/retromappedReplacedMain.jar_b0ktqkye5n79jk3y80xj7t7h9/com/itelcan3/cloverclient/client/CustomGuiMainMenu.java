package com.itelcan3.cloverclient.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class CustomGuiMainMenu extends GuiMainMenu {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("cloverclient", "textures/gui/main_menu_background.png");
    private static final ResourceLocation MINECRAFT_LOGO = new ResourceLocation("textures/gui/title/minecraft.png");

    public CustomGuiMainMenu() {
        super();
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        // 1. Reset e Sfondo
        this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, 0xFF000000, 0xFF000000);
        this.drawCustomBackground();

        // 2. FORMULA UNIVERSALE PER IL LOGO
        this.drawCenteredLogo(60);

        // 3. Bottoni e scritte
        for (int i = 0; i < this.field_146292_n.size(); ++i) {
            ((GuiButton)this.field_146292_n.get(i)).func_146112_a(this.field_146297_k, mouseX, mouseY);
        }
        this.func_73731_b(this.field_146289_q, "Clover Client 1.0", 2, this.field_146295_m - 10, -1);
    }

    private void drawCenteredLogo(int y) {
        this.field_146297_k.func_110434_K().func_110577_a(MINECRAFT_LOGO);
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
        // FORMULE MATEMATICHE PER CENTRARE IL LOGO

        // Larghezza totale effettiva del logo Minecraft nella 1.8.9
        // Non è 310, ma 274 pixel di logo "pieno" dentro una texture da 310.
        // Questo è il motivo per cui la formula standard fallisce.
        int logoActualWidth = 274; 
        
        // Calcoliamo il centro esatto dello schermo
        // Usiamo un offset di -2 che compensa l'asimmetria del file minecraft.png originale
        int centerX = (this.field_146294_l / 2) - (logoActualWidth / 2) - 2;

        // Disegniamo le due metà
        // Nota: la prima metà si ferma a 155, la seconda inizia a 155.
        // Ma noi le facciamo sovrapporre di qualche pixel per eliminare la linea bianca centrale
        this.func_73729_b(centerX, y, 0, 0, 155, 44);
        this.func_73729_b(centerX + 155, y, 0, 45, 155, 44);
        
        GlStateManager.func_179084_k();
    }

    private void drawCustomBackground() {
        this.field_146297_k.func_110434_K().func_110577_a(BACKGROUND_TEXTURE);
        Tessellator tessellator = Tessellator.func_178181_a();
        WorldRenderer worldrenderer = tessellator.func_178180_c();
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        worldrenderer.func_181662_b(0.0D, (double)this.field_146295_m, 0.0D).func_181673_a(0.0D, 1.0D).func_181675_d();
        worldrenderer.func_181662_b((double)this.field_146294_l, (double)this.field_146295_m, 0.0D).func_181673_a(1.0D, 1.0D).func_181675_d();
        worldrenderer.func_181662_b((double)this.field_146294_l, 0.0D, 0.0D).func_181673_a(1.0D, 0.0D).func_181675_d();
        worldrenderer.func_181662_b(0.0D, 0.0D, 0.0D).func_181673_a(0.0D, 0.0D).func_181675_d();
        tessellator.func_78381_a();
    }

    @Override
    public void func_146278_c(int p_146278_1_) {}
}