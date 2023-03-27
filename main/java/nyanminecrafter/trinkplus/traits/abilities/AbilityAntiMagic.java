package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.DispelGelSettings;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IAttackAbility;

public class AbilityAntiMagic extends Ability implements IAttackAbility {
	
	private DispelGelSettings config = ModConfig.SERVER.GEL;
	
	public AbilityAntiMagic() {
		super(Reference.MODID, "anti_magic");
	}
	
	@Override
	public float hurt(EntityLivingBase attacked, DamageSource source, float dmg) {
		if (!(source.canHarmInCreative()) && (source.isMagicDamage())) {
			return (dmg * config.multiply_incoming);
		}
		return dmg;
	}
	
	@Override
	public float hurtEntity(EntityLivingBase target, DamageSource source, float dmg) {
		// with only vanilla, trinkets, and trinkplus damage types taken into account the only damage type both magic and absolute is overload damage
		if (!(source.canHarmInCreative() || source.isDamageAbsolute()) && (source.isMagicDamage())) {
			return (dmg * config.multiply_outgoing);
		}
		return dmg;
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("All magic damage you take is multiplied by " + config.multiply_incoming);
		tooltips.add("However, all magic damage you deal is multiplied by " + config.multiply_outgoing);
		tooltips.add("Some types of magic damage may not be ignored");
	}
	
}
