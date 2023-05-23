package turniplabs.industry.block.tile

import sunsetsatellite.energyapi.util.Connection
import sunsetsatellite.energyapi.util.Direction

class TileEntityRelay(capacity: Int, energy: Int, transfer: Int): TileEntityEnergyConductorDamageable() {

    private var isPowered: Boolean = false

    init {
        setCapacity(capacity)
        setEnergy(energy)
        setTransfer(transfer)

        for (dir: Direction in Direction.values())
            setConnection(dir, Connection.BOTH)
    }

    fun setPowered(powered: Boolean) {
        this.isPowered = powered
    }

    override fun receive(dir: Direction?, amount: Int, test: Boolean): Int {
        return if (isPowered) super.receive(dir, amount, test)
        else 0
    }

    override fun provide(dir: Direction?, amount: Int, test: Boolean): Int {
        return if (isPowered) super.provide(dir, amount, test)
        else 0
    }
}