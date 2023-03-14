package turniplabs.industry.item;

import net.minecraft.src.ItemStack;
import sunsetsatellite.energyapi.EnergyAPI;
import sunsetsatellite.energyapi.template.items.ItemBattery;

public class ItemBatteryRedstone extends ItemIndBattery {

    public ItemBatteryRedstone(int id) {
        super(id, 1024, 16, 16, "battery_redstone_0.png", "battery_redstone_1.png", "battery_redstone_2.png", "battery_redstone_3.png", "battery_redstone_4.png");
    }
}
