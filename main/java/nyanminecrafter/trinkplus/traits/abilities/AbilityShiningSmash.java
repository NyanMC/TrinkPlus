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
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperShadowTreadsSettings;
import xzeroair.trinkets.attributes.UpdatingAttribute;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IPotionAbility;
import xzeroair.trinkets.traits.abilities.interfaces.ITickableAbility;

public class AbilityShiningSmash extends Ability implements ITickableAbility, IPotionAbility {
	
	private SuperShadowTreadsSettings config = ModConfig.SERVER.S_TREADS;
	
	public AbilityShiningSmash() {
		super(Reference.MODID, "shining_smash");
	}
	
	protected UpdatingAttribute movement = new UpdatingAttribute(UUID.fromString("081b5c32-b64e-4302-8d01-522d669b40af"), SharedMonsterAttributes.ATTACK_DAMAGE);

	@Override
	public void tickAbility(EntityLivingBase entity) {
		World world = entity.getEntityWorld();
		if (entity.isEntityAlive() && world != null && !world.isRemote) {
			BlockPos playerpos = new BlockPos(entity);
			Chunk chunk = world.getChunk(playerpos);
			
			if (world.getLight(playerpos, true) > 7) {
				movement.addModifier(entity, config.lightpower, config.lightpoweroperation);
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
		if (e.contentEquals("minecraft:weakness"))
			return true;
		return cancel;
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		String modifier = ""; // this blank string should never show up, i just wanted to avoid it being null ever
		if (config.lightpoweroperation == 0) {
			modifier = "" + config.lightpower;
		} else {
			modifier = (config.lightpower * 100) + "%";
		}
		tooltips.add("Grants immunity to weakness");
		tooltips.add("Attack damage increased by " + modifier + " in light");
	}

}
