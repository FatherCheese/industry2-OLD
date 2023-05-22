package turniplabs.industry.block

import net.minecraft.src.Block
import net.minecraft.src.Material
import turniplabs.industry.item.IndustryItems
import java.util.*

class BlockOreTin(i: Int, material: Material?) : Block(i, material) {

    override fun idDropped(i: Int, random: Random?): Int {
        return IndustryItems.RAW_TIN.itemID
    }
}