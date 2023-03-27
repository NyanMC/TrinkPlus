package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperDamageShieldSettings;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.capabilities.Vip.VipStatus;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IAttackAbility;
import xzeroair.trinkets.traits.abilities.interfaces.IPotionAbility;
import xzeroair.trinkets.traits.abilities.interfaces.ITickableAbility;
import xzeroair.trinkets.util.TrinketsConfig;
import xzeroair.trinkets.util.config.ClientConfig.ClientConfigItems.ClientConfigDamageShield;
import xzeroair.trinkets.util.config.trinkets.ConfigDamageShield;

public class AbilityReturnToSender extends Ability implements ITickableAbility, IAttackAbility, IPotionAbility {
	
	protected static final ClientConfigDamageShield clientConfig = TrinketsConfig.CLIENT.items.DAMAGE_SHIELD;
	protected static final ConfigDamageShield serverConfig = TrinketsConfig.SERVER.Items.DAMAGE_SHIELD;
	private SuperDamageShieldSettings superConfig = ModConfig.SERVER.S_SOH;

	protected int hitCount;

	public AbilityReturnToSender() {
		super(Reference.MODID, "return_to_sender");
		hitCount = 0;
	}

	public int getHitCount() {
		return hitCount;
	}

	public AbilityReturnToSender setHitCount(int hitCount) {
		this.hitCount = hitCount;
		return this;
	}

	@Override
	public void tickAbility(EntityLivingBase entity) {
		final World world = entity.getEntityWorld();
		if (world.isRemote) {
			return;
		}
		final int duration = 30;
		Potion resistance = Potion.getPotionFromResourceLocation(serverConfig.potionEffect);
		if (resistance == null) {
			resistance = MobEffects.RESISTANCE;
		}
		final boolean has = entity.isPotionActive(resistance);
		if (!has) {
			entity.addPotionEffect(new PotionEffect(resistance, duration, serverConfig.resistance_level, false, false));
		} else {
		}
	}

	@Override
	public boolean attacked(EntityLivingBase attacked, DamageSource source, float dmg, boolean cancel) {
		if (serverConfig.damage_ignore) {
			if (!attacked.world.isRemote) {
				if (dmg >= 1) {
					final boolean indirect = this.isIndirectDamage(source);
					if (indirect) {
						this.countHit(attacked, indirect);
						if (this.trigger(attacked, indirect)) {
							return true;
						}
					}
				}
			}
		}
		return cancel;
	}

	@Override
	public float hurt(EntityLivingBase attacked, DamageSource source, float dmg) {
		if (serverConfig.damage_ignore) {
			if (dmg >= 1) {
				final boolean indirect = this.isIndirectDamage(source);
				if (!indirect) {
					this.countHit(attacked, indirect);
					if (this.trigger(attacked, indirect)) {
						Entity attacker = source.getTrueSource();
						if (attacker != null) {
							attacker.attackEntityFrom(DamageSource.causeThornsDamage(attacked), dmg * superConfig.thorns_multiplier);
						}
						dmg = 0;
					}
				}
			}
		}
		if (serverConfig.explosion_resist) {
			if (source.isExplosion()) {
				if (dmg >= 1)
					return dmg * serverConfig.explosion_amount;
			}
		}
		return dmg;
	}

	protected void countHit(EntityLivingBase attacked, boolean indirect) {
		hitCount++;
		Capabilities.getEntityProperties(attacked, prop -> {
			prop.sendInformationToPlayer(attacked);
		});
	}

