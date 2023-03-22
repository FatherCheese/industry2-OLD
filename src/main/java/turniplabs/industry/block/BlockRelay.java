package turniplabs.industry.block;

import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import turniplabs.industry.block.tile.TileEntityCable;
import turniplabs.industry.block.tile.TileEntityRelay;

import java.util.Random;

public class BlockRelay extends BlockCable{
    public BlockRelay(int i, Material material, int capacity, int energy, int transfer) {
        super(i, material, capacity, energy, transfer, 0);
    }

    public void onBlockAdded(World world, int i, int j, int k) {
        super.onBlockAdded(world, i, j, k);
        world.scheduleBlockUpdate(i, j, k, this.blockID, this.tickRate());
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
        world.scheduleBlockUpdate(i, j, k, this.blockID, this.tickRate());
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        boolean isPoweredByBlock = world.isBlockGettingPowered(i, j, k) || world.isBlockIndirectlyGettingPowered(i, j, k);
        TileEntityRelay tile = (TileEntityRelay) world.getBlockTileEntity(i, j, k);
        tile.setPowered(isPoweredByBlock);
    }

    @Override
    protected TileEntity getBlockEntity() {
        return new TileEntityRelay(capacity, energy, transfer);
    }

    @Override
    public int getRenderType() {
        return 0;
    }
}
