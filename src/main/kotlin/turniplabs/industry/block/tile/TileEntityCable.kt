package turniplabs.industry.block.tile

import net.minecraft.src.AxisAlignedBB
import net.minecraft.src.Entity
import net.minecraft.src.EntityLiving
import net.minecraft.src.helper.DamageType
import sunsetsatellite.energyapi.util.Connection
import sunsetsatellite.energyapi.util.Direction

// This class was also auto-generated to Kotlin, check up on this later too!!
class TileEntityCable(capacity: Int, energy: Int, transfer: Int, dangerLevel: Int) :
    TileEntityEnergyConductorDamageable() {
    private var dangerLevel: Int

    init {
        setCapacity(capacity)
        setEnergy(energy)
        setTransfer(transfer)
        this.dangerLevel = dangerLevel
        for (dir in Direction.values()) {
            setConnection(dir, Connection.BOTH)
        }
    }

    fun setDangerLevel(dangerLevel: Int) {
        this.dangerLevel = dangerLevel
    }

    override fun updateEntity() {
        if (dangerLevel > 0 && energy >= dangerLevel * 10) {
            val list = worldObj.getEntitiesWithinAABB(
                Entity::class.java, AxisAlignedBB.getBoundingBox(
                    xCoord.toDouble() - 0.55,
                    yCoord.toDouble() - 0.55,
                    zCoord.toDouble() - 0.55,
                    xCoord.toDouble() + 1.55,
                    yCoord.toDouble() + 1.55,
                    zCoord.toDouble() + 1.55
                )
            )
            for (entity in list) {
                if (entity is EntityLiving) {
                    val prevHealth = entity.health
                    entity.attackEntityFrom(null, dangerLevel, DamageType.BLAST)

                    // only generate particles if player actually took damage.
                    if (prevHealth != entity.health) {
                        for (run in 0 until random.nextInt(11)) {
                            // get some random coordinate within the block's boundaries
                            val x = random.nextDouble() + entity.posX
                            val y = -1 + random.nextDouble() + random.nextInt(2) + entity.posY
                            val z = random.nextDouble() + entity.posZ
                            worldObj.spawnParticle("flame", x, y, z, 0.0, 0.0, 0.0)
                        }
                        energy -= dangerLevel * 10
                    }
                }
            }
        }
        super.updateEntity()
    }
}