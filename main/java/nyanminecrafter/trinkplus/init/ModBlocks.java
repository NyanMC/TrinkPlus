package nyanminecrafter.trinkplus.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import nyanminecrafter.trinkplus.blocks.BlockSuperTeddyBear;
import nyanminecrafter.trinkplus.blocks.EnderIngotBlock;
import nyanminecrafter.trinkplus.blocks.GlowingIngotBlock;

public class ModBlocks {
	
	public static List<Block> BLOCKS = new ArrayList<>();
	
	public static final Block glowingIngotBlock = new GlowingIngotBlock("glowing_ingot_block");
	public static final Block enderIngotBlock = new EnderIngotBlock("ender_ingot_block");
	public static final BlockSuperTeddyBear superTeddyBear = new BlockSuperTeddyBear();
	
	public static void registerBlocks() {
		registerBlock(glowingIngotBlock);
		registerBlock(enderIngotBlock);
		registerBlockWithoutItem(superTeddyBear);
	}
	
	public static void registerBlock(Block block) {
		BLOCKS.add(block);
		ModItems.ITEMS.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	
	public static void registerBlockWithoutItem(Block block) {
		BLOCKS.add(block);
	}
	
}
