package turniplabs.industry.block.tile;

import sunsetsatellite.energyapi.util.Connection;
import sunsetsatellite.energyapi.util.Direction;

public class TileEntityRelay extends TileEntityEnergyConductorDamageable{

    boolean isPowered = false;

    public TileEntityRelay(int capacity, int energy, int transfer) {
        setCapacity(capacity);
        setEnergy(energy);
        setTransfer(transfer);

        for (Direction dir : Direction.values()) {
            setConnection(dir, Connection.BOTH);
        }
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    @Override
    public int receive(Direction dir, int amount, boolean test) {
        if (isPowered) return super.receive(dir, amount, test);
        return 0;
    }

    @Override
    public int provide(Direction dir, int amount, boolean test) {
        if (isPowered) return super.provide(dir, amount, test);
        return 0;
    }
}
