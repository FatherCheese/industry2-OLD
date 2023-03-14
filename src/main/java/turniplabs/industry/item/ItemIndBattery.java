package turniplabs.industry.item;

import net.minecraft.src.ItemStack;
import sunsetsatellite.energyapi.EnergyAPI;
import sunsetsatellite.energyapi.template.items.ItemBattery;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.industry.ModIndustry2;

public class ItemIndBattery extends ItemBattery {
    public int[][] textureCoordinates = new int[5][];

    public ItemIndBattery(int id, int capacity, int provide, int receive, String fullTexture, String midFullTexture, String halfTexture,  String midHalfTexture, String emptyTexture) {
        super(id);
        baseCapacity = capacity;
        baseProvide = provide;
        baseReceive = receive;
        String modID = ModIndustry2.MOD_ID;
        textureCoordinates[0] = TextureHelper.registerItemTexture(modID, emptyTexture);
        textureCoordinates[1] = TextureHelper.registerItemTexture(modID, midHalfTexture);
        textureCoordinates[2] = TextureHelper.registerItemTexture(modID, halfTexture);
        textureCoordinates[3] = TextureHelper.registerItemTexture(modID, midFullTexture);
        textureCoordinates[4] = TextureHelper.registerItemTexture(modID, fullTexture);
    }

    @Override
    public int getIconIndex(ItemStack itemStack) {
        int mapped = (int) EnergyAPI.map((float) getEnergy(itemStack) / (float) getCapacity(itemStack),0,1,0,4);
        setIconCoord(textureCoordinates[mapped][0], textureCoordinates[mapped][1]);
        return iconIndex;
    }
}
