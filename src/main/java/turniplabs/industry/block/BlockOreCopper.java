package turniplabs.industry.block;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import turniplabs.industry.ModIndustry2;

import java.util.Random;

public class BlockOreCopper extends Block {

    public BlockOreCopper(int id, Material material) {
        super(id, material);
    }

    @Override
    public int idDropped(int i, Random random) {
        return ModIndustry2.RAW_COPPER.itemID;
    }
}
