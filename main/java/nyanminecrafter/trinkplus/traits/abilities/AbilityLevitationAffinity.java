package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.LunarCrystalSettings;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IAttackAbility;
import xzeroair.trinkets.traits.abilities.interfaces.IPotionAbility;

public class AbilityLevitationAffinity extends Ability implements IAttackAbility, IPotionAbility {
	
	private LunarCrystalSettings config = ModConfig.SERVER.CRYSTAL;
	
	public AbilityLevitationAffinity() {
		super(Reference.MODID, "levitation_affinity");
	}
	
	@Override
	public boolean attacked(EntityLivingBase attacked, DamageSource source, float dmg, boolean cancel) {
		if (source.equals(DamageSource.FALL) && config.ignoreFallDamage)
			return true;
		return cancel;
	}
	
	@Override
	public boolean attackEntity(EntityLivingBase target, DamageSource source, float dmg, boolean cancel) {
		//System.out.println("hi");
		if (!target.isPotionActive(MobEffects.LEVITATION)) {
			final Random rand = new Random();
			if (rand.nextInt(config.levitationChance - 1) == 0) {
				target.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, config.levitationDuration, config.levitationAmplifier, false, true));
			}
		}
		return cancel;
	}

	@Override
	public boolean potionApplied(EntityLivingBase entity, PotionEffect effect, boolean cancel) {
		final String e = effect.getPotion().getRegistryName().toString();
		if (e.contentEquals("minecraft:levitation"))
			return true;
		return cancel;
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("Grants immunity to levitation and fall damage");
		tooltips.add("Each attack has a 1 in " + (config.levitationChance - 1) + " chance to grant Levitation " + (config.levitationAmplifier + 1) + " for " + (config.levitationDuration / 20) + " seconds");
	}
}
