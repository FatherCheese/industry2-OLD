package turniplabs.industry.block.tile;

import sunsetsatellite.energyapi.impl.TileEntityEnergyConductor;
import sunsetsatellite.energyapi.util.Connection;
import sunsetsatellite.energyapi.util.Direction;

public class TileEntityCable extends TileEntityEnergyConductor {

    public TileEntityCable(int capacity, int energy, int transfer) {
        setCapacity(capacity);
        setEnergy(energy);
        setTransfer(transfer);
        for (Direction dir : Direction.values()) {
            setConnection(dir, Connection.BOTH);
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
    }
}
