package turniplabs.industry.item;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import sunsetsatellite.energyapi.api.IEnergy;

public class ItemMultiMeter extends Item {

    public ItemMultiMeter(int i) {
        super(i);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, double heightPlaced) {
        TileEntity conductor = world.getBlockTileEntity(i, j, k);

        if (conductor instanceof IEnergy) {
            StringTranslate translator = StringTranslate.getInstance();
            Minecraft.getMinecraft().commandHandler.sendMessageToPlayer(entityplayer, translator.translateKey("message.multimeter").replace("[]", String.valueOf(((IEnergy) conductor).getEnergy())));
            return true;
        }

        return false;
    }
}
