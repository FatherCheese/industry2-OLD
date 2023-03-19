package turniplabs.industry.gui;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import org.lwjgl.opengl.GL11;
import sunsetsatellite.energyapi.util.Config;
import turniplabs.industry.block.tile.TileEntityGeneratorInd;
import turniplabs.industry.container.ContainerGeneratorInd;

public class GuiGeneratorInd extends GuiContainer {
    private final TileEntityGeneratorInd tile;

    public GuiGeneratorInd(InventoryPlayer player, TileEntityGeneratorInd tile) {
        super(new ContainerGeneratorInd(player, tile));
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int tex = mc.renderEngine.getTexture("/assets/industry/gui/generator.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(tex);

        int texX = (width - xSize) / 2;
        int texY = (height - ySize) / 2;
        drawTexturedModalRect(texX, texY, 0, 0, xSize, ySize);

        double power = (float) tile.energy / (float) tile.capacity;
        drawTexturedModalRect(texX + 80, texY + 39, 176, 0, (int) (power * 16), 8);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer();
        fontRenderer.drawString("Generator", 64, 6, 0xFF404040);
        fontRenderer.drawString("Inventory", 8, ySize - 96 + 2, 0x404040);
    }

    @Override
    public void drawScreen(int x, int y, float renderPartialTicks) {
        int screenX = (width - xSize) / 2;
        int screenY = (height - ySize) / 2;
        super.drawScreen(x, y, renderPartialTicks);
        StringBuilder text = new StringBuilder();
        
        if(x > screenX + 80 && x < screenX + 96)
            if (y > screenY + 39 && y < screenY + 47) {
                text.append(Config.getFromConfig("energyName", "Energy")).append(": ").append(tile.energy).append(" ").append(Config.getFromConfig("energySuffix", "E")).append("/").append(tile.capacity).append(" ").append(Config.getFromConfig("energySuffix", "E"));
                drawTooltip(text.toString(), x, y, 8, -8, true);
            }
    }
}
