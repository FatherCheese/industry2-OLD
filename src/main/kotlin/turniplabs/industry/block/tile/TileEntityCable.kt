package turniplabs.industry.block.tile

import net.minecraft.src.AxisAlignedBB
import net.minecraft.src.Entity
import net.minecraft.src.EntityLiving
import net.minecraft.src.helper.DamageType
import sunsetsatellite.energyapi.util.Connection
import sunsetsatellite.energyapi.util.Direction


class TileEntityCable(capacity: Int, energy: Int, transfer: Int, dangerLevel: Int): TileEntityEnergyConductorDamageable() {
    private var dangerLevel: Int

    init {
        this.dangerLevel = dangerLevel
        for (dir: Direction in Direction.values()) {
            setConnection(dir, Connection.BOTH)
        }
    }

    fun setDangerLevel(dangerLevel: Int) {
        this.dangerLevel = dangerLevel
    }

    override fun updateEntity() {
        if (dangerLevel > 0 && energy >= (dangerLevel * 10)) {
            val list = worldObj.getEntitiesWithinAABB(
                Entity::class.java,
                AxisAlignedBB.getBoundingBox(
                    xCoord.toDouble() - 0.55,
                    yCoord.toDouble() - 0.55,
                    zCoord.toDouble() - 0.55,
                    xCoord.toDouble() + 1.55,
                    yCoord.toDouble() + 1.55,
                    zCoord.toDouble() + 1.55
                )
            )

            for (entity: Entity in list) {
                if (entity is EntityLiving) {
                    val prevHealth: Int = entity.health
                    entity.attackEntityFrom(null, dangerLevel, DamageType.BLAST)

                    // Only generate particles if the entity actually took damage
                    if (prevHealth != entity.health) {
                        for (run in 0..random.nextInt(11)) {
                            // Get some random particles within the block's boundaries
                            val x: Double = random.nextDouble() + entity.posX
                            val y: Double = -1 + random.nextDouble() + random.nextInt(2) + entity.posY
                            val z: Double = random.nextDouble() + entity.posZ

                            worldObj.spawnParticle("flame", x, y, z, 0.0, 0.0, 0.0)
                        }
                        this.energy -= dangerLevel * 10
                    }
                }
            }
        }
        super.updateEntity()
    }
}