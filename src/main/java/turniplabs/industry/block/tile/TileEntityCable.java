package turniplabs.industry.block.tile;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.helper.DamageType;
import sunsetsatellite.energyapi.util.Connection;
import sunsetsatellite.energyapi.util.Direction;

import java.util.List;

public class TileEntityCable extends TileEntityEnergyConductorDamageable {
    int dangerLevel;

    public TileEntityCable(int capacity, int energy, int transfer, int dangerLevel) {
        setCapacity(capacity);
        setEnergy(energy);
        setTransfer(transfer);
        this.dangerLevel = dangerLevel;

        for (Direction dir : Direction.values()) {
            setConnection(dir, Connection.BOTH);
        }
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    @Override
    public void updateEntity() {

        if (dangerLevel > 0 && this.energy >= (dangerLevel * 10)) {
            List<Entity> list = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox((double) this.xCoord - 0.55, (double) this.yCoord - 0.55, (double) this.zCoord - 0.55, (double) this.xCoord + 1.55, (double) this.yCoord + 1.55, (double) this.zCoord + 1.55));
            for (Entity entity : list) {
                if (entity instanceof EntityLiving) {
                    int prevHealth = ((EntityLiving) entity).health;
                    entity.attackEntityFrom(null, dangerLevel, DamageType.BLAST);

                    // only generate particles if player actually took damage.
                    if (prevHealth != ((EntityLiving) entity).health) {
                        for (int run = 0; run < random.nextInt(11); run++) {
                            // get some random coordinate within the block's boundaries
                            double x = random.nextDouble() + entity.posX;
                            double y = -1 + random.nextDouble() + random.nextInt(2) + entity.posY;
                            double z = random.nextDouble() + entity.posZ;

                            worldObj.spawnParticle("flame", x, y, z, 0.0, 0.0, 0.0);
                        }
                        this.energy -= (dangerLevel * 10);
                    }
                }
            }
        }

        super.updateEntity();
    }
}
