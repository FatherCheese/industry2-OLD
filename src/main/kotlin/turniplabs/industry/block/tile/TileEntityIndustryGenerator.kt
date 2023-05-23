package turniplabs.industry.block.tile

import net.minecraft.src.*
import sunsetsatellite.energyapi.api.LookupFuelEnergy
import sunsetsatellite.energyapi.impl.ItemEnergyContainer
import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.energyapi.util.Connection
import sunsetsatellite.energyapi.util.Direction

// NOTE - this entire class was auto-translated to Kotlin due to Array out of bounds errors when rewritten, go through this later!
class TileEntityIndustryGenerator : TileEntityEnergyConductor(), IInventory {
    private var contents: Array<ItemStack?>
    var maxBurnTime = 0
    var currentBurnTime = 0
    private var currentFuel: ItemStack? = null

    init {
        setCapacity(4096)
        setTransfer(32)
        contents = arrayOfNulls(2)
        for (dir in Direction.values()) setConnection(dir, Connection.OUTPUT)
    }

    override fun updateEntity() {
        super.updateEntity()
        if (currentBurnTime > 0) {
            --currentBurnTime
            modifyEnergy(getEnergyYieldForItem(currentFuel))
        }
        if (currentBurnTime == 0) {
            currentBurnTime = getBurnTimeFromItem(contents[1]) / 5
            maxBurnTime = currentBurnTime
            if (currentBurnTime > 0) {
                currentFuel = contents[1]
                onInventoryChanged()
                if (contents[1] != null) {
                    --contents[1]!!.stackSize
                    if (contents[1]!!.stackSize == 0) contents[1] = null
                }
            } else currentFuel = null
        }
        if (getStackInSlot(1) != null && getStackInSlot(1)?.item is ItemEnergyContainer) {
            val stack = getStackInSlot(1)
            provide(stack, getMaxProvide(), false)
            onInventoryChanged()
        }
        if (getStackInSlot(0) != null && getStackInSlot(0)?.item is ItemEnergyContainer) {
            val stack = getStackInSlot(0)
            val item = getStackInSlot(0)?.item as ItemEnergyContainer
            receive(stack, getMaxReceive(), false)
            onInventoryChanged()
        }
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

    override fun onInventoryChanged() {
        super.onInventoryChanged()
    }

    override fun getInvName(): String {
        return "Generator"
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
        currentBurnTime = nbttagcompound.getInteger("BurnTime")
        maxBurnTime = nbttagcompound.getInteger("MaxBurnTime")
        currentFuel = ItemStack(nbttagcompound.getCompoundTag("CurrentFuel"))
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
        val fuel = NBTTagCompound()
        if (currentFuel != null) {
            currentFuel!!.writeToNBT(fuel)
        }
        nbttagcompound.setTag("Items", nbtTagList)
        nbttagcompound.setCompoundTag("CurrentFuel", fuel)
        nbttagcompound.setInteger("BurnTime", currentBurnTime.toShort().toInt())
        nbttagcompound.setInteger("MaxBurnTime", maxBurnTime.toShort().toInt())
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

    private fun getBurnTimeFromItem(itemStack: ItemStack?): Int {
        return if (itemStack == null) 0 else LookupFuelFurnace.fuelFurnace().getFuelYield(itemStack.item.itemID)
    }

    private fun getEnergyYieldForItem(itemStack: ItemStack?): Int {
        return if (itemStack == null) 0 else LookupFuelEnergy.fuelEnergy().getEnergyYield(itemStack.item.itemID)
    }

    fun getBurnTimeRemainingScaled(i: Int): Int {
        return if (maxBurnTime == 0) 0 else currentBurnTime * i / maxBurnTime
    }

    val isBurning: Boolean
        get() = currentBurnTime > 0
}