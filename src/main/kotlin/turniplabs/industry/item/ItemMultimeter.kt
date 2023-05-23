package turniplabs.industry.item

import net.minecraft.client.Minecraft
import net.minecraft.src.*
import net.minecraft.src.command.ChatColor
import sunsetsatellite.energyapi.api.IEnergy
import sunsetsatellite.energyapi.util.ICustomDescription
import turniplabs.industry.interfaces.IMachineCondition

class ItemMultimeter(i: Int) : Item(i), ICustomDescription {

    override fun getDescription(stack: ItemStack?): String {
        val translator: StringTranslate = StringTranslate.getInstance()
        val savedReadingRC: String = stack?.tag?.getInteger("savedReadingRC").toString()
        val savedReadingHealth: String = stack?.tag?.getInteger("savedReadingHealth").toString()

        return ChatColor.yellow.also { translator.translateKey("message.industry.multimeter.saved").replace("[0]", savedReadingRC).replace("[1]", savedReadingHealth) }.toString()
    }

    override fun onItemUse(
        itemstack: ItemStack?,
        entityPlayer: EntityPlayer?,
        world: World?,
        x: Int,
        y: Int,
        z: Int,
        l: Int,
        heightPlaced: Double
    ): Boolean {
        val conductor: TileEntity? = world?.getBlockTileEntity(x, y, z)

        if (entityPlayer?.isSneaking == true && conductor is IMachineCondition) {
            val reading: Int = (conductor as IMachineCondition).getMachineHealth
            val translator: StringTranslate = StringTranslate.getInstance()

            Minecraft.getMinecraft().commandHandler.sendMessageToPlayer(entityPlayer, translator.translateKey("message.industry.multimeter.health").replace("[]", "$reading"))
            itemstack?.tag?.setInteger("savedReadingHealth", reading)
            return true
        }

        if (conductor is IEnergy) {
            val reading: Int = (conductor as IEnergy).energy
            val translator: StringTranslate = StringTranslate.getInstance()

            Minecraft.getMinecraft().commandHandler.sendMessageToPlayer(entityPlayer, translator.translateKey("message.industry.multimeter.energy").replace("[]", "$reading"))
            itemstack?.tag?.setInteger("savedReadingRC", reading)
            return true
        }

        return false
    }
}