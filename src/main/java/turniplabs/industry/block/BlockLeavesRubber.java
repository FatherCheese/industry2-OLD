package turniplabs.industry.block;

import net.minecraft.src.*;
import turniplabs.industry.ModIndustry2;

import java.util.Random;

public class BlockLeavesRubber extends BlockLeavesBase {

    public BlockLeavesRubber(int i, Material material, boolean flag) {
        super(i, material, flag);
        setLightOpacity(1);
    }

    @Override
    public int getRenderColor(int i) {
        return ColorProperties.fRGB2iRGB(ColorProperties.pine.inventoryR, ColorProperties.pine.inventoryG, ColorProperties.pine.inventoryB);
    }

    @Override
    public int colorMultiplier(World world, IBlockAccess iblockaccess, int i, int j, int k) {
        int baseFoliageColor = ColorProperties.fRGB2iRGB(ColorProperties.pine.inventoryR, ColorProperties.pine.inventoryG, ColorProperties.pine.inventoryB);
        if (world != null) {
            double localTemperature = iblockaccess.getWorldChunkManager().getTemperature(i, k);
            double localHumidity = iblockaccess.getWorldChunkManager().getHumidity(i, k);
            Season season = world.getCurrentSeason();
            if (season != null) {
                float progress = world.getSeasonProgress();
                baseFoliageColor = ColorizerFoliage.getSeasonalColor(season, progress, localTemperature, localHumidity, ColorProperties.pine);
            }
        }
        return baseFoliageColor;
    }

    @Override
    public int quantityDropped(int metadata, Random random) {
        return random.nextInt(30) != 0 ? 0 : 1;
    }

    @Override
    public int idDropped(int i, Random random) {
        return ModIndustry2.RUBBER_SAPLING.blockID;
    }
}
