package turniplabs.industry.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import turniplabs.industry.ModIndustry2;

public class ItemTreeTap extends Item {

    public ItemTreeTap(int i) {
        super(i);
        setMaxDamage(64);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int l, double heightPlaced) {
        int blockLook = world.getBlockId(x, y, z);
        int randAmount = itemRand.nextInt(4 - 1) + 1;

        if (blockLook != 0 && blockLook == ModIndustry2.RUBBER_LOG.blockID) {
            if (!world.isMultiplayerAndNotHost) {
                player.inventory.addItemStackToInventory(new ItemStack(ModIndustry2.TREE_RESIN, randAmount));
                itemstack.damageItem(this.itemID, player);
                world.playSoundAtEntity(player, "random.wood click", 1.0f, 1.0f);
            }
        }
        return false;
    }
}
