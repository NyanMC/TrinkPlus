package nyanminecrafter.trinkplus.items.base;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import xzeroair.trinkets.items.base.AccessoryBase;

// this class is used for things i was getting tired of copypasting between classes
public class BaseAccessory extends AccessoryBase implements IsModelLoaded {
	
	public BaseAccessory(String name) {
		super(name);
		ModItems.TRINKETS_ITEMS.add(this);
		this.setCreativeTab(TrinkPlus.trinkplustab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if (enchantment == Enchantments.BINDING_CURSE) {
			return true;
		} else {
			return super.canApplyAtEnchantingTable(stack, enchantment);
		}
	}
	
}
