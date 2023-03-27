package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.DualityRingsSettings;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IAttackAbility;

public class AbilityAroOfClubs extends Ability implements IAttackAbility {
	
	private DualityRingsSettings config = ModConfig.SERVER.RINGS;
	
	public AbilityAroOfClubs() {
		super(Reference.MODID, "aro_of_clubs");
	}
	
	@Override
	public float hurtEntity(EntityLivingBase target, DamageSource source, float dmg) {
		if (source.isProjectile() == true) {
			if (source.getDamageType().equalsIgnoreCase("arrow") || config.boostAllProjectiles == true) {
				return (dmg * config.arrowAmplifier);
			}
		}
		return dmg;
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		if (config.boostAllProjectiles == false) {
			tooltips.add("Multiplies arrow damage by " + config.arrowAmplifier);
		} else {
			tooltips.add("Multiplies projectile damage by " + config.arrowAmplifier);
		}
		super.getDescription(tooltips);
	}
}
