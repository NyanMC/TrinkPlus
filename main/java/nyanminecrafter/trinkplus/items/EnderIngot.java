package nyanminecrafter.trinkplus.items;

import net.minecraft.item.Item;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;

public class EnderIngot extends Item implements IsModelLoaded {
	
	public EnderIngot(String name) {
		this.setTranslationKey(name);
		this.setRegistryName(name);
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
