package nyanminecrafter.trinkplus.items.baubles;

import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.items.supertrinkets.SuperDispelGel;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperDispelGelSettings;
import xzeroair.trinkets.util.compat.baubles.BaublesHelper;

public class BaubleSuperDispelGel extends SuperDispelGel implements IBauble {
	private SuperDispelGelSettings config = ModConfig.SERVER.S_GEL;
	
	public BaubleSuperDispelGel(String name) {
		super(name);
		ModItems.Baubles.BAUBLES_ITEMS.add(this);
	}
	
	@Override
	public baubles.api.BaubleType getBaubleType(ItemStack itemstack) {
		return BaublesHelper.getBaubleType(config.compat.baubles.bauble_type);
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			return super.canEquipAccessory(itemstack, player);
		}
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			return super.canUnequipAccessory(itemstack, player);
		}
		return true;
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			super.onAccessoryEquipped(stack, player);
		}
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			super.onAccessoryUnequipped(stack, player);
		}
	}
}
