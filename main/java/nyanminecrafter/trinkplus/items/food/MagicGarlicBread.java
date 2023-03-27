package nyanminecrafter.trinkplus.items.food;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.events.ModItemRegistry;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.GarlicBreadSettings;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.items.potions.PotionObject;

public class MagicGarlicBread extends ItemFood implements IsModelLoaded {

	private GarlicBreadSettings config = ModConfig.SERVER.BREAD;

	public MagicGarlicBread(String name) {

		super(10, 1F, false);
		this.setTranslationKey(name);
		this.setRegistryName(name);
		this.setCreativeTab(TrinkPlus.trinkplustab);
		this.setAlwaysEdible();
		ModItems.ITEMS.add(this);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		final PotionObject potion = ModItemRegistry.myPotions.get("manaregen");
		if (potion != null) {
			if (config.manaRegenLevel > -1) {
				entityLiving.addPotionEffect(new PotionEffect(potion.getPotion(), config.manaRegenDuration, config.manaRegenLevel));
			}
		} else {
			TrinkPlus.log.warn("Mana regen effect missing. This is not a good thing");
		}
		if (!entityLiving.world.isRemote) {
			Capabilities.getEntityProperties(entityLiving, prop -> {
				if (prop.getAbilityHandler().getAbility("trinkplus:ace_of_spades") != null) {
					if (config.healthBoostLevel > -1) {
						entityLiving.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, config.healthBoostDuration, config.healthBoostLevel));
					}
					if (config.saturationLevel > -1) {
						entityLiving.addPotionEffect(new PotionEffect(MobEffects.SATURATION, config.saturationDuration, config.saturationLevel));
					}
				}
			});
		}
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}

	@Override
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

}
