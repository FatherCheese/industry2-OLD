package turniplabs.industry.mixin;

import net.minecraft.src.BiomeGenRainforest;
import net.minecraft.src.WorldGenTreeShapeDefault;
import net.minecraft.src.WorldGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import turniplabs.industry.block.IndustryBlocks;

import java.util.Random;

@Mixin(value = BiomeGenRainforest.class, remap = false)
public class BiomeGenRainforestMixin {

    @Inject(method = "getRandomWorldGenForTrees", at = @At("TAIL"), cancellable = true)
    private void industry_addRubberTrees(Random random, CallbackInfoReturnable<WorldGenerator> cir) {
        if (random.nextInt(15) == 0) cir.setReturnValue(new WorldGenTreeShapeDefault(IndustryBlocks.INSTANCE.getRUBBER_LEAVES().blockID, IndustryBlocks.INSTANCE.getRUBBER_LOG().blockID,8));
    }
}
