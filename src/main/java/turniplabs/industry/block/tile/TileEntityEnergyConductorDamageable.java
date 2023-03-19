package turniplabs.industry.block.tile;

import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;
import sunsetsatellite.energyapi.util.Connection;
import sunsetsatellite.energyapi.util.Direction;
import turniplabs.industry.interfaces.IMachineCondition;

import java.util.Random;

public abstract class TileEntityEnergyConductorDamageable extends TileEntityEnergyConductor implements IMachineCondition {

    /*                             /
    /  Energy Ratings:             /
    /  8RC = Low voltage           /
    /  32RC = Medium voltage       /
    /  128RC = High voltage        /
    /  256RC = Super High voltage */

    protected int maxMachineHealth = 100;

    protected int machineHealth = maxMachineHealth;
    protected boolean hasBeenDamagedInLastTick;

    Random random = new Random();

    @Override
    public int getMachineHealth() {
        return machineHealth;
    }

    @Override
    public void setMachineHealth(int machineHealth) {
        this.machineHealth = machineHealth;
    }

    @Override
    public int receive(Direction dir, int amount, boolean test) {
        if(canConnect(dir, Connection.INPUT)){
            int received = Math.min(this.capacity - this.energy, Math.min(this.maxReceive, amount));

            if (amount > maxReceive && random.nextInt(4) == 0) {
                machineHealth -= amount - maxReceive;
                hasBeenDamagedInLastTick = true;
            }

            if (!test) energy += received;
            return received;
        }
        return 0;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        // destroy itself on zero.
        if (machineHealth < 1) worldObj.createExplosion(null, xCoord, yCoord, zCoord, 0.25F);

        if (machineHealth < maxMachineHealth) {
            // generate smoke particles
            for (int run = 0; run < random.nextInt(6); run++) {
                // get some random coordinate within the block's boundaries
                double x = xCoord + random.nextDouble();
                double z = zCoord + random.nextDouble();
                double y = yCoord + random.nextDouble();

                worldObj.spawnParticle("smoke", x, y, z, 0.0, 0.0, 0.0);
            }

            // heal itself overtime.
            if (!hasBeenDamagedInLastTick && random.nextInt(4) == 0) machineHealth += 1;
            hasBeenDamagedInLastTick = false;
        }
    }

}
