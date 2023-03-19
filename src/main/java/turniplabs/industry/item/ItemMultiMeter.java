package turniplabs.industry.item;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.command.ChatColor;
import sunsetsatellite.energyapi.api.IEnergy;
import sunsetsatellite.energyapi.util.ICustomDescription;
import turniplabs.industry.interfaces.IMachineCondition;

public class ItemMultiMeter extends Item implements ICustomDescription {

    public ItemMultiMeter(int i) {
        super(i);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, double heightPlaced) {
        TileEntity conductor = world.getBlockTileEntity(i, j, k);

        if (entityplayer.isSneaking() && conductor instanceof IMachineCondition) {
            int reading = ((IMachineCondition) conductor).getMachineHealth();
            StringTranslate translator = StringTranslate.getInstance();

            Minecraft.getMinecraft().commandHandler.sendMessageToPlayer(entityplayer, translator.translateKey("message.industry.multimeter.health").replace("[]", String.valueOf(reading)));
            itemstack.tag.setInteger("savedReadingHealth", reading);
            return true;
        }

        if (conductor instanceof IEnergy) {
            int reading = ((IEnergy) conductor).getEnergy();
            StringTranslate translator = StringTranslate.getInstance();

            Minecraft.getMinecraft().commandHandler.sendMessageToPlayer(entityplayer, translator.translateKey("message.industry.multimeter.energy").replace("[]", String.valueOf(reading)));
            itemstack.tag.setInteger("savedReadingRC", reading);
            return true;
        }

        return false;
    }

    @Override
    public String getDescription(ItemStack stack) {
        StringTranslate translator = StringTranslate.getInstance();
        String savedReadingRC = String.valueOf(stack.tag.getInteger("savedReadingRC"));
        String savedReadingHealth = String.valueOf(stack.tag.getInteger("savedReadingHealth"));

        return ChatColor.yellow + translator.translateKey("message.industry.multimeter.saved").replace("[0]", savedReadingRC).replace("[1]", savedReadingHealth);
    }
}
