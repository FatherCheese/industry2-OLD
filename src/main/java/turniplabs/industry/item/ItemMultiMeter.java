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
            int reading = ((IEnergy) conductor).getEnergy();
            StringTranslate translator = StringTranslate.getInstance();

            if (entityplayer.isSneaking()) {
                int savedReading = itemstack.tag.getInteger("savedReading");

                // sends the message to the player. split this one into multiple lines to improve readability.
                Minecraft.getMinecraft().commandHandler.sendMessageToPlayer(
                    entityplayer, translator.translateKey("message.industry.multimeter.saved")
                    .replace("[0]", String.valueOf(savedReading))
                    .replace("[1]", String.valueOf(Math.abs(savedReading - reading)))
                    .replace("[2]", String.valueOf(reading))
                );

                itemstack.tag.setInteger("savedReading", reading);
                return true;
            }

            Minecraft.getMinecraft().commandHandler.sendMessageToPlayer(entityplayer, translator.translateKey("message.industry.multimeter").replace("[]", String.valueOf(reading)));
            itemstack.tag.setInteger("savedReading", reading);
            return true;
        }

        return false;
    }
}