	protected boolean trigger(EntityLivingBase attacked, boolean indirect) {
		if ((hitCount > serverConfig.hits)) {
			hitCount = 0;
			if (attacked instanceof EntityPlayer) {
				final EntityPlayer player = (EntityPlayer) attacked;
				if (serverConfig.special && TrinketsConfig.SERVER.misc.retrieveVIP) {
					String string = "Ow!";
					final VipStatus vip = Capabilities.getVipStatus(attacked);
					if (vip != null) {
						final String quote = vip.getRandomQuote();
						if (!quote.isEmpty()) {
							string = quote;
						}
					}
					final TextComponentString message = new TextComponentString(TextFormatting.BOLD + "" + TextFormatting.GOLD + string);
					player.sendStatusMessage(message, true);

					if (clientConfig.effectVolume > 0) {
						player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, (float) clientConfig.effectVolume, 1F);
					}
				}
			}
			Capabilities.getEntityProperties(attacked, prop -> {
				prop.sendInformationToPlayer(attacked);
			});
			return true;
		}
		return false;
	}

	@Override
	public boolean potionApplied(EntityLivingBase entity, PotionEffect effect, boolean cancel) {
		final String AppliedEffect = effect.getPotion().getRegistryName().toString();
		final String targetEffect = serverConfig.potionEffect;
		if (!AppliedEffect.contentEquals(targetEffect)) {
			return cancel;
		}
		if (!entity.world.isRemote && serverConfig.resistance_stacks) {
			Potion resistance = Potion.getPotionFromResourceLocation(targetEffect);
			if (resistance == null) {
				resistance = MobEffects.RESISTANCE;
			}
			final boolean has = entity.isPotionActive(resistance);

			final PotionEffect newEffect = effect;
			final int newAmp = newEffect.getAmplifier();
			final int newDur = newEffect.getDuration();

			if (!has) {
				//				System.out.println("We Don't Have it, Just apply");
				return cancel;
			} else {
				final PotionEffect oldEffect = entity.getActivePotionEffect(resistance);
				final int oldAmp = oldEffect.getAmplifier();
				final int oldDur = oldEffect.getDuration();

				//				if ((newDur > oldDur) && (newAmp <= oldAmp)) {
				//					System.out.println("Probably not?");
				//					return cancel;
				//				}

				int amp = newAmp + 1;
				int duration = newDur;

				if ((oldAmp > amp)) {
					//					System.out.println("Don't Allow");
					return true;
				}

				//				System.out.println("Upgrading: " + oldAmp + "|" + newAmp + "|" + amp);
				//				System.out.println("Upgrading: " + oldDur + "|" + newDur + "|" + duration);
				//						entity.removePotionEffect(resistance);
				//				System.out.println("Removing, Adding");
				//				entity.removeActivePotionEffect(resistance);
				//				entity.addPotionEffect(
				//						new PotionEffect(
				//								resistance,
				//								duration, // just give effect duration
				//								amp, // just give effect amp  + 1
				//								false,
				//								false
				//						)
				//				);
				if ((amp <= serverConfig.resistance_stacks_max)) {
					try {
						PotionEffect e = new PotionEffect(
								resistance,
								duration, // just give effect duration
								amp, // just give effect amp  + 1
								false,
								false
						);
						effect.combine(e);
					} catch (Exception e) {
					}
				}
				return false;
			}
		}
		return cancel;
	}

	@Override
	public void onAbilityRemoved(EntityLivingBase entity) {
		Potion resistance = Potion.getPotionFromResourceLocation(serverConfig.potionEffect);
		if (resistance == null) {
			resistance = MobEffects.RESISTANCE;
		}
		if (entity.isPotionActive(resistance)) {
			entity.removePotionEffect(resistance);
			if (entity.world.isRemote) {
				entity.addPotionEffect(new PotionEffect(resistance, 30, serverConfig.resistance_level, false, false));
			}
		}
	}

	@Override
	public void loadStorage(NBTTagCompound nbt) {
		if (nbt.hasKey("hitCount")) {
			hitCount = nbt.getInteger("hitCount");
		}
	}

	@Override
	public NBTTagCompound saveStorage(NBTTagCompound nbt) {
		nbt.setInteger("hitCount", hitCount);
		return nbt;
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("Inherits the abilities of the Shield of Honor");
		tooltips.add("Blocking a hit redirects the damage to the attacker");
	}
	
}
