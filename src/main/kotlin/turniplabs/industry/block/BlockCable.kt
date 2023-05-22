package turniplabs.industry.block

import net.minecraft.src.*
import net.minecraft.src.command.ChatColor
import sunsetsatellite.energyapi.util.Config
import sunsetsatellite.energyapi.util.ICustomDescription
import turniplabs.industry.block.tile.TileEntityCable

class BlockCable(
    i: Int,
    material: Material?,
    private val capacity: Int,
    private val energy: Int,
    private val transfer: Int,
    private val dangerLevel: Int,
) : BlockContainer(i, material), ICustomDescription {

    override fun getBlockEntity(): TileEntity {
        return TileEntityCable(capacity, energy, transfer, dangerLevel)
    }

    override fun getDescription(stack: ItemStack?): String? {
        return ChatColor.white.toString() + "Max Transfer: " + ChatColor.lightGray + transfer + " " + Config.getFromConfig(
            "energySuffix",
            "E"
        ) + " IN " + "| " + transfer + " " + Config.getFromConfig("energySuffix", "E") + " OUT"
    }

    override fun renderAsNormalBlock(): Boolean {
        return false
    }

    override fun blockActivated(world: World?, i: Int, j: Int, k: Int, entityplayer: EntityPlayer?): Boolean {
        return false
    }

    override fun getRenderType(): Int {
        return 31
    }

    override fun isOpaqueCube(): Boolean {
        return false
    }
}