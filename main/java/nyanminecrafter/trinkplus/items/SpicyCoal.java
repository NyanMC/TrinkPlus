package nyanminecrafter.trinkplus.items;

import net.minecraft.item.ItemStack;
import nyanminecrafter.trinkplus.items.base.BaseItem;

public class SpicyCoal extends BaseItem {

	public SpicyCoal() {
		super("spicy_coal");
		this.setMaxDamage(0);
	}
	
	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		return 12800;
	}

}
