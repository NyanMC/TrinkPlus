package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperTeddyBearSettings;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.capabilities.magic.MagicStats;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IHeldAbility;
import xzeroair.trinkets.traits.abilities.interfaces.ISleepAbility;
import xzeroair.trinkets.util.TrinketsConfig;
import xzeroair.trinkets.util.config.trinkets.ConfigTeddyBear;

public class AbilityExquisitelyRested extends Ability implements ISleepAbility, IHeldAbility {
	
	public static final ConfigTeddyBear serverConfig = TrinketsConfig.SERVER.Items.TEDDY_BEAR;
	private SuperTeddyBearSettings superConfig = ModConfig.SERVER.S_TB;
	
	public AbilityExquisitelyRested() {
		super(Reference.MODID, "exquisitely_rested");
	}
	
	@Override
	public void onWakeUp(EntityLivingBase entity, boolean wakeImmediately, boolean updatedWorld, boolean setSpawn) {
		if (entity.world.isRemote)
			return;
		if (superConfig.healthRestoreAmount > 0.0F) {
			entity.heal(entity.getMaxHealth() * superConfig.healthRestoreAmount);
			if (entity.getHealth() > entity.getMaxHealth())
				entity.setHealth(entity.getMaxHealth());
		}
		if (superConfig.manaRestoreAmount > 0.0F) {
			MagicStats mana = Capabilities.getMagicStats(entity);
			if (mana != null) {
				mana.addMana(mana.getMaxMana() * superConfig.manaRestoreAmount);
			}
		}
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		if (superConfig.healthRestoreAmount > 0.0F || superConfig.manaRestoreAmount > 0.0F)
			tooltips.add("Upon sleeping:");
		if (superConfig.healthRestoreAmount > 0.0F)
			tooltips.add("Restores " + Math.floor(superConfig.healthRestoreAmount * 100) + "% of your maximum health");
		if (superConfig.manaRestoreAmount > 0.0F)
			tooltips.add("Restores " + Math.floor(superConfig.manaRestoreAmount * 100) + "% of your maximum mana");
	}

}
