package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.AzaleanSettings;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.ITickableAbility;

public class AbilityPhotosynthesis extends Ability implements ITickableAbility {
	
	private AzaleanSettings config = ModConfig.SERVER.AZALEAN;
	
	public AbilityPhotosynthesis() {
		super(Reference.MODID, "photosynthesis");
	}

	@Override
	public void tickAbility(EntityLivingBase entity) {
		//TODO maybe make this deplete the thirst bar faster if TaN/SD is installed
		World world = entity.getEntityWorld();
		if (entity.isEntityAlive() && (world != null) && !world.isRemote) {
			BlockPos playerpos = new BlockPos(entity);
			if ((config.regenerationLevel > -1) && world.isDaytime() && world.canSeeSky(playerpos)) {
				entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, config.regenerationDuration, config.regenerationLevel, false, false));
			}
		}
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("Grants regeneration under direct sunlight");
	}

}
