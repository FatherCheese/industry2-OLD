package turniplabs.industry.block.tile

import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor
import sunsetsatellite.energyapi.util.Connection
import sunsetsatellite.energyapi.util.Direction
import turniplabs.industry.interfaces.IMachineCondition
import java.util.*
import kotlin.math.min

open class TileEntityEnergyConductorDamageable: TileEntityEnergyConductor(), IMachineCondition {

    /*                             /
    / Energy Ratings:              /
    / 32RC = Low voltage           /
    / 256RC = Medium voltage       /
    / 512RC = High voltage         /
    / 1024RC = Super High voltage */

    val random: Random = Random()

    private val maxMachineHealth: Int = 100
    private val healAmount: Int = 1

    private var machineHealth: Int = maxMachineHealth
    private var hasBeenDamagedInLastTick: Boolean = false

    override val getMachineHealth: Int
        get() = machineHealth

    override fun setMachineHealth(newHealth: Int) {
        this.machineHealth = newHealth
    }

    override fun receive(dir: Direction?, amount: Int, test: Boolean): Int {
        if (canConnect(dir, Connection.INPUT)) {
            val received: Int = min(capacity - energy, min(maxReceive, amount))

            if (amount > maxReceive && random.nextInt(4) == 0) {
                machineHealth -= amount - maxReceive
                hasBeenDamagedInLastTick = true
            }
            if (!test) energy += received
            return received
        }
        return 0
    }

    override fun updateEntity() {
        super.updateEntity()

        // Destroy itself on 0
        if (machineHealth < 1)
            worldObj.createExplosion(null, xCoord.toDouble(), yCoord.toDouble(), zCoord.toDouble(), 0.25f)

        if (machineHealth < maxMachineHealth) {
            // Generate smoke particles
            for (run in 0..random.nextInt(6)) {
                // Get some random coords within the block's boundaries
                val x: Double = xCoord.toDouble() * random.nextDouble()
                val y: Double = xCoord.toDouble() * random.nextDouble()
                val z: Double = xCoord.toDouble() * random.nextDouble()

                worldObj.spawnParticle("smoke", x, y, z, 0.0, 0.0, 0.0)
            }
            // Health itself over time
            if (!hasBeenDamagedInLastTick && random.nextInt(4) == 0) machineHealth += healAmount
            hasBeenDamagedInLastTick = false
        }
    }
}