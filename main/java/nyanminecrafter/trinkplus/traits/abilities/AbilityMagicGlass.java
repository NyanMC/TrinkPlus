package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.GlassShieldSettings;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IAttackAbility;
import xzeroair.trinkets.traits.abilities.interfaces.ITickableAbility;
import xzeroair.trinkets.util.handlers.Counter;

public class AbilityMagicGlass extends Ability implements ITickableAbility, IAttackAbility {
	
	private GlassShieldSettings config = ModConfig.SERVER.SHIELD;
	
	public AbilityMagicGlass() {
		super(Reference.MODID, "magic_glass");
	}

	@Override
	public void tickAbility(EntityLivingBase entity) {
		if(!entity.world.isRemote) {
			final Counter ticker = tickHandler.getCounter("cooldown");
			if ((ticker != null && ticker.Tick())) {
				tickHandler.removeCounter("cooldown");
//        		TrinkPlus.log.info("Glass shield cooldown finished");
				entity.getEntityWorld().playSound((EntityPlayer)null, entity.getPosition(), SoundEvents.BLOCK_GLASS_PLACE, SoundCategory.PLAYERS, 0.5F, 1F);
				if (entity instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entity;
        			final TextComponentString message = new TextComponentString(TextFormatting.BOLD + "" + TextFormatting.GOLD + "Your Glass Shield has recharged.");
        			player.sendStatusMessage(message, true);
				}
			}
		}
	}
	
	@Override
	public float hurt(EntityLivingBase attacked, DamageSource source, float dmg) {
		final Counter ticker = tickHandler.getCounter("cooldown");
		if (ticker == null) {
			tickHandler.addCounter("cooldown", config.cooldown, true, true, false);
			attacked.getEntityWorld().playSound((EntityPlayer)null, attacked.getPosition(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 0.5F, 1F);
			return 0;
		}
		return dmg;
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("Completely blocks the full damage of a single attack, and then shatters");
		tooltips.add("Takes " + (config.cooldown / 20) + " seconds to recharge after shattering");
	}
	
}
