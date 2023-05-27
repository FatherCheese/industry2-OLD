package turniplabs.industry.block.tile

import net.minecraft.src.Block
import net.minecraft.src.Season
import sunsetsatellite.energyapi.template.tiles.TileEntityBatteryBox
import sunsetsatellite.energyapi.util.Connection
import sunsetsatellite.energyapi.util.Direction
import kotlin.math.min


class TileEntitySolarGenerator : TileEntityBatteryBox() {
    var generatedEnergy: Int = 0

    init {
        setCapacity(256)
        setTransfer(8)

        for (dir: Direction in Direction.values()) setConnection(dir, Connection.OUTPUT)
    }

    override fun updateEntity() {
        if (energy < capacity && isFacingSky()) {
            generatedEnergy = 4

            if (worldObj.getBlockTemperature(xCoord, zCoord) > 0.85f && worldObj.getBlockHumidity(xCoord, zCoord) < 0.30f)
                generatedEnergy += 2

            if (worldObj.getBlockTemperature(xCoord, zCoord) < 0.40f && worldObj.getBlockHumidity(xCoord, zCoord) < 0.30f)
                generatedEnergy -= 2

            if (yCoord > 150) generatedEnergy += yCoord / 100

            if (worldObj.currentSeason === Season.surfaceWinter || worldObj.currentSeason === Season.surfaceFall) generatedEnergy -= 2
            generatedEnergy -= worldObj.skylightSubtracted

            if (generatedEnergy > 0) energy = min(energy + generatedEnergy, capacity)
        }

        super.updateEntity()
    }

    private fun isFacingSky(): Boolean {
        for (newYCoord in yCoord + 1..254) {
            val block = Block.getBlock(worldObj.getBlockId(xCoord, newYCoord, zCoord))
            if (block != null && block.isOpaqueCube) return false
        }
        return true
    }
}