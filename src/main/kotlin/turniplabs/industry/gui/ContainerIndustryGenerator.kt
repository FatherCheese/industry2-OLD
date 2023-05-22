package turniplabs.industry.container

import net.minecraft.src.EntityPlayer
import net.minecraft.src.IInventory
import net.minecraft.src.Slot
import sunsetsatellite.energyapi.template.containers.ContainerEnergy
import turniplabs.industry.block.tile.TileEntityIndustryGenerator

// This class was auto-translated to Kotlin, check on this later!!
class ContainerIndustryGenerator(iInventory: IInventory?, tileEntity: TileEntityIndustryGenerator) : ContainerEnergy() {
    private var maxBurnTime = 0
    private var currentBurnTime = 0

    init {
        tile = tileEntity
        addSlot(Slot(tileEntity, 0, 80, 17))
        addSlot(Slot(tileEntity, 1, 80, 53))
        for (ySlot in 0..2) for (xSlot in 0..8) addSlot(
            Slot(
                iInventory,
                xSlot + ySlot * 9 + 9,
                8 + xSlot * 18,
                84 + ySlot * 18
            )
        )
        for (hotbar in 0..8) addSlot(Slot(iInventory, hotbar, 8 + hotbar * 18, 142))
    }

    override fun updateInventory() {
        super.updateInventory()
        for (crafter in crafters) {
            if (currentBurnTime != (tile as TileEntityIndustryGenerator).currentBurnTime) crafter.updateCraftingInventoryInfo(
                this,
                1,
                (tile as TileEntityIndustryGenerator).currentBurnTime
            )
            if (maxBurnTime != (tile as TileEntityIndustryGenerator).maxBurnTime) crafter.updateCraftingInventoryInfo(
                this,
                2,
                (tile as TileEntityIndustryGenerator).maxBurnTime
            )
        }
        currentBurnTime = (tile as TileEntityIndustryGenerator).currentBurnTime
        maxBurnTime = (tile as TileEntityIndustryGenerator).maxBurnTime
    }

    override fun updateClientProgressBar(id: Int, value: Int) {
        if (id == 1) (tile as TileEntityIndustryGenerator).currentBurnTime = value
        if (id == 2) (tile as TileEntityIndustryGenerator).maxBurnTime = value
    }

    override fun isUsableByPlayer(entityPlayer: EntityPlayer): Boolean {
        return true
    }

    override fun quickMoveItems(i: Int, entityPlayer: EntityPlayer, bl: Boolean, bl2: Boolean) {}
}