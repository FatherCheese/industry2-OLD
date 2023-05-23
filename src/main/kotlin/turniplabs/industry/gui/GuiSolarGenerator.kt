package turniplabs.industry.gui

import net.minecraft.src.GuiContainer
import net.minecraft.src.InventoryPlayer
import org.lwjgl.opengl.GL11
import sunsetsatellite.energyapi.util.Config
import turniplabs.industry.block.tile.TileEntitySolarGenerator

class GuiSolarGenerator(inventoryPlayer: InventoryPlayer, tile: TileEntitySolarGenerator) : GuiContainer(ContainerSolarGenerator(inventoryPlayer, tile)) {
    var tile: TileEntitySolarGenerator

    init {
        this.tile = tile
    }

    override fun drawGuiContainerBackgroundLayer(f: Float) {
        val tex = mc.renderEngine.getTexture("/assets/industry/gui/generator_solar.png")
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        mc.renderEngine.bindTexture(tex)
        val texX = (width - xSize) / 2
        val texY = (height - ySize) / 2
        drawTexturedModalRect(texX, texY, 0, 0, xSize, ySize)

        // Energy Bar
        // Thanks to Astoria for the help!
        val power = (tile.energy.toFloat() / tile.capacity.toFloat()).toDouble()
        drawTexturedModalRect(texX + 80, texY + 57, 176, 0, (power * 16).toInt(), 8)

        // TODO - turn to moon if it's night or blocked (only happens on reload atm)
        if (tile.generatedEnergy > 0) drawTexturedModalRect(
            texX + 84,
            texY + 21,
            176,
            8,
            8,
            8
        ) else drawTexturedModalRect(texX + 84, texY + 21, 184, 8, 8, 8)
    }

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("Solar Generator", 46, 6, 0xFF404040.toInt())
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040)
    }

    override fun drawScreen(x: Int, y: Int, renderPartialTicks: Float) {
        val screenX: Int = (width - xSize) / 2
        val screenY: Int = (height - ySize) / 2
        super.drawScreen(x, y, renderPartialTicks)

        val text = StringBuilder()
        if (x > screenX + 80 && x < screenX + 96)
            if (y > screenY + 57 && y < screenY + 66) {
                text.append(Config.getFromConfig("energyName", "Energy")).append(": ").append(tile.energy).append(" ")
                    .append(
                        Config.getFromConfig(
                            "energySuffix",
                            "E"
                        )
                    ).append("/").append(tile.capacity).append(" ").append(Config.getFromConfig("energySuffix", "E"))
                drawTooltip(text.toString(), x, y, 8, -8, true)
            }
    }
}