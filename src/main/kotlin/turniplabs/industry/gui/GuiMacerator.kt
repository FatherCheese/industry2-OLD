package turniplabs.industry.gui

import net.minecraft.src.GuiContainer
import net.minecraft.src.InventoryPlayer
import org.lwjgl.opengl.GL11
import sunsetsatellite.energyapi.util.Config
import turniplabs.industry.block.tile.TileEntityMacerator

class GuiMacerator(container: InventoryPlayer?, private val tile: TileEntityMacerator) : GuiContainer(
    ContainerMacerator(container!!, tile)
) {

    override fun drawGuiContainerBackgroundLayer(f: Float) {
        val texture: Int = mc.renderEngine.getTexture("/assets/industry/gui/macerator.png")

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        mc.renderEngine.bindTexture(texture)

        val textX: Int = (width - xSize) / 2
        val textY: Int = (height - ySize) / 2
        drawTexturedModalRect(textX, textY, 0, 0, xSize, ySize)

        val power: Double = (tile.energy.toFloat() / tile.capacity.toFloat()).toDouble()
        drawTexturedModalRect(textX + 56, textY + 39, 176, 0, (power * 16).toInt(), 8)

        val progressBar: Int = tile.getCrushProgressScaled(23)
        this.drawTexturedModalRect(textX + 79, textY + 35, 176, 8, progressBar + 1, 23)
    }

    override fun drawGuiContainerForegroundLayer() {
        super.drawGuiContainerForegroundLayer()
        fontRenderer.drawString("Macerator", 64, 6, 0xFF404040.toInt())
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0xFF404040.toInt())
    }

    override fun drawScreen(x: Int, y: Int, renderPartialTicks: Float) {
        val screenX: Int = (width - xSize) / 2
        val screenY: Int = (height - ySize) / 2
        super.drawScreen(x, y, renderPartialTicks)

        val text: StringBuilder = StringBuilder()
        if (x > screenX + 56 && x < screenX + 72) {
            if (y > screenY + 39 && y < screenY + 47) {
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
}