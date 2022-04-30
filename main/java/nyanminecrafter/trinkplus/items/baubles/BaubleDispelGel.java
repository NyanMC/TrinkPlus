package nyanminecrafter.trinkplus.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.items.DispelGel;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.DispelGelSettings;
import xzeroair.trinkets.util.compat.baubles.BaubleHelper;

public class BaubleDispelGel extends DispelGel implements IBauble {
	
	private DispelGelSettings config = ModConfig.SERVER.GEL;

	public BaubleDispelGel(String name) {
		super(name);
		ModItems.Baubles.BAUBLES_ITEMS.add(this);
	}

	@Override
	public baubles.api.BaubleType getBaubleType(ItemStack itemstack) {
		// TODO Auto-generated method stub
		return BaubleHelper.getBaubleType(config.compat.baubles.bauble_type);
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			return super.playerCanEquip(itemstack, player);
		}
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			return super.playerCanUnequip(itemstack, player);
		}
		return true;
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			super.playerEquipped(stack, player);
		}
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			super.playerUnequipped(stack, player);
		}
	}
}
