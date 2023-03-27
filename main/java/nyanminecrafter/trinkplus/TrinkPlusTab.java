package nyanminecrafter.trinkplus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import nyanminecrafter.trinkplus.init.ModItems;

public class TrinkPlusTab extends CreativeTabs {

	public TrinkPlusTab() {
		super("trinkplustab");
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack createIcon() {
		if (Loader.isModLoaded("baubles")) {
			return new ItemStack(ModItems.Baubles.baublegoldenheart);
		} else {
			return new ItemStack(ModItems.goldenheart);
		}
	}

}
