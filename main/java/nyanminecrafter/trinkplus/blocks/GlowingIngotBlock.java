package nyanminecrafter.trinkplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import nyanminecrafter.trinkplus.TrinkPlus;

public class GlowingIngotBlock extends Block {

	public GlowingIngotBlock(String name) {
		super(Material.IRON, MapColor.YELLOW);
		this.setHardness(5F);
		this.setResistance(6F);
		this.setHarvestLevel("pickaxe", 1);
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
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 15;
	}

}
