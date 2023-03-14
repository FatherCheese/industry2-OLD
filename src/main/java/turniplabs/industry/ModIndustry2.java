package turniplabs.industry;

import net.fabricmc.api.ModInitializer;
import net.minecraft.src.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sunsetsatellite.energyapi.EnergyAPI;
import turniplabs.halplibe.helper.BlockHelper;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeHelper;
import turniplabs.halplibe.mixin.accessors.CraftingManagerAccessor;
import turniplabs.industry.block.BlockOreCopper;
import turniplabs.industry.block.BlockOreTin;
import turniplabs.industry.item.ItemBatteryRedstone;
import turniplabs.industry.item.ItemIndBattery;


public class ModIndustry2 implements ModInitializer {
    public static final String MOD_ID = "industry";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static int itemID = 1199;
    private static int blockID = 1199;

    private static int nextItem() {
        return itemID++;
    }

    private static int nextBlock() {
        return blockID++;
    }

    // Copper //
    public static final Block ORE_COPPER_STONE = BlockHelper.createBlock(MOD_ID, new BlockOreCopper(nextBlock(), Material.rock), "ore.copper.stone", "ore_copper_stone.png", Block.soundStoneFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block ORE_COPPER_BASALT = BlockHelper.createBlock(MOD_ID, new BlockOreCopper(nextBlock(), Material.rock), "ore.copper.basalt", "ore_copper_basalt.png", Block.soundStoneFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block ORE_COPPER_LIMESTONE = BlockHelper.createBlock(MOD_ID, new BlockOreCopper(nextBlock(), Material.rock), "ore.copper.limestone", "ore_copper_limestone.png", Block.soundStoneFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block ORE_COPPER_GRANITE = BlockHelper.createBlock(MOD_ID, new BlockOreCopper(nextBlock(), Material.rock), "ore.copper.granite", "ore_copper_granite.png", Block.soundStoneFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block BLOCK_OF_COPPER = BlockHelper.createBlock(MOD_ID, new Block(nextBlock(), Material.iron), "block.copper", "block_copper_top.png", "block_copper_bottom.png", "block_copper_sides.png", Block.soundMetalFootstep, 5.0f, 6.0f, 0.0f);

    public static final Item RAW_COPPER = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "raw.copper", "raw_copper.png");
    public static final Item DUST_COPPER = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "dust.copper", "dust_copper.png");
    public static final Item INGOT_COPPER = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "ingot.copper", "ingot_copper.png");

    // Tin //
    public static final Block ORE_TIN_STONE = BlockHelper.createBlock(MOD_ID, new BlockOreTin(nextBlock(), Material.rock), "ore.tin.stone", "ore_tin_stone.png", Block.soundStoneFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block ORE_TIN_BASALT = BlockHelper.createBlock(MOD_ID, new BlockOreTin(nextBlock(), Material.rock), "ore.tin.basalt", "ore_tin_basalt.png", Block.soundStoneFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block ORE_TIN_LIMESTONE = BlockHelper.createBlock(MOD_ID, new BlockOreTin(nextBlock(), Material.rock), "ore.tin.limestone", "ore_tin_limestone.png", Block.soundStoneFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block ORE_TIN_GRANITE = BlockHelper.createBlock(MOD_ID, new BlockOreTin(nextBlock(), Material.rock), "ore.tin.granite", "ore_tin_granite.png", Block.soundStoneFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block BLOCK_OF_TIN = BlockHelper.createBlock(MOD_ID, new Block(nextBlock(), Material.iron), "block.tin", "block_tin_top.png", "block_tin_bottom.png", "block_tin_sides.png", Block.soundMetalFootstep, 5.0f, 6.0f, 0.0f);

    public static final Item RAW_TIN = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "raw.tin", "raw_tin.png");
    public static final Item DUST_TIN = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "dust.tin", "dust_tin.png");
    public static final Item INGOT_TIN = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "ingot.tin", "ingot_tin.png");

    // Misc //
    public static final Block BLOCK_OF_BRONZE = BlockHelper.createBlock(MOD_ID, new Block(nextBlock(), Material.iron), "block.bronze", "block_bronze_top.png", "block_bronze_bottom.png", "block_bronze_sides.png", Block.soundMetalFootstep, 5.0f, 6.0f, 0.0f);

    public static final Item DUST_BRONZE = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "dust.bronze", "dust_bronze.png");
    public static final Item INGOT_BRONZE = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "ingot.bronze", "ingot_bronze.png");

    // Vanilla Metals //
    public static final Item DUST_IRON = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "dust.iron", "dust_iron.png");
    public static final Item DUST_GOLD = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "dust.gold", "dust_gold.png");

    // Tools //
    public static final Item BATTERY_REDSTONE = ItemHelper.createItem(MOD_ID, new ItemBatteryRedstone(nextItem()), "battery.redstone").setMaxStackSize(1);

    @Override
    public void onInitialize() {
        LOGGER.info("Better than Industry2 has been initialized. Have fun!");
    }
}
