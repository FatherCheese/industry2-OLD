package turniplabs.industry.recipes

import net.minecraft.src.Block
import net.minecraft.src.ItemStack
import turniplabs.industry.block.IndustryBlocks
import turniplabs.industry.item.IndustryItems

object RecipesMacerator {
    private val maceratorRecipes: RecipesMacerator = RecipesMacerator
    private var map = HashMap<Int?, ItemStack?>()

    init {
        addRecipe(IndustryItems.RAW_COPPER.itemID, ItemStack(IndustryItems.DUST_COPPER, 2))
        addRecipe(IndustryItems.RAW_TIN.itemID, ItemStack(IndustryItems.DUST_TIN, 2))

        addRecipe(IndustryBlocks.ORE_COPPER_STONE.blockID, ItemStack(IndustryItems.DUST_COPPER, 2))
        addRecipe(IndustryBlocks.ORE_COPPER_BASALT.blockID, ItemStack(IndustryItems.DUST_COPPER, 2))
        addRecipe(IndustryBlocks.ORE_COPPER_LIMESTONE.blockID, ItemStack(IndustryItems.DUST_COPPER, 2))
        addRecipe(IndustryBlocks.ORE_COPPER_GRANITE.blockID, ItemStack(IndustryItems.DUST_COPPER, 2))

        addRecipe(IndustryBlocks.ORE_TIN_STONE.blockID, ItemStack(IndustryItems.DUST_TIN, 2))
        addRecipe(IndustryBlocks.ORE_TIN_BASALT.blockID, ItemStack(IndustryItems.DUST_TIN, 2))
        addRecipe(IndustryBlocks.ORE_TIN_LIMESTONE.blockID, ItemStack(IndustryItems.DUST_TIN, 2))
        addRecipe(IndustryBlocks.ORE_TIN_GRANITE.blockID, ItemStack(IndustryItems.DUST_TIN, 2))

        addRecipe(Block.oreIronStone.blockID, ItemStack(IndustryItems.DUST_IRON, 2))
        addRecipe(Block.oreIronBasalt.blockID, ItemStack(IndustryItems.DUST_IRON, 2))
        addRecipe(Block.oreIronLimestone.blockID, ItemStack(IndustryItems.DUST_IRON, 2))
        addRecipe(Block.oreIronGranite.blockID, ItemStack(IndustryItems.DUST_IRON, 2))

        addRecipe(Block.oreGoldStone.blockID, ItemStack(IndustryItems.DUST_GOLD, 2))
        addRecipe(Block.oreGoldBasalt.blockID, ItemStack(IndustryItems.DUST_GOLD, 2))
        addRecipe(Block.oreGoldLimestone.blockID, ItemStack(IndustryItems.DUST_GOLD, 2))
        addRecipe(Block.oreGoldGranite.blockID, ItemStack(IndustryItems.DUST_GOLD, 2))
    }

    fun addRecipe(input: Int, output: ItemStack) {
        map[input] = output
    }

    fun getResult(id: Int): ItemStack {
        return this.map[id] as ItemStack
    }

    fun getList(): Map<*, *> {
        return map
    }

    fun crushing(): RecipesMacerator {
        return this.maceratorRecipes
    }
}