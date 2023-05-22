package turniplabs.industry.item

import net.minecraft.src.Item
import sunsetsatellite.energyapi.template.items.ItemBattery
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.industry.Industry2

object IndustryItems {

    private var itemID = 1199

    private fun nextItemID(): Int {
        return itemID++
    }

        // COPPER
        val RAW_COPPER: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "raw.copper", "raw_copper.png")
        val DUST_COPPER: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "dust.copper", "dust_copper.png")
        val INGOT_COPPER: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "ingot.copper", "ingot_copper.png")
        val PLATE_COPPER: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "plate.copper", "plate_copper.png")

        // TIN
        val RAW_TIN: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "raw.tin", "raw_tin.png")
        val DUST_TIN: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "dust.tin", "dust_tin.png")
        val INGOT_TIN: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "ingot.tin", "ingot_tin.png")
        val PLATE_TIN: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "plate.tin", "plate_tin.png")

        // BRONZE
        val DUST_BRONZE: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "dust.bronze", "dust_bronze.png")
        val INGOT_BRONZE: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "ingot.bronze", "ingot_bronze.png")
        val PLATE_BRONZE: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "plate.bronze", "plate_bronze.png")

        // VANILLA METALS
        val DUST_IRON: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "dust.iron", "dust_iron.png")
        val DUST_GOLD: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "dust.gold", "dust_gold.png")
        val PLATE_IRON: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "plate.iron", "plate_iron.png")
        val PLATE_GOLD: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "plate.gold", "plate_gold.png")
        val PLATE_STEEL: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "plate.steel", "plate_steel.png")

        // MISCELLANEOUS
        val TREE_RESIN: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "resin", "tree_resin.png")
        val INGOT_RUBBER: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "ingot.rubber", "ingot_rubber.png")

        // TOOLS
        val TOOL_TAP: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "tool.tap", "tree_tap.png")
        val TOOL_MULTIMETER: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "tool.multimeter", "tool_multimeter.png")
        val TOOL_WRENCH: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "tool.wrench", "tool_wrench.png")

        // MACHINES
        val BATTERY_REDSTONE: Item = ItemHelper.createItem(Industry2.MODID, ItemBattery(nextItemID()), "battery.redstone", "battery_redstone_0.png")

        val CIRCUIT_BASIC: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "circuit.basic", "circuit_basic.png")
        val CIRCUIT_ADVANCED: Item = ItemHelper.createItem(Industry2.MODID, Item(nextItemID()), "circuit.advanced", "circuit_advanced.png")
}