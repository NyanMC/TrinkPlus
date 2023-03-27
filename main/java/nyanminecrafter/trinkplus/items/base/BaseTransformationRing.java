package nyanminecrafter.trinkplus.items.base;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import xzeroair.trinkets.items.base.TrinketRaceBase;
import xzeroair.trinkets.races.EntityRace;

public class BaseTransformationRing extends TrinketRaceBase implements IsModelLoaded {

	public BaseTransformationRing(String name, EntityRace race, String[] attributeConfig) {
		super(name, race, null);
		ModItems.TRINKETS_ITEMS.add(this);
		this.setAttributeConfig(attributeConfig);
		this.setCreativeTab(TrinkPlus.trinkplustab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public boolean ItemEnabled() {
		return true;
	}

}
