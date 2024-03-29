package turniplabs.industry.block.tile

import net.minecraft.src.*
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.energyapi.util.Connection
import sunsetsatellite.energyapi.util.Direction
import turniplabs.industry.block.IndustryBlocks
import turniplabs.industry.block.machine.BlockMacerator
import turniplabs.industry.recipes.RecipesMacerator

// NOTE - this entire class was auto-translated to Kotlin due to Array out of bounds errors when rewritten, go through this later!
class TileEntityMacerator : TileEntityEnergyConductor(), IInventory {
    var active = false
    private var contents: Array<ItemStack?>
    private var currentCrushTime = 0
    private var maxCrushTime = 256

    init {
        setCapacity(4096)
        setTransfer(0)
        setMaxReceive(32)
        contents = arrayOfNulls(3)
        for (dir: Direction in Direction.values()) setConnection(dir, Connection.INPUT)
    }

    // TODO - variable "active" is only updating when the block next to is updated
    override fun updateEntity() {
        super.updateEntity()
        val hasEnergy: Boolean = energy > 0
        var machineUpdated = false

        if (getStackInSlot(1) != null && getStackInSlot(1)?.item is ItemEnergyContainer) {
            val stack: ItemStack? = getStackInSlot(1)
            receive(stack, getMaxReceive(), false)
            onInventoryChanged()
        }

        if (!worldObj!!.isMultiplayerAndNotHost) {
            if (worldObj!!.getBlockId(xCoord, yCoord, zCoord) == IndustryBlocks.MACHINE_MACERATOR.blockID &&
                currentCrushTime == 0 &&
                contents[0] == null
                ) {
                BlockMacerator.updateBlockState(true, worldObj, xCoord, yCoord, zCoord)
                machineUpdated = true

            }

            if (hasEnergy && canCrush()) {
                ++currentCrushTime
                --energy
                active = true
                if (currentCrushTime == maxCrushTime) {
                    currentCrushTime = 0
                    crushItem()
                    active = false
                    machineUpdated = true
                }
            } else {
                currentCrushTime = 0
                active = false
            }
        }
        if (machineUpdated) this.onInventoryChanged()
    }

    override fun getSizeInventory(): Int {
        return contents.size
    }

    override fun getStackInSlot(i: Int): ItemStack? {
        return contents[i]
    }

    override fun decrStackSize(i: Int, j: Int): ItemStack? {
        return if (contents[i] != null) {
            if (contents[i]!!.stackSize <= j) {
                val itemStack: ItemStack? = contents[i]
                contents[i] = null
                onInventoryChanged()
                return itemStack!!
            }
            val itemStack: ItemStack = contents[i]!!.splitStack(j)
            if (contents[i]!!.stackSize == 0) {
                contents[i] = null
            }
            onInventoryChanged()
            itemStack
        } else {
            null
        }
    }

    override fun setInventorySlotContents(i: Int, itemstack: ItemStack) {
        contents[i] = itemstack
        if (itemstack != null && itemstack.stackSize > inventoryStackLimit) {
            itemstack.stackSize = inventoryStackLimit
        }
        onInventoryChanged()
    }

    override fun getInvName(): String {
        return "Macerator"
    }

    override fun readFromNBT(nbttagcompound: NBTTagCompound) {
        super.readFromNBT(nbttagcompound)
        val nbtTagList: NBTTagList = nbttagcompound.getTagList("Items")

        contents = arrayOfNulls(sizeInventory)
        for (i in 0 until nbtTagList.tagCount()) {
            val nbtTagCompound1 = nbtTagList.tagAt(i) as NBTTagCompound
            val nbtTagCompoundByte: Int = nbtTagCompound1.getByte("Slot").toInt() and 0xff

            if (nbtTagCompoundByte < contents.size) contents[nbtTagCompoundByte] = ItemStack(nbtTagCompound1)
        }
    }

    override fun writeToNBT(nbttagcompound: NBTTagCompound) {
        super.writeToNBT(nbttagcompound)
        val nbtTagList = NBTTagList()
        for (i in contents.indices) {
            if (contents[i] != null) {
                val nbtTagCompound1 = NBTTagCompound()
                nbtTagCompound1.setByte("Slot", i.toByte())
                contents[i]!!.writeToNBT(nbtTagCompound1)
                nbtTagList.setTag(nbtTagCompound1)
            }
        }
        nbttagcompound.setTag("Items", nbtTagList)
    }

    override fun getInventoryStackLimit(): Int {
        return 64
    }

    override fun canInteractWith(entityplayer: EntityPlayer): Boolean {
        return if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) !== this) {
            false
        } else entityplayer.getDistanceSq(
            xCoord.toDouble() + 0.5,
            yCoord.toDouble() + 0.5,
            zCoord.toDouble() + 0.5
        ) <= 64.0
    }

    fun getCrushProgressScaled(i: Int): Int {
        return if (maxCrushTime == 0) 0 else currentCrushTime * i / maxCrushTime
    }

    private fun canCrush(): Boolean {
        if (contents[0] == null) return false

        val itemStack: ItemStack = RecipesMacerator.crushing().getResult(contents[0]!!.item.itemID)
        if (itemStack == null) return false

        if (contents[2] == null) return true

        if (!contents[2]!!.isItemEqual(itemStack)) return false

        if (contents[2]!!.stackSize < inventoryStackLimit && contents[2]!!.stackSize < contents[2]!!.maxStackSize)
            return true

        return contents[2]!!.stackSize < itemStack.maxStackSize
    }

    private fun crushItem() {
        if (canCrush()) {
            val itemStack: ItemStack = RecipesMacerator.crushing().getResult(contents[0]!!.item.itemID)
            if (contents[2] == null) contents[2] = itemStack.copy()
            else if (contents[2]!!.itemID == itemStack.itemID) ++contents[2]!!.stackSize

            --contents[0]!!.stackSize
            if (contents[0]!!.stackSize <= 0) contents[0] = null
        }
    }
}