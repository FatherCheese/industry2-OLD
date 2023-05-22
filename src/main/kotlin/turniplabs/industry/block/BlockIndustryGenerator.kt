package turniplabs.industry.block

import net.minecraft.src.*
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.industry.block.tile.TileEntityIndustryGenerator
import turniplabs.industry.container.ContainerIndustryGenerator
import turniplabs.industry.gui.GuiIndustryGenerator


class BlockIndustryGenerator(i: Int, material: Material?) : BlockContainerRotatable(i, material) {

    override fun getBlockEntity(): TileEntity {
        return TileEntityIndustryGenerator()
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

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, entityPlayer: EntityPlayer?): Boolean {
        if (!world?.isMultiplayerAndNotHost!!) {
            val tile: TileEntityIndustryGenerator = world.getBlockTileEntity(x, y, z) as TileEntityIndustryGenerator

            if (tile != null)
                EnergyAPI.displayGui(
                    entityPlayer,
                    GuiIndustryGenerator(entityPlayer?.inventory, tile),
                    ContainerIndustryGenerator(entityPlayer?.inventory!!, tile),
                    entityPlayer.inventory
                )
        }
        return true
    }
}