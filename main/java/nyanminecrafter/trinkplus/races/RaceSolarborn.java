package nyanminecrafter.trinkplus.races;

import java.util.UUID;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.events.ModItemRegistry;
import nyanminecrafter.trinkplus.init.ModRaces;
import nyanminecrafter.trinkplus.traits.abilities.AbilityWaterDamage;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SolarbornSettings;
import xzeroair.trinkets.Trinkets;
import xzeroair.trinkets.attributes.UpdatingAttribute;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.capabilities.magic.MagicStats;
import xzeroair.trinkets.items.potions.PotionObject;
import xzeroair.trinkets.races.EntityRacePropertiesHandler;
import xzeroair.trinkets.traits.abilities.AbilityFireBreathing;
import xzeroair.trinkets.traits.abilities.AbilityFireImmunity;
import xzeroair.trinkets.traits.abilities.compat.survival.AbilityHeatImmunity;

public class RaceSolarborn extends EntityRacePropertiesHandler {

	private SolarbornSettings config = ModConfig.SERVER.SOLARBORN;

	protected UpdatingAttribute movement, damage, armor;

	public RaceSolarborn(@Nonnull EntityLivingBase e) {
		super(e, ModRaces.solarborn);
		movement = new UpdatingAttribute(UUID.fromString("6f04b992-9f18-42bc-9718-464b22f0c7dc"), SharedMonsterAttributes.MOVEMENT_SPEED).setSavedInNBT(false);
		damage = new UpdatingAttribute(UUID.fromString("69e37397-e10d-4eda-9881-91f8d9f5bf2d"), SharedMonsterAttributes.ATTACK_DAMAGE).setSavedInNBT(false);
		armor = new UpdatingAttribute(UUID.fromString("cba45f5e-6991-4d41-84f9-21801926092d"), SharedMonsterAttributes.ARMOR).setSavedInNBT(false);
	}

	@Override
	public void startTransformation() {
		this.addAbility(new AbilityFireImmunity());
		if (Trinkets.ToughAsNails || Trinkets.SimpleDifficulty) {
			this.addAbility(new AbilityHeatImmunity());
		}
		this.addAbility(new AbilityFireBreathing());
		this.addAbility(new AbilityWaterDamage());
	}

	@Override
	public void whileTransformed() {
		//TODO maybe move this to an ability?
		final MagicStats magic = Capabilities.getMagicStats(entity);
		if (magic != null) {
			final double mana = magic.getMana() / 2;
			final double manaMax = magic.getMaxMana() / 2;

			final int minSize = config.minSize;
			final int maxSize = config.maxSize;
			
			double percent;
			
			if (manaMax > 0) {
				percent = mana / manaMax;
			} else {
				percent = 0D; // i'm not sure if divide by zero is even possible here but i am not taking chances
			}
//			double sizeValue = Math.max(minSize, (maxSize * percent));

			double speedMod = ((config.minSpeedCap)+(config.maxSpeedCap-config.minSpeedCap) * percent);
			double dmgMod = ((config.minDamageCap)+(config.maxDamageCap-config.minDamageCap) * percent);
			double armorMod = ((config.minArmorCap)+(config.maxArmorCap-config.minArmorCap) * percent);

//			this.setTargetHeight((int) sizeValue);
//			this.setTargetWidth((int) sizeValue);
			movement.addModifier(entity, -speedMod, 2);
			damage.addModifier(entity, dmgMod, 2);
			armor.addModifier(entity, armorMod, 2);
		}
	}
	
	@Override
	public int getTargetHeight() {
		final MagicStats magic = Capabilities.getMagicStats(entity);
		if (magic != null) {
			final double mana = magic.getMana() / 2;
			final double manaMax = magic.getMaxMana() / 2;

			final int minSize = config.minSize;
			final int maxSize = config.maxSize;
			final double percent = mana / manaMax;//(minSize / maxSize);
			double sizeValue = Math.max(minSize, (maxSize * percent));
			
			return (int) sizeValue;
		}
		return super.getTargetHeight();
	}
	
	@Override
	public int getTargetWidth() {
		// TODO Auto-generated method stub
		return getTargetHeight();
	}

	@Override
	public boolean isAttacked(DamageSource source, float dmg) {
		if (entity.getEntityWorld().isRemote) {
			return super.isAttacked(source, dmg); // how did i forget to do this for literal months
		}
		final PotionObject potion = ModItemRegistry.myPotions.get("manableed");
		if ((dmg > 0F) && (config.manaBleedLevel > -1) && !(source.isFireDamage() || source.isMagicDamage())) { // why do i even need a check for fire damage when the player shouldn't even be taking fire damage in the first place
			Capabilities.getEntityProperties(entity, prop -> {
				if (potion != null) {
					if (prop.getAbilityHandler().getAbility("trinkplus:friendly_firing") != null) {
						entity.addPotionEffect(new PotionEffect(potion.getPotion(), config.manaBleedDuration / 2, config.manaBleedLevel));
					} else {
						entity.addPotionEffect(new PotionEffect(potion.getPotion(), config.manaBleedDuration, config.manaBleedLevel));
					}
				} else {
					TrinkPlus.log.warn("Mana bleed effect missing. This is REALLY not a good thing");
				}
			});
		}
		return super.isAttacked(source, dmg);
	}
	
	@Override
	public float isHurt(DamageSource source, float dmg) {
		if (entity.getEntityWorld().isRemote) {
			return super.isHurt(source, dmg);
		}
		if (source.isFireDamage()) {
			return 0; // last resort fire damage nullification for solarborn, hopefully fixes that one tiered fire resistance mod
		}
		return super.isHurt(source, dmg);
	}

	@Override
	public void endTransformation() {
		movement.removeModifier(entity);
		damage.removeModifier(entity);
		armor.removeModifier(entity);
	}

}
