package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.FriendlyFireFlowerSettings;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.capabilities.magic.MagicStats;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IAttackAbility;

public class AbilityFriendlyFiring extends Ability implements IAttackAbility {
	
	private FriendlyFireFlowerSettings config = ModConfig.SERVER.FLOWER;
	
	public AbilityFriendlyFiring() {
		super(Reference.MODID, "friendly_firing");
	}
	
	@Override
	public float hurt(EntityLivingBase attacked, DamageSource source, float dmg) {
		float finalDamage = dmg;
		if (source.isFireDamage()) {
			finalDamage *= config.fireDamageMultiplier;
		}
		if (source.getTrueSource() == attacked) {
			MagicStats mana = Capabilities.getMagicStats(attacked);
			if (mana != null) {
				if (!config.isFlatCost) {
					float requiredMana = dmg * config.manaCost;
					if (mana.getMana() >= requiredMana) {
						mana.spendMana(requiredMana);
						attacked.getEntityWorld().playSound((EntityPlayer)null, attacked.getPosition(), SoundEvents.ENTITY_ENDEREYE_DEATH, SoundCategory.PLAYERS, 0.5F, 1F);
						return 0;
					} else {
						if (mana.getMana() > 0F) {
							attacked.getEntityWorld().playSound((EntityPlayer)null, attacked.getPosition(), SoundEvents.ENTITY_ENDEREYE_DEATH, SoundCategory.PLAYERS, 0.5F, 1F);
							mana.setMana(0F);
							return (finalDamage - (mana.getMana() / config.manaCost));
						}
					}
				} else {
					float requiredMana = config.manaCost;
					if (mana.getMana() >= requiredMana) {
						mana.spendMana(requiredMana);
						attacked.getEntityWorld().playSound((EntityPlayer)null, attacked.getPosition(), SoundEvents.ENTITY_ENDEREYE_DEATH, SoundCategory.PLAYERS, 0.5F, 1F);
						return 0;
					} else {
						return finalDamage;
					}
				}
			}
		}
		return finalDamage;
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("Multiplies fire damage by " + config.fireDamageMultiplier);
		tooltips.add("If you are a Solarborn, halves the Mana Bleed given when taking damage");
		tooltips.add("If you manage to harm yourself, the damage will be redirected to your mana reserves before harming you");
		if (!config.isFlatCost) {
			tooltips.add("It costs " + config.manaCost + "mana to block one damage");
		} else {
			tooltips.add("It costs " + config.manaCost + "mana to block an attack");
		}
	}
	
}
