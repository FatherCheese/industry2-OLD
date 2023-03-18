package turniplabs.industry.block;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import sunsetsatellite.energyapi.EnergyAPI;
import sunsetsatellite.energyapi.template.blocks.BlockBatteryBox;
import turniplabs.industry.block.tile.TileEntitySolarGenerator;
import turniplabs.industry.container.ContainerSolarGenerator;
import turniplabs.industry.gui.GuiSolarGenerator;

public class BlockSolarGenerator extends BlockBatteryBox {
    public BlockSolarGenerator(int i, Material material) {
        super(i, material);
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer entityplayer)
    {
        if (!world.isMultiplayerAndNotHost) {
            TileEntitySolarGenerator tile = (TileEntitySolarGenerator) world.getBlockTileEntity(x, y, z);
            if (tile != null) {
                EnergyAPI.displayGui(entityplayer, new GuiSolarGenerator(entityplayer.inventory, tile), new ContainerSolarGenerator(entityplayer.inventory, tile), tile);
            }
        }
        return true;
    }

    @Override
    protected TileEntity getBlockEntity() {
        return new TileEntitySolarGenerator();
    }

}
