package nyanminecrafter.trinkplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import nyanminecrafter.trinkplus.TrinkPlus;

public class EnderIngotBlock extends Block {

	public EnderIngotBlock(String name) {
		super(Material.IRON, MapColor.YELLOW);
		this.setHardness(5F);
		this.setResistance(1200F);
		this.setHarvestLevel("pickaxe", 2);
		this.setSoundType(SoundType.METAL);
		this.setTranslationKey(name);
		this.setRegistryName(name);
		this.setCreativeTab(TrinkPlus.trinkplustab);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return true;
	}

}
