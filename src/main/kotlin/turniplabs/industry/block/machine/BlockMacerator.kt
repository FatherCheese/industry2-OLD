package turniplabs.industry.block.machine

import net.minecraft.src.*
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.industry.Industry2
import turniplabs.industry.block.tile.TileEntityMacerator
import turniplabs.industry.gui.ContainerMacerator
import turniplabs.industry.gui.GuiMacerator


class BlockMacerator(i: Int, material: Material?) : BlockContainerRotatable(i, material) {
    private var active: Boolean = false
    private var keepInventory: Boolean = false

    init {
        setupInstance(this)
    }

    override fun getBlockEntity(): TileEntity {
        return TileEntityMacerator()
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
            val tile: TileEntityMacerator = world.getBlockTileEntity(x, y, z) as TileEntityMacerator

            if (tile != null)
                EnergyAPI.displayGui(
                    entityPlayer,
                    GuiMacerator(entityPlayer?.inventory, tile),
                    ContainerMacerator(entityPlayer?.inventory!!, tile),
                    entityPlayer.inventory
                )
        }
        return true
    }

    override fun getBlockTexture(iblockaccess: IBlockAccess?, x: Int, y: Int, z: Int, side: Int): Int {
        val tileEntity: TileEntityMacerator = iblockaccess?.getBlockTileEntity(x, y, z) as TileEntityMacerator
        val metadata: Int = iblockaccess.getBlockMetadata(x, y, z)

        /*
        0 = bottom
        1 = top
        2 = east
        3 = west
        4 = north
        5 = south
         */

        val index: Int = Sides.orientationLookUp[6 * metadata + side]
        if (index in 2..5) {
            if (tileEntity.active)
                return texCoordToIndex(
                    Industry2.maceratorTexture[1][0],
                    Industry2.maceratorTexture[1][1]
                ).also { atlasIndices[index] = it }
             else return texCoordToIndex(
                Industry2.maceratorTexture[0][0],
                Industry2.maceratorTexture[0][1]
             ).also { atlasIndices[index] = it }
        }
        return atlasIndices[index]
    }

    // This is required since Kotlin doesn't support static methods
    // It also doesn't support calling main functions in Companion Objects, so it requires even more craziness
    companion object {
        private var instance: BlockMacerator? = null

        fun setupInstance(macerator: BlockMacerator) {
            instance = macerator
        }

        fun updateBlockState(active: Boolean, world: World?, x: Int, y: Int, z: Int) {
            val metadata: Int? = world?.getBlockMetadata(x, y, z)
            val tile: TileEntity? = world?.getBlockTileEntity(x, y, z)

            if (tile == null) world?.setBlockWithNotify(x, y, z, 0)
            else {
                getInstance().keepInventory = true
                if (active) world.setBlockMetadataWithNotify(x, y, z, 1)
                if (!active) world.setBlockMetadataWithNotify(x, y, z, 0)

                getInstance().keepInventory = false
                world.setBlockMetadataWithNotify(x, y, z, metadata!!)
                tile.validate()
                world.setBlockTileEntity(x, y, z, tile)
            }
        }

        private fun getInstance(): BlockMacerator {
            return instance ?: throw NullPointerException("Instance of BlockMacerator hasn't been set!")
        }
    }
}