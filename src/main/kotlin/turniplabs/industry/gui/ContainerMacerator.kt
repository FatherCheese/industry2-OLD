package turniplabs.industry.gui

import net.minecraft.src.*
import sunsetsatellite.energyapi.template.containers.ContainerEnergy
import turniplabs.industry.block.tile.TileEntityMacerator

// This class was auto-translated to Kotlin, check on this later!!
class ContainerMacerator(iInventory: InventoryPlayer?, tileEntity: TileEntityMacerator) : ContainerEnergy() {

    init {
        tile = tileEntity
        addSlot(Slot(tileEntity, 0, 56, 17))
        addSlot(Slot(tileEntity, 1, 56, 53))
        addSlot(SlotFurnace(iInventory?.player, tileEntity, 2, 116, 35))
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
        for (crafter: ICrafting in crafters) {
        }
    }

    override fun updateClientProgressBar(id: Int, value: Int) {
    }

    override fun isUsableByPlayer(entityPlayer: EntityPlayer): Boolean {
        return (tile as TileEntityMacerator).canInteractWith(entityPlayer)
    }

    override fun quickMoveItems(i: Int, entityPlayer: EntityPlayer, bl: Boolean, bl2: Boolean) {}
}