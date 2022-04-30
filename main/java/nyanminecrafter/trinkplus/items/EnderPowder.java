package nyanminecrafter.trinkplus.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;

public class EnderPowder extends Item implements IsModelLoaded {
	
	public EnderPowder(String name) {
		this.setTranslationKey(name);
		this.setRegistryName(name);
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		// TODO Auto-generated method stub
		return EnumRarity.EPIC;
	}
}
