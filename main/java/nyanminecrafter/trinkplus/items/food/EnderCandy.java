package nyanminecrafter.trinkplus.items.food;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.init.TrinkplusDamageSource;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.OverloadedCandySettings;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.items.foods.Mana_Candy;

public class EnderCandy extends Mana_Candy implements IsModelLoaded {
	
	private OverloadedCandySettings config = ModConfig.SERVER.CANDY;

	public EnderCandy(String name) {
		super(name);
		this.setCreativeTab(TrinkPlus.trinkplustab);
		this.setUUID("75b3e119-6999-406d-9484-0dce4c99e07d");
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (!entityLiving.world.isRemote) {
			Capabilities.getMagicStats(entityLiving, prop -> {
				if(prop.getRacialAffinity() < config.requiredMagicAffinity) {
					entityLiving.attackEntityFrom(TrinkplusDamageSource.overload.setDamageBypassesArmor().setDamageIsAbsolute().setMagicDamage(), config.damageAmount);
				}
			});
		}
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}

}
