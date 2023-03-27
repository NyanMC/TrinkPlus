package nyanminecrafter.trinkplus.races;

import java.util.UUID;

import javax.annotation.Nonnull;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import nyanminecrafter.trinkplus.init.ModRaces;
import nyanminecrafter.trinkplus.traits.abilities.AbilityPhotosynthesis;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.AzaleanSettings;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.AzaleanSettings.FlowerBoosts;
import xzeroair.trinkets.attributes.UpdatingAttribute;
import xzeroair.trinkets.races.EntityRacePropertiesHandler;

public class RaceAzalean extends EntityRacePropertiesHandler {

	protected UpdatingAttribute armor, damage, speed;

	private AzaleanSettings config = ModConfig.SERVER.AZALEAN;
	private FlowerBoosts boosts = config.flowerBoosts;

	public RaceAzalean(@Nonnull EntityLivingBase e) {
		super(e, ModRaces.azalean);
		armor = new UpdatingAttribute(UUID.fromString("59246e1b-8d69-4e21-ae34-046fae65e81e"), SharedMonsterAttributes.ARMOR).setSavedInNBT(false);
		damage = new UpdatingAttribute(UUID.fromString("2f055714-87a7-432f-af6d-8fa436b2646c"), SharedMonsterAttributes.ATTACK_DAMAGE).setSavedInNBT(false);
		speed = new UpdatingAttribute(UUID.fromString("d6f285db-c46f-4862-8045-17969180d1b9"), SharedMonsterAttributes.MOVEMENT_SPEED).setSavedInNBT(false);
	}
	
	@Override
	public void startTransformation() {
		this.addAbility(new AbilityPhotosynthesis());
	}

	@Override
	public void whileTransformed() {
		//TODO maybe move this to an ability?
		//TODO also redo this, it's very buggy and held together with dollar store glue
		final AxisAlignedBB aabb = entity.getEntityBoundingBox().grow(5, 1, 5);
		final int i = MathHelper.floor(aabb.minX);
		final int j = MathHelper.floor(aabb.maxX);
		final int i1 = MathHelper.floor(aabb.minZ);
		final int j1 = MathHelper.floor(aabb.maxZ);
		boolean foundFlower = false;
		for (int k1 = i; k1 < j; ++k1) {
			for (int i2 = i1; i2 < j1; ++i2) {
				final BlockPos pos = new BlockPos(k1, MathHelper.floor(aabb.minY), i2);
				final IBlockState block = entity.world.getBlockState(pos.add(0, 1, 0));
				if ((block.getBlock() instanceof BlockFlower) || (block.getBlock() instanceof BlockDoublePlant)) {
					damage.addModifier(entity, boosts.damageModifier, boosts.damageOperation);
					armor.addModifier(entity, boosts.armorModifier, boosts.armorOperation);
					speed.addModifier(entity, boosts.speedModifier, boosts.speedOperation);
					foundFlower = true;
				}
			}
		}
		if (!foundFlower) {
			speed.removeModifier(entity);
			damage.removeModifier(entity);
			armor.removeModifier(entity);
		}
	}

	@Override
	public void endTransformation() {
		speed.removeModifier(entity);
		damage.removeModifier(entity);
		armor.removeModifier(entity);
	}

}
