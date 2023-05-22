package turniplabs.industry.block

import net.minecraft.src.Block
import net.minecraft.src.BlockLog
import net.minecraft.src.Material
import sunsetsatellite.energyapi.template.blocks.BlockWire
import turniplabs.halplibe.helper.BlockHelper
import turniplabs.industry.Industry2

object IndustryBlocks {

    private var blockID = 1199

    private fun nextBlockID(): Int {
        return blockID++
    }

    // COPPER
    val ORE_COPPER_STONE: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockOreCopper(nextBlockID(), Material.rock),
        "ore.copper.stone",
        "ore_copper_stone.png",
        Block.soundStoneFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val ORE_COPPER_BASALT: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockOreCopper(nextBlockID(), Material.rock),
        "ore.copper.basalt",
        "ore_copper_basalt.png",
        Block.soundStoneFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val ORE_COPPER_LIMESTONE: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockOreCopper(nextBlockID(), Material.rock),
        "ore.copper.limestone",
        "ore_copper_limestone.png",
        Block.soundStoneFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val ORE_COPPER_GRANITE: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockOreCopper(nextBlockID(), Material.rock),
        "ore.copper.granite",
        "ore_copper_granite.png",
        Block.soundStoneFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val BLOCK_OF_COPPER: Block = BlockHelper.createBlock(
        Industry2.MODID,
        Block(nextBlockID(), Material.iron),
        "block.copper",
        "block_copper_top.png",
        "block_copper_bottom.png",
        "block_copper_sides.png",
        Block.soundMetalFootstep,
        5.0f,
        6.0f,
        0.0f
    )

    val COPPER_CABLE: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockWire(nextBlockID(), Material.iron),
        "cable.copper",
        "block_copper_sides.png",
        Block.soundMetalFootstep,
        0.2f,
        0.2f,
        0.2f
    )

    // TIN
    val ORE_TIN_STONE: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockOreTin(nextBlockID(), Material.rock),
        "ore.tin.stone",
        "ore_tin_stone.png",
        Block.soundStoneFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val ORE_TIN_BASALT: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockOreTin(nextBlockID(), Material.rock),
        "ore.tin.basalt",
        "ore_tin_basalt.png",
        Block.soundStoneFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val ORE_TIN_LIMESTONE: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockOreTin(nextBlockID(), Material.rock),
        "ore.tin.limestone",
        "ore_tin_limestone.png",
        Block.soundStoneFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val ORE_TIN_GRANITE: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockOreTin(nextBlockID(), Material.rock),
        "ore.tin.granite",
        "ore_tin_granite.png",
        Block.soundStoneFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val BLOCK_OF_TIN: Block = BlockHelper.createBlock(
        Industry2.MODID,
        Block(nextBlockID(), Material.iron),
        "block.tin",
        "block_tin_top.png",
        "block_tin_bottom.png",
        "block_tin_sides.png",
        Block.soundMetalFootstep,
        5.0f,
        6.0f,
        0.0f
    )

    val TIN_CABLE: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockWire(nextBlockID(), Material.iron),
        "cable.tin",
        "block_tin_sides.png",
        Block.soundMetalFootstep,
        0.2f,
        0.2f,
        0.2f
    )

    // BRONZE
    val BLOCK_OF_BRONZE: Block = BlockHelper.createBlock(
        Industry2.MODID,
        Block(nextBlockID(), Material.iron),
        "block.bronze",
        "block_bronze_top.png",
        "block_bronze_bottom.png",
        "block_bronze_sides.png",
        Block.soundMetalFootstep,
        5.0f,
        6.0f,
        0.0f
    )

    // MISCELLANEOUS
    val RUBBER_LOG: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockLog(nextBlockID()),
        "log.rubber",
        "rubber_log_top.png",
        "rubber_log.png",
        Block.soundWoodFootstep,
        2.0f,
        2.0f,
        0.0f
    )

    val RUBBER_LEAVES: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockLeavesRubber(nextBlockID(), Material.leaves, false),
        "leaves.rubber",
        "rubber_leaves.png",
        Block.soundGrassFootstep,
        0.2f,
        0.2f,
        0.0f
    )

    val RUBBER_LEAVES_FAST: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockLeavesRubber(nextBlockID(), Material.leaves, false),
        "leaves.rubber",
        "rubber_leaves_fast.png",
        Block.soundGrassFootstep,
        0.2f,
        0.2f,
        0.0f
    )

    val RUBBER_SAPLING: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockSaplingRubber(nextBlockID()),
        "sapling.rubber",
        "rubber_sapling.png",
        Block.soundGrassFootstep,
        0.0f,
        0.0f,
        0.0f
    )

    // MACHINES
    val MACHINE_CASING_BASIC: Block = BlockHelper.createBlock(
        Industry2.MODID,
        Block(nextBlockID(), Material.iron),
        "machine.casing.basic",
        "machine_casing_basic.png",
        Block.soundMetalFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val MACHINE_CASING_ADVANCED: Block = BlockHelper.createBlock(
        Industry2.MODID,
        Block(nextBlockID(), Material.iron),
        "machine.casing.advanced",
        "machine_casing_advanced.png",
        Block.soundMetalFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val MACHINE_GENERATOR: Block = BlockHelper.createBlock(
        Industry2.MODID,
        BlockIndustryGenerator(nextBlockID(), Material.iron),
        "machine.generator",
        "machine_casing_basic.png",
        "machine_casing_basic.png",
        "machine_generator.png",
        "machine_casing_basic.png",
        "machine_casing_basic.png",
        "machine_casing_basic.png",
        Block.soundMetalFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val MACHINE_GENERATOR_SOLAR: Block = BlockHelper.createBlock(
        Industry2.MODID,
        Block(nextBlockID(), Material.iron),
        "machine.generator.solar",
        "machine_generator_solar_top.png",
        "machine_casing_basic.png",
        "machine_casing_basic.png",
        Block.soundMetalFootstep,
        3.0f,
        3.0f,
        0.0f
    )

    val MACHINE_RELAY: Block = BlockHelper.createBlock(
        Industry2.MODID,
        Block(nextBlockID(), Material.iron),
        "machine.relay",
        "machine_casing_basic.png",
        Block.soundMetalFootstep,
        3.0f,
        3.0f,
        0.0f
    )
}