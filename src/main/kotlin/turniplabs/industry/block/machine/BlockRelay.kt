package turniplabs.industry.block.machine

import net.minecraft.src.Material
import net.minecraft.src.TileEntity
import net.minecraft.src.World
import turniplabs.industry.block.BlockCable
import turniplabs.industry.block.tile.TileEntityRelay
import java.util.*

class BlockRelay(i: Int, material: Material?, private val capacity: Int, private val energy: Int, private val transfer: Int) :
    BlockCable(i, material, capacity, energy, transfer, 0) {

    override fun onBlockAdded(world: World?, x: Int, y: Int, z: Int) {
        super.onBlockAdded(world, x, y, z)
        world?.scheduleBlockUpdate(x, y, z, blockID, tickRate())
    }

    override fun onNeighborBlockChange(world: World?, x: Int, y: Int, z: Int, l: Int) {
        world?.scheduleBlockUpdate(x, y, z, blockID, tickRate())
    }

    override fun updateTick(world: World?, x: Int, y: Int, z: Int, random: Random?) {
        val isPoweredByBlock: Boolean = world?.isBlockGettingPowered(x, y, z)!! || world.isBlockIndirectlyGettingPowered(x, y, z)
        val tile: TileEntityRelay = world.getBlockTileEntity(x, y, z) as TileEntityRelay

        tile.setPowered(isPoweredByBlock)
    }

    override fun getBlockEntity(): TileEntity {
        return TileEntityRelay(this.capacity, this.energy, this.transfer)
    }

    override fun getRenderType(): Int {
        return 0
    }
}