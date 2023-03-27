package nyanminecrafter.trinkplus.items.supertrinkets;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.items.ShadowTreads;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityShadowStep;
import nyanminecrafter.trinkplus.traits.abilities.AbilityShiningSmash;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperShadowTreadsSettings;
import nyanminecrafter.trinkplus.util.helpers.AttributeTooltipHelper;
import nyanminecrafter.trinkplus.util.interfaces.ISuperTrinket;
import xzeroair.trinkets.api.TrinketHelper;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;
import xzeroair.trinkets.util.helpers.TranslationHelper;
import xzeroair.trinkets.util.helpers.TranslationHelper.KeyEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.OptionEntry;

public class SuperShadowTreads extends BaseAccessory implements ISuperTrinket {
	
	private SuperShadowTreadsSettings superConfig = ModConfig.SERVER.S_TREADS;

	public SuperShadowTreads(String name) {
		super(name);
		this.setUUID("aef2ce7b-3571-4528-b5f8-997a38d740da");
		this.setAttributeConfig(superConfig.attributes);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected String customItemInformation(ItemStack stack, World world, ITooltipFlag flagIn, int index, String translation) {
		final TranslationHelper helper = TranslationHelper.INSTANCE;
		final KeyEntry lightpower = new OptionEntry("lightpower", AttributeTooltipHelper.modifierToString(superConfig.lightpower, superConfig.lightpoweroperation));
		return helper.formatAddVariables(translation, lightpower);
	}

	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilityShadowStep());
		abilities.add(new AbilityShiningSmash());
	}

	@Override
	public boolean canEquipAccessory(ItemStack stack, EntityLivingBase player) {
		return this.canEquipTrinket(stack, player);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return this.getSuperRarity(stack);
	}
	
	@Override
	public void eventPlayerTick(ItemStack stack, EntityPlayer player) {
		super.eventPlayerTick(stack, player);
		if (!player.world.isRemote) {
			TrinketHelper.applyToAccessories(player, (s)->{
	            if(s.getItem() instanceof ShadowTreads) {
	                final EntityItem ei = new EntityItem(
	                		player.world,
	                		player.posX, player.posY + player.getEyeHeight(), player.posZ,
	                        s.copy()
	                );
	                ei.setPickupDelay(40);
	                s.setCount(0);
	                player.world.spawnEntity(ei);
	            }
	        });
		}
	}

}
