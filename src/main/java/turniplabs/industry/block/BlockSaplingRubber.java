package turniplabs.industry.block;

import net.minecraft.src.*;
import turniplabs.industry.ModIndustry2;

import java.util.Random;

public class BlockSaplingRubber extends BlockSaplingBase {

    public BlockSaplingRubber(int i) {
        super(i);
    }

    @Override
    public void growTree(World world, int i, int j, int k, Random random) {
        WorldGenTreeShapeDefault obj = null;
        world.setBlock(i, j, k, 0);
        obj = new WorldGenTreeShapeDefault(ModIndustry2.RUBBER_LEAVES.blockID, ModIndustry2.RUBBER_LOG.blockID, 6);
        if (!((WorldGenerator) obj).generate(world, random, i, j, k)) {
            world.setBlock(i, j, k, this.blockID);
        }
    }
}
