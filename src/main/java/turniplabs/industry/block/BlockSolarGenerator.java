package turniplabs.industry.block;

import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import sunsetsatellite.energyapi.template.blocks.BlockBatteryBox;
import turniplabs.industry.entity.TileEntitySolarGenerator;

public class BlockSolarGenerator extends BlockBatteryBox {
    public BlockSolarGenerator(int i, Material material) {
        super(i, material);
    }

    @Override
    protected TileEntity getBlockEntity() {
        return new TileEntitySolarGenerator();
    }

}
