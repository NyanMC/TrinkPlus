package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import nyanminecrafter.trinkplus.Reference;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IPotionAbility;

public class AbilityPotionWarding extends Ability implements IPotionAbility {
	
	public AbilityPotionWarding() {
		super(Reference.MODID, "potion_warding");
	}

	@Override
	public boolean potionApplied(EntityLivingBase entity, PotionEffect effect, boolean cancel) {
		return true; // no potion effects applied on my watch
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("All potion effects, good or bad, are nullified upon receiving them");
	}

}
