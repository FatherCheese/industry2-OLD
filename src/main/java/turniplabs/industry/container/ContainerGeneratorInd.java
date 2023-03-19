package turniplabs.industry.container;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.IInventory;
import net.minecraft.src.Slot;
import sunsetsatellite.energyapi.template.containers.ContainerEnergy;
import turniplabs.industry.block.tile.TileEntityGeneratorInd;

public class ContainerGeneratorInd extends ContainerEnergy {
    private int maxBurnTime = 0;
    private int currentBurnTime = 0;

    public ContainerGeneratorInd(IInventory iInventory, TileEntityGeneratorInd tileEntity) {
        tile = tileEntity;
        addSlot(new Slot(tileEntity, 0, 80, 17));
        addSlot(new Slot(tileEntity, 1, 80, 53));

        for (int ySlot = 0; ySlot < 3; ySlot++)
            for (int xSlot = 0; xSlot < 9; xSlot++)
                addSlot(new Slot(iInventory, xSlot + ySlot * 9 + 9, 8 + xSlot * 18, 84 + ySlot * 18));

        for (int hotbar = 0; hotbar < 9; hotbar++)
            addSlot(new Slot(iInventory, hotbar, 8 + hotbar * 18, 142));
    }

    @Override
    public void updateInventory() {
        super.updateInventory();

        for (ICrafting crafter : crafters) {
            if (this.currentBurnTime != ((TileEntityGeneratorInd) tile).currentBurnTime)
                crafter.updateCraftingInventoryInfo(this, 1, ((TileEntityGeneratorInd) tile).currentBurnTime);

            if (maxBurnTime != ((TileEntityGeneratorInd) tile).maxBurnTime)
                crafter.updateCraftingInventoryInfo(this, 2, ((TileEntityGeneratorInd) tile).maxBurnTime);
        }

        this.currentBurnTime = ((TileEntityGeneratorInd)tile).currentBurnTime;
        this.maxBurnTime = ((TileEntityGeneratorInd)tile).maxBurnTime;
    }

    @Override
    public void updateClientProgressBar(int id, int value) {
        if (id == 1)
            ((TileEntityGeneratorInd)tile).currentBurnTime = value;

        if (id == 2)
            ((TileEntityGeneratorInd)tile).maxBurnTime = value;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void quickMoveItems(int i, EntityPlayer entityPlayer, boolean bl, boolean bl2) {
    }
}
