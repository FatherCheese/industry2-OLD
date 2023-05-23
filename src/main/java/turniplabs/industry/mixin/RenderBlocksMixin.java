package turniplabs.industry.mixin;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sunsetsatellite.energyapi.util.RenderWire;
import turniplabs.industry.block.IndustryBlocks;

@Mixin (value = RenderBlocks.class, remap = false)
public class RenderBlocksMixin {

    @Shadow
    private IBlockAccess blockAccess;

    @Inject(method = "renderBlockByRenderType", at = @At("TAIL"), cancellable = true)
    private void industry_renderCables(Block block, int i, int j, int k, CallbackInfoReturnable<Boolean> cir) {
        if (block == IndustryBlocks.INSTANCE.getCOPPER_CABLE() || block == IndustryBlocks.INSTANCE.getTIN_CABLE())
            cir.setReturnValue(RenderWire.render((RenderBlocks) ((Object)this),this.blockAccess, i, j, k, block, 0));
    }
}
