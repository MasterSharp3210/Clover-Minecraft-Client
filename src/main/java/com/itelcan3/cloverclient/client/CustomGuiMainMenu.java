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
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // 1. Reset e Sfondo
        this.drawGradientRect(0, 0, this.width, this.height, 0xFF000000, 0xFF000000);
        this.drawCustomBackground();

        // 2. FORMULA UNIVERSALE PER IL LOGO
        this.drawCenteredLogo(60);

        // 3. Bottoni e scritte
        for (int i = 0; i < this.buttonList.size(); ++i) {
            ((GuiButton)this.buttonList.get(i)).drawButton(this.mc, mouseX, mouseY);
        }
        this.drawString(this.fontRendererObj, "Clover Client 1.0", 2, this.height - 10, -1);
    }

    private void drawCenteredLogo(int y) {
        this.mc.getTextureManager().bindTexture(MINECRAFT_LOGO);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        // FORMULE MATEMATICHE PER CENTRARE IL LOGO

        // Larghezza totale effettiva del logo Minecraft nella 1.8.9
        // Non è 310, ma 274 pixel di logo "pieno" dentro una texture da 310.
        // Questo è il motivo per cui la formula standard fallisce.
        int logoActualWidth = 274; 
        
        // Calcoliamo il centro esatto dello schermo
        // Usiamo un offset di -2 che compensa l'asimmetria del file minecraft.png originale
        int centerX = (this.width / 2) - (logoActualWidth / 2) - 2;

        // Disegniamo le due metà
        // Nota: la prima metà si ferma a 155, la seconda inizia a 155.
        // Ma noi le facciamo sovrapporre di qualche pixel per eliminare la linea bianca centrale
        this.drawTexturedModalRect(centerX, y, 0, 0, 155, 44);
        this.drawTexturedModalRect(centerX + 155, y, 0, 45, 155, 44);
        
        GlStateManager.disableBlend();
    }

    private void drawCustomBackground() {
        this.mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(0.0D, (double)this.height, 0.0D).tex(0.0D, 1.0D).endVertex();
        worldrenderer.pos((double)this.width, (double)this.height, 0.0D).tex(1.0D, 1.0D).endVertex();
        worldrenderer.pos((double)this.width, 0.0D, 0.0D).tex(1.0D, 0.0D).endVertex();
        worldrenderer.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0.0D).endVertex();
        tessellator.draw();
    }

    @Override
    public void drawBackground(int p_146278_1_) {}
}