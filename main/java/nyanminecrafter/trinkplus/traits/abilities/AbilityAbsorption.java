package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.GoldenHeartSettings;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.ITickableAbility;

public class AbilityAbsorption extends Ability implements ITickableAbility {
	
	private GoldenHeartSettings config = ModConfig.SERVER.HEART;
	
	public AbilityAbsorption() {
		super(Reference.MODID, "absorption");
	}

	@Override
	public void tickAbility(EntityLivingBase entity) {
		if(entity.ticksExisted%config.recharge==0) {
			entity.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, config.recharge, config.potency, false, false));
//			TrinkPlus.log.info("Attempting to reapply player absorption");
		}
	}
	
	@Override
	public void onAbilityAdded(EntityLivingBase entity) {
		if(!entity.isPotionActive(MobEffects.ABSORPTION)) {
			entity.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, config.recharge, config.potency, false, false));
		}
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("Applies the Absorption effect at level " + (config.potency + 1) + " for " + (config.recharge / 20) + " seconds");
		tooltips.add("When the effect is about to run out, it is replenished");
	}
	
}
