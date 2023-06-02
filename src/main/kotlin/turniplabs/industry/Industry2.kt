package turniplabs.industry

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.halplibe.helper.EntityHelper
import turniplabs.halplibe.helper.RecipeHelper
import turniplabs.halplibe.helper.TextureHelper
import turniplabs.industry.block.IndustryBlocks
import turniplabs.industry.block.tile.*
import turniplabs.industry.gui.GuiElectricFurnace
import turniplabs.industry.gui.GuiIndustryGenerator
import turniplabs.industry.gui.GuiSolarGenerator
import turniplabs.industry.item.IndustryItems

object Industry2 {
    const val MODID = "industry"
    val LOGGER: Logger = LoggerFactory.getLogger(MODID)
}

@Suppress("unused")
fun init() {

    Industry2.LOGGER.info("Industry2 has been initialized. Get to automating!")

    IndustryBlocks
    IndustryItems

    EntityHelper.createTileEntity(TileEntityCable::class.java, "CABLE")
    EntityHelper.createTileEntity(TileEntityIndustryGenerator::class.java, "GENERATOR")
    EntityHelper.createTileEntity(TileEntitySolarGenerator::class.java, "SOLAR_GENERATOR")
    EntityHelper.createTileEntity(TileEntityMacerator::class.java, "MACERATOR")
    EntityHelper.createTileEntity(TileEntityElectricFurnace::class.java, "ELECTRIC_FURNACE")

    EnergyAPI.addToNameGuiMap("Generator", GuiIndustryGenerator::class.java, TileEntityIndustryGenerator::class.java)
    EnergyAPI.addToNameGuiMap("SolarGenerator", GuiSolarGenerator::class.java, TileEntitySolarGenerator::class.java)
    EnergyAPI.addToNameGuiMap("ElectricFurnace", GuiElectricFurnace::class.java, TileEntityElectricFurnace::class.java)

    // Recipes
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_COPPER, IndustryBlocks.ORE_COPPER_STONE)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_COPPER, IndustryBlocks.ORE_COPPER_BASALT)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_COPPER, IndustryBlocks.ORE_COPPER_LIMESTONE)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_COPPER, IndustryBlocks.ORE_COPPER_GRANITE)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_COPPER, IndustryItems.RAW_COPPER)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_COPPER, IndustryItems.DUST_COPPER)

    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_TIN, IndustryBlocks.ORE_TIN_STONE)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_TIN, IndustryBlocks.ORE_TIN_BASALT)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_TIN, IndustryBlocks.ORE_TIN_LIMESTONE)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_TIN, IndustryBlocks.ORE_TIN_GRANITE)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_TIN, IndustryItems.RAW_TIN)
    RecipeHelper.Smelting.createRecipe(IndustryItems.INGOT_TIN, IndustryItems.DUST_TIN)
}