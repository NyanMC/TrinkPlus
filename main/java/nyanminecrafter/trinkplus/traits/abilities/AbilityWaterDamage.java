package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.init.TrinkplusDamageSource;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.capabilities.magic.MagicStats;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.ITickableAbility;
import xzeroair.trinkets.util.TrinketsConfig;
import xzeroair.trinkets.util.handlers.Counter;

public class AbilityWaterDamage extends Ability implements ITickableAbility { // because xzeroair couldn't just make this separate from AbilityEnderQueen
	
	public AbilityWaterDamage() {
		super(Reference.MODID, "water_damage");
	}

	@Override
	public void tickAbility(EntityLivingBase entity) {
		if (entity.world.isRemote) {
			return;
		}
		final Counter counter = tickHandler.getCounter("water_hurt", 20, true, true, true, true);
		if (counter.Tick()) {
			if ((entity.isInWater() || entity.isWet())) {
				final MagicStats magic = Capabilities.getMagicStats(entity);
				if ((magic != null) && magic.spendMana(25F)) {
					magic.setManaRegenTimeout(TrinketsConfig.SERVER.mana.mana_regen_timeout * 2);
				} else {
					entity.attackEntityFrom(TrinkplusDamageSource.water.setDamageBypassesArmor().setMagicDamage(), 2);
				}
			}
			//			} else {
			//				this.removeCounter("water_hurt");
		}
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("Water drains mana, or deals damage if mana runs out");
	}

}
