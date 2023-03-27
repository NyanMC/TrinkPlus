package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperFaelisClawsSettings;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IAttackAbility;

public class AbilityDeadlyStrike extends Ability implements IAttackAbility {
	
	private SuperFaelisClawsSettings config = ModConfig.SERVER.S_CLAWS;
	
	public AbilityDeadlyStrike() {
		super(Reference.MODID, "deadly_strike");
	}
	
	@Override
	public float hurt(EntityLivingBase attacked, DamageSource source, float dmg) {
		if (!(source.canHarmInCreative())) {
			return (dmg * config.multiply_incoming);
		}
		return dmg;
	}
	
	@Override
	public float hurtEntity(EntityLivingBase target, DamageSource source, float dmg) {
		if (config.multiply_all_outgoing) {
			if (!source.canHarmInCreative()) {
				return (dmg * config.multiply_outgoing);
			}
		} else {
			if (!(source.canHarmInCreative() || isIndirectDamage(source) || source.isFireDamage())) {
				return (dmg * config.multiply_outgoing);
			}
		}
		return dmg;
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		if (config.multiply_all_outgoing)
			tooltips.add("ALL damage you deal is multiplied by " + config.multiply_outgoing);
		else
			tooltips.add("All pseudo-melee damage you deal is multiplied by " + config.multiply_outgoing);
		tooltips.add("However, ALL damage you take is multiplied by " + config.multiply_incoming);
		if (!config.multiply_all_outgoing)
			tooltips.add("Pseudo-melee damage is everything but fire damage, magic damage, explosions, and projectiles");
	}
	
}
