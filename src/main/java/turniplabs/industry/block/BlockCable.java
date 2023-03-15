package turniplabs.industry.block;

import net.minecraft.src.*;
import net.minecraft.src.command.ChatColor;
import sunsetsatellite.energyapi.util.Config;
import sunsetsatellite.energyapi.util.ICustomDescription;
import turniplabs.industry.block.tileentity.TileEntityCable;

public class BlockCable extends BlockContainer implements ICustomDescription {
    int capacity;
    int energy;
    int transfer;

    public BlockCable(int i, Material material, int capacity, int energy, int transfer) {
        super(i, material);
        this.capacity = capacity;
        this.energy = energy;
        this.transfer = transfer;
    }



    @Override
    protected TileEntity getBlockEntity() {
        return new TileEntityCable(capacity, energy, transfer);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
        return false;
    }

    @Override
    public int getRenderType() {
        return 31;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public String getDescription(ItemStack stack) {
        return ChatColor.white + "Max Transfer: " + ChatColor.lightGray + transfer + " " + Config.getFromConfig("energySuffix", "E") + " IN " + "| " + transfer + " " + Config.getFromConfig("energySuffix", "E") + " OUT";
    }
}
