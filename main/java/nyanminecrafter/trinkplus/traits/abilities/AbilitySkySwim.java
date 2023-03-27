package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.HarpyFeatherSettings;
import xzeroair.trinkets.traits.abilities.Ability;
import xzeroair.trinkets.traits.abilities.interfaces.IMiningAbility;
import xzeroair.trinkets.traits.abilities.interfaces.ITickableAbility;

public class AbilitySkySwim extends Ability implements ITickableAbility, IMiningAbility {
	
	private HarpyFeatherSettings config = ModConfig.SERVER.FEATHER;
	
	public AbilitySkySwim() {
		super(Reference.MODID, "sky_swim");
	}

	@Override
	public void tickAbility(EntityLivingBase entity) {
		//TODO REDO THIS
		//if (!((entity instanceof EntityPlayer) && !this.isCreativePlayer(entity))) {
		if (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isFlying) {
			final BlockPos head = entity.getPosition();
			final IBlockState headBlock = entity.world.getBlockState(head);
			final Block block = headBlock.getBlock();
			if (true) {
				double motion = 0.1;
				final double bouyance = ((EntityPlayer) entity).capabilities.getFlySpeed() * config.verticalSpeed;
				if (!entity.isSneaking()) {
					entity.motionY = 0f;
					if ((this.movingForward(entity, entity.getHorizontalFacing()) == true)) {
						if (((entity.motionX > motion) || (entity.motionX < -motion)) || ((entity.motionZ > motion) || (entity.motionZ < -motion))) {
							//System.out.println(MathHelper.clamp(entity.getLookVec().y / 1, -bouyance, bouyance));
							entity.motionY += MathHelper.clamp(entity.getLookVec().y / 1, -bouyance, bouyance);
						}
					}
				} else {
					if ((this.movingForward(entity, entity.getHorizontalFacing()) == false)) {
						if (!(entity.motionY > 0)) {
							entity.motionY *= 1.25;
						} else {

						}

					}
				}
			}
		}
	}

	private boolean movingForward(EntityLivingBase entity, EnumFacing facing) {
		return ((entity.getHorizontalFacing().getDirectionVec().getX() * entity.motionX) > 0) || ((entity.getHorizontalFacing().getDirectionVec().getZ() * entity.motionZ) > 0);
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("The direction of creative flight is influenced by where the player is looking");
	}

}
