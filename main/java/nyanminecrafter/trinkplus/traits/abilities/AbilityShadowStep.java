package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.ShadowTreadsSettings;
import xzeroair.trinkets.attributes.UpdatingAttribute;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IPotionAbility;
import xzeroair.trinkets.traits.abilities.interfaces.ITickableAbility;

public class AbilityShadowStep extends Ability implements ITickableAbility, IPotionAbility {
	
	private ShadowTreadsSettings config = ModConfig.SERVER.TREADS;
	
	public AbilityShadowStep() {
		super(Reference.MODID, "shadow_step");
	}
	
	protected UpdatingAttribute movement = new UpdatingAttribute(UUID.fromString("081b5c32-b64e-4302-8d01-522d669b40af"), SharedMonsterAttributes.MOVEMENT_SPEED);

	@Override
	public void tickAbility(EntityLivingBase entity) {
		World world = entity.getEntityWorld();
		if (entity.isEntityAlive() && world != null && !world.isRemote) {
			BlockPos playerpos = new BlockPos(entity);
			Chunk chunk = world.getChunk(playerpos);
			
			if (world.getLight(playerpos, true) <= 7) {
				movement.addModifier(entity, config.darkspeed, config.darkspeedoperation);
			} else {
				movement.removeModifier(entity);
			}
//			TrinkPlus.log.info("Current light is " + world.getLight(playerpos, true));
		}
	}
	
	@Override
	public void onAbilityRemoved(EntityLivingBase entity) {
		movement.removeModifier(entity);
	}

	@Override
	public boolean potionApplied(EntityLivingBase entity, PotionEffect effect, boolean cancel) {
		final String e = effect.getPotion().getRegistryName().toString();
		if (e.contentEquals("minecraft:slowness"))
			return true;
		return cancel;
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		String modifier = ""; // this blank string should never show up, i just wanted to avoid it being null ever
		if (config.darkspeedoperation == 0) {
			modifier = "" + config.darkspeed;
		} else {
			modifier = (config.darkspeed * 100) + "%";
		}
		tooltips.add("Grants immunity to slowness");
		tooltips.add("Movement speed increased by " + modifier + " in darkness");
	}

}
