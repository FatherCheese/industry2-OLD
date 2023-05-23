package turniplabs.industry

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.halplibe.helper.EntityHelper
import turniplabs.industry.block.IndustryBlocks
import turniplabs.industry.block.tile.TileEntityCable
import turniplabs.industry.block.tile.TileEntityIndustryGenerator
import turniplabs.industry.block.tile.TileEntitySolarGenerator
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

    EnergyAPI.addToNameGuiMap("Generator", GuiIndustryGenerator::class.java, TileEntityIndustryGenerator::class.java)
    EnergyAPI.addToNameGuiMap("SolarGenerator", GuiSolarGenerator::class.java, TileEntitySolarGenerator::class.java)
}