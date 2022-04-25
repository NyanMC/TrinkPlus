package nyanminecrafter.trinkplus.items.food;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.events.ModItemRegistry;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;

public class MagicGarlicBread extends ItemFood implements IsModelLoaded {

	public MagicGarlicBread(String name) {
		super(10, 1F, false);
		this.setTranslationKey(name);
		this.setRegistryName(name);
		this.setAlwaysEdible();
		ModItems.ITEMS.add(this);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		entityLiving.addPotionEffect(new PotionEffect(ModItemRegistry.myPotion.getPotion(), 6000, 1));
		super.onItemUseFinish(stack, worldIn, entityLiving);
		return stack;
	}
	
	@Override
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
