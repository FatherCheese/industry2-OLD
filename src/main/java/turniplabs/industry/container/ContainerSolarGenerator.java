package turniplabs.industry.container;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Slot;
import sunsetsatellite.energyapi.template.containers.ContainerEnergy;
import turniplabs.industry.block.tile.TileEntitySolarGenerator;

public class ContainerSolarGenerator extends ContainerEnergy {
    public ContainerSolarGenerator(IInventory iInventory, TileEntitySolarGenerator solarGenerator) {
        tile = solarGenerator;
        addSlot(new Slot(solarGenerator, 0, 80, 35));

        for (int ySlot = 0; ySlot < 3; ySlot++)
            for (int xSlot = 0; xSlot < 9; xSlot++)
                addSlot(new Slot(iInventory, xSlot + ySlot * 9 + 9, 8 + xSlot * 18, 84 + ySlot * 18));

        for (int hotbar = 0; hotbar < 9; hotbar++)
            addSlot(new Slot(iInventory, hotbar, 8 + hotbar * 18, 142));
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void quickMoveItems(int i, EntityPlayer entityPlayer, boolean bl, boolean bl2) {

    }
}
