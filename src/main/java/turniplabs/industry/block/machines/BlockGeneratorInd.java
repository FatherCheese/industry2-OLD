package turniplabs.industry.block.machines;

import net.minecraft.src.*;
import sunsetsatellite.energyapi.EnergyAPI;
import turniplabs.industry.block.tile.TileEntityGeneratorInd;
import turniplabs.industry.container.ContainerGeneratorInd;
import turniplabs.industry.gui.GuiGeneratorInd;

public class BlockGeneratorInd extends BlockContainerRotatable {
    public BlockGeneratorInd(int i, Material material) {
        super(i, material);
    }

    @Override
    protected TileEntity getBlockEntity() {
        return new TileEntityGeneratorInd();
    }

    @Override
    public void onBlockRemoval(World world, int x, int y, int z) {
        IInventory inventory = ((IInventory) world.getBlockTileEntity(x, y, z));
        label0:
        for (int invSize = 0; invSize < inventory.getSizeInventory(); invSize++) {
            ItemStack itemStack = inventory.getStackInSlot(invSize);
            if (itemStack == null)
                continue;

            float fX = world.rand.nextFloat() * 0.8F + 0.1F;
            float fY = world.rand.nextFloat() * 0.8F + 0.1F;
            float fZ = world.rand.nextFloat() * 0.8F + 0.1F;
            do {
                if (itemStack.stackSize <= 0)
                    continue label0;

                int worldRand = world.rand.nextInt(21) + 10;
                if (worldRand > itemStack.stackSize)
                    worldRand = itemStack.stackSize;
                itemStack.stackSize -= worldRand;

                EntityItem entityItem = new EntityItem(world, (float) x + fX, (float) y + fY, (float) z + fZ, new ItemStack(itemStack.itemID, worldRand, itemStack.getMetadata(), itemStack.tag));
                entityItem.motionX = (float)world.rand.nextGaussian() * 0.05f;
                entityItem.motionY = (float)world.rand.nextGaussian() * 0.05f + 0.2F;
                entityItem.motionZ = (float)world.rand.nextGaussian() * 0.05f;
                world.entityJoinedWorld(entityItem);
            } while (true);
        }
        super.onBlockRemoval(world, x, y, z);
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer entityplayer)
    {
        if (!world.isMultiplayerAndNotHost) {
            TileEntityGeneratorInd tile = (TileEntityGeneratorInd) world.getBlockTileEntity(x, y, z);
            if (tile != null)
                EnergyAPI.displayGui(entityplayer, new GuiGeneratorInd(entityplayer.inventory, tile), new ContainerGeneratorInd(entityplayer.inventory, tile), entityplayer.inventory);
        }
        return true;
    }
}
