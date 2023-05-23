package turniplabs.industry.gui

import net.minecraft.src.EntityPlayer
import net.minecraft.src.IInventory
import net.minecraft.src.Slot
import sunsetsatellite.energyapi.template.containers.ContainerEnergy
import turniplabs.industry.block.tile.TileEntitySolarGenerator

class ContainerSolarGenerator(iInventory: IInventory, solarGenerator: TileEntitySolarGenerator): ContainerEnergy() {

    init {
        tile = solarGenerator
        addSlot(Slot(solarGenerator, 0, 80, 35))

        for (ySlot in 0 until 3)
            for (xSlot in 0 until 9)
                addSlot(Slot(iInventory, xSlot + (ySlot * 9) + 9, 8 + (xSlot * 18), 84 + (ySlot * 18)))

        for (hotbar in 0 until 9)
            addSlot(Slot(iInventory, hotbar, 8 + (hotbar * 18), 142))
    }

    override fun isUsableByPlayer(entityPlayer: EntityPlayer?): Boolean {
        return true
    }
}