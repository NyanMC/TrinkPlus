package nyanminecrafter.trinkplus.items;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilitySkySwim;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.HarpyFeatherSettings;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;

public class HarpyFeather extends BaseAccessory {

	private HarpyFeatherSettings config = ModConfig.SERVER.FEATHER;

	public HarpyFeather(String name) {
		super(name);
		this.setUUID("c15c19b8-d6fa-4e85-8698-eacbc1db8a2a");
		this.setAttributeConfig(config.attributes);
	}

	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilitySkySwim());
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		player.getCooldownTracker().setCooldown(this, config.cooldown);
		if (!world.isRemote) {
			player.fallDistance = 0;
			player.getEntityWorld().playSound((EntityPlayer) null, player.getPosition(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.8F, 5F);
		}
		if (player.getLowestRidingEntity() != null && config.worksOnMounts) {
			player.getLowestRidingEntity().motionY = config.launchForce;
		} else {
			player.motionY = config.launchForce;
		}
		return super.onItemRightClick(world, player, hand);
	}

}
