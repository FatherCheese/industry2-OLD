package turniplabs.industry;

import net.fabricmc.api.ModInitializer;
import net.minecraft.src.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sunsetsatellite.energyapi.EnergyAPI;
import sunsetsatellite.energyapi.template.blocks.BlockGenerator;
import turniplabs.halplibe.helper.*;
import turniplabs.industry.block.*;
import turniplabs.industry.block.machines.BlockGeneratorInd;
import turniplabs.industry.block.machines.BlockSolarGenerator;
import turniplabs.industry.block.tile.TileEntityGeneratorInd;
import turniplabs.industry.gui.GuiGeneratorInd;
import turniplabs.industry.gui.GuiSolarGenerator;
import turniplabs.industry.item.*;
import turniplabs.industry.block.tile.TileEntitySolarGenerator;
import turniplabs.industry.block.tile.TileEntityCable;

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
    public static final Block COPPER_CABLE = BlockHelper.createBlock(MOD_ID, new BlockCable(nextBlock(), Material.iron, 32, 0, 32, 1), "cable.copper", "cable_copper.png", Block.soundClothFootstep, 0.0f, 0.0f, 0.0f);
    public static final Block TIN_CABLE = BlockHelper.createBlock(MOD_ID, new BlockCable(nextBlock(), Material.iron, 8, 0, 8, 0), "cable.tin", "block_tin_sides.png", Block.soundClothFootstep, 0.0f, 0.0f, 0.0f);

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

    // Bronze //
    public static final Block BLOCK_OF_BRONZE = BlockHelper.createBlock(MOD_ID, new Block(nextBlock(), Material.iron), "block.bronze", "block_bronze_top.png", "block_bronze_bottom.png", "block_bronze_sides.png", Block.soundMetalFootstep, 5.0f, 6.0f, 0.0f);

    public static final Item DUST_BRONZE = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "dust.bronze", "dust_bronze.png");
    public static final Item INGOT_BRONZE = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "ingot.bronze", "ingot_bronze.png");

    // Vanilla Metals //
    public static final Item DUST_IRON = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "dust.iron", "dust_iron.png");
    public static final Item DUST_GOLD = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "dust.gold", "dust_gold.png");

    // Misc //
    public static final Block RUBBER_LOG = BlockHelper.createBlock(MOD_ID, new BlockLog(nextBlock()), "log.rubber", "rubber_log_top.png", "rubber_log.png", Block.soundWoodFootstep, 2.0f, 2.0f, 0.0f).setFenceCanConnectTo();
    public static final Block RUBBER_LEAVES = BlockHelper.createBlock(MOD_ID, new BlockLeavesRubber(nextBlock(), Material.plants, false), "leaves.rubber", "rubber_leaves.png", Block.soundGrassFootstep,  0.2f, 0.2f, 0.0f);
    public static final Block RUBBER_LEAVES_FAST = BlockHelper.createBlock(MOD_ID, new BlockLeavesRubber(nextBlock(), Material.plants, false), "leaves.rubber", "rubber_leaves_fast.png", Block.soundGrassFootstep, 0.2f, 0.2f, 0.0f).setNotInCreativeMenu();
    public static final Block RUBBER_SAPLING = BlockHelper.createBlock(MOD_ID, new BlockSaplingRubber(nextBlock()), "sapling.rubber", "rubber_sapling.png", Block.soundGrassFootstep, 0.0f, 0.0f, 0.0f);

    public static final Item TREE_RESIN = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "resin", "tree_resin.png");
    public static final Item INGOT_RUBBER = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "ingot.rubber", "ingot_rubber.png");

    // Tools //
    public static final Item TOOL_TAP = ItemHelper.createItem(MOD_ID, new ItemTreeTap(nextItem()), "tool.tap", "tree_tap.png");
    public static final Item TOOL_MULTIMETER = ItemHelper.createItem(MOD_ID, new ItemMultiMeter(nextItem()), "tool.multimeter", "tool_multimeter.png");
    public static final Item TOOL_WRENCH = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "tool.wrench", "tool_wrench.png");

    public static final Item BATTERY_REDSTONE = ItemHelper.createItem(MOD_ID, new ItemBatteryRedstone(nextItem()), "battery.redstone").setMaxStackSize(1);

    // Machines //
    public static final Block MACHINE_CASING_BASIC = BlockHelper.createBlock(MOD_ID, new Block(nextBlock(), Material.iron), "machine.casing.basic", "machine_casing_basic.png", Block.soundMetalFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block MACHINE_CASING_ADVANCED = BlockHelper.createBlock(MOD_ID, new Block(nextBlock(), Material.iron), "machine.casing.advanced", "machine_casing_advanced.png", Block.soundMetalFootstep, 3.0f, 3.0f, 0.0f);

    public static final Block MACHINE_GENERATOR = BlockHelper.createBlock(MOD_ID, new BlockGeneratorInd(nextBlock(), Material.iron), "machine.generator", "machine_casing_basic.png", "machine_casing_basic.png", "machine_generator.png", "machine_casing_basic.png", "machine_casing_basic.png", "machine_casing_basic.png", Block.soundMetalFootstep, 3.0f, 3.0f, 0.0f);
    public static final Block MACHINE_GENERATOR_SOLAR = BlockHelper.createBlock(MOD_ID, new BlockSolarGenerator(nextBlock(), Material.iron), "machine.generator.solar", "machine_generator_solar_top.png", "machine_casing_basic.png", "machine_casing_basic.png", Block.soundMetalFootstep, 3.0f, 3.0f, 0.0f);

    public static final Item CIRCUIT_BASIC = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "circuit.basic", "circuit_basic.png");
    public static final Item CIRCUIT_ADVANCED = ItemHelper.createItem(MOD_ID, new Item(nextItem()), "circuit.advanced", "circuit_advanced.png");

    @Override
    public void onInitialize() {
        LOGGER.info("Better than Industry2 has been initialized. Have fun!");

        EntityHelper.createTileEntity(TileEntityCable.class, "Cable");
        EntityHelper.createTileEntity(TileEntitySolarGenerator.class, "MACHINE_GENERATOR_SOLAR");
        EntityHelper.createTileEntity(TileEntityGeneratorInd.class, "GENERATOR");

        int[] copperCable = TextureHelper.registerBlockTexture(MOD_ID, "item_cable_copper.png");
        int[] tinCable = TextureHelper.registerBlockTexture(MOD_ID, "item_cable_tin.png");

        Item.itemsList[COPPER_CABLE.blockID].setIconCoord(copperCable[0], copperCable[1]);
        Item.itemsList[TIN_CABLE.blockID].setIconCoord(tinCable[0], tinCable[1]);


        EnergyAPI.addToNameGuiMap("Solar Generator", GuiSolarGenerator.class, TileEntitySolarGenerator.class);
        EnergyAPI.addToNameGuiMap("Generator", GuiGeneratorInd.class, TileEntityGeneratorInd.class);
    }
}
