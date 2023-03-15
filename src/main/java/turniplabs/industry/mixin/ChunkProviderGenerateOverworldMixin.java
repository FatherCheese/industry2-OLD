package turniplabs.industry.mixin;

import net.minecraft.src.ChunkProviderGenerateOverworld;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.industry.ModIndustry2;

import java.util.Random;

@Mixin(value = ChunkProviderGenerateOverworld.class, remap = false)
public class ChunkProviderGenerateOverworldMixin {

    @Shadow protected int heightModifier;

    @Shadow protected Random rand;

    @Shadow protected int terrainMaxHeight;

    @Shadow protected World worldObj;

    @Inject(method = "populate", at = @At("HEAD"))
    private void industry_populateOres(IChunkProvider ichunkprovider, int chunkX, int chunkZ, CallbackInfo ci) {
        int x1 = chunkX * 16;
        int z1 = chunkZ * 16;

        for (int copper = 0; copper < 20 * heightModifier; ++copper) {
            int copperX = x1 + rand.nextInt(16);
            int copperY = rand.nextInt(terrainMaxHeight) / 2;
            int copperZ = z1 + rand.nextInt(16);

            new WorldGenMinable(ModIndustry2.ORE_COPPER_STONE.blockID, 8,true).generate(worldObj, rand, copperX, copperY, copperZ);
        }

        for (int tin = 0; tin < 16 * heightModifier; ++tin) {
            int tinX = x1 + rand.nextInt(16);
            int tinY = rand.nextInt(terrainMaxHeight) / 2;
            int tinZ = z1 + rand.nextInt(16);

            new WorldGenMinable(ModIndustry2.ORE_TIN_STONE.blockID, 6, true).generate(worldObj, rand, tinX, tinY, tinZ);
        }
    }
}
