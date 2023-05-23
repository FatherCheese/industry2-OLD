package turniplabs.industry.block.machine

import net.minecraft.src.*
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.industry.block.tile.TileEntitySolarGenerator
import turniplabs.industry.gui.ContainerSolarGenerator
import turniplabs.industry.gui.GuiSolarGenerator

class BlockSolarGenerator(i: Int, material: Material?) : BlockContainer(i, material) {

    override fun getBlockEntity(): TileEntity {
        return TileEntitySolarGenerator()
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, entityPlayer: EntityPlayer?): Boolean {
        if (!world!!.isMultiplayerAndNotHost) {
            val tile: TileEntitySolarGenerator = world.getBlockTileEntity(x, y, z) as TileEntitySolarGenerator

            if (tile != null)
                EnergyAPI.displayGui(entityPlayer, GuiSolarGenerator(entityPlayer!!.inventory, tile),
                    ContainerSolarGenerator(entityPlayer.inventory, tile), tile)
        }
        return true
    }

    override fun onBlockRemoval(world: World?, x: Int, y: Int, z: Int) {
        val inventory: IInventory = world?.getBlockTileEntity(x, y, z) as IInventory

        label0@
        for (invSize in 0 until inventory.sizeInventory) {
            val itemStack: ItemStack = inventory.getStackInSlot(invSize) ?: continue

            val fX: Float = (world.rand.nextFloat() * 0.8f) + 0.1f
            val fY: Float = (world.rand.nextFloat() * 0.8f) + 0.1f
            val fZ: Float = (world.rand.nextFloat() * 0.8f) + 0.1f
            do {
                if (itemStack.stackSize <= 0)
                    continue@label0

                var worldRand: Int = world.rand.nextInt(21) + 10
                if (worldRand > itemStack.stackSize)
                    worldRand = itemStack.stackSize
                itemStack.stackSize -= worldRand

                val item = EntityItem(
                    world,
                    (x.toFloat() + fX).toDouble(),
                    (y.toFloat() + fY).toDouble(),
                    (z.toFloat() + fZ).toDouble(),
                    ItemStack(itemStack.itemID, worldRand, itemStack.metadata, itemStack.tag)
                )
                item.motionX = world.rand.nextGaussian() * 0.05f
                item.motionY = world.rand.nextGaussian() * 0.05f
                item.motionZ = world.rand.nextGaussian() * 0.05f
                world.entityJoinedWorld(item)
            } while (true)
        }
        super.onBlockRemoval(world, x, y, z)
    }
}