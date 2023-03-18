package turniplabs.industry.gui;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import org.lwjgl.opengl.GL11;
import sunsetsatellite.energyapi.util.Config;
import turniplabs.industry.block.tile.TileEntitySolarGenerator;
import turniplabs.industry.container.ContainerSolarGenerator;

public class GuiSolarGenerator extends GuiContainer {
    private final TileEntitySolarGenerator tile;

    public GuiSolarGenerator(InventoryPlayer inventoryPlayer, TileEntitySolarGenerator tile) {
        super(new ContainerSolarGenerator(inventoryPlayer, tile));
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer()
    {
        super.drawGuiContainerForegroundLayer();
        fontRenderer.drawString("Solar Generator", 46, 6, 0xFF404040);
        fontRenderer.drawString("Inventory", 8, ySize - 96 + 2, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int tex = mc.renderEngine.getTexture("/assets/industry/gui/generator_solar.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(tex);

        int texX = (width - xSize) / 2;
        int texY = (height - ySize) / 2;
        drawTexturedModalRect(texX, texY, 0, 0, xSize, ySize);

        // Energy Bar
        // Thanks to Astoria for the help!
        double power = (float) tile.energy / (float) tile.capacity;
        drawTexturedModalRect(texX + 80, texY + 58, 176, 0, (int) (power * 15), 7);
    }

    @Override
    public void drawScreen(int x, int y, float renderPartialTicks) {
        int screenX = (width - xSize) / 2;
        int screenY = (height - ySize) / 2;
        super.drawScreen(x, y, renderPartialTicks);
        StringBuilder text = new StringBuilder();
        if(x > screenX + 80 && x < screenX + 94)
            if (y > screenY + 58 && y < screenY + 65) {
                text.append(Config.getFromConfig("energyName", "Energy")).append(": ").append(tile.energy).append(" ").append(Config.getFromConfig("energySuffix", "E")).append("/").append(tile.capacity).append(" ").append(Config.getFromConfig("energySuffix", "E"));
                drawTooltip(text.toString(), x, y, 8, -8, true);
        }
    }
}