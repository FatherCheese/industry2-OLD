package turniplabs.industry.block

import net.minecraft.src.*
import java.util.*

class BlockLeavesRubber(i: Int, material: Material?, flag: Boolean) : BlockLeavesBase(i, material, flag) {

    override fun getRenderColor(i: Int): Int {
        return ColorProperties.fRGB2iRGB(
            ColorProperties.pine.inventoryR,
            ColorProperties.pine.inventoryG,
            ColorProperties.pine.inventoryB
        )
    }

    override fun colorMultiplier(world: World?, iblockaccess: IBlockAccess?, x: Int, y: Int, z: Int): Int {
        var baseFoliageColor = ColorProperties.fRGB2iRGB(
            ColorProperties.pine.inventoryR,
            ColorProperties.pine.inventoryG,
            ColorProperties.pine.inventoryB
        )

        if (world != null) {
            val localTemp: Double? = iblockaccess?.worldChunkManager?.getTemperature(x, z)
            val localHumid: Double? = iblockaccess?.worldChunkManager?.getHumidity(x, z)
            val season: Season = world.currentSeason

            if (season != null) {
                val progress: Float = world.seasonProgress
                baseFoliageColor =
                    ColorizerFoliage.getSeasonalColor(season, progress, localTemp!!, localHumid!!, ColorProperties.pine)
            }
        }
        return baseFoliageColor
    }

    override fun idDropped(i: Int, random: Random?): Int {
        return IndustryBlocks.RUBBER_SAPLING.blockID
    }

    override fun quantityDropped(metadata: Int, random: Random?): Int {
        return if (random?.nextInt(20) == 0) 1 else 0
    }

    override fun harvestBlock(world: World?, entityplayer: EntityPlayer?, x: Int, y: Int, z: Int, meta: Int) {
        if (!world?.isMultiplayerAndNotHost!! &&
            entityplayer?.currentEquippedItem != null &&
            entityplayer.currentEquippedItem?.itemID == Item.toolShears.itemID
        ) {
            entityplayer.addStat(StatList.mineBlockStatArray[blockID], 1)
            world.dropItem(x, y, z, ItemStack(this.blockID, 1, meta))
        } else super.harvestBlock(world, entityplayer, x, y, z, meta)
    }
}