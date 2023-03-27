package nyanminecrafter.trinkplus.items.supertrinkets;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import nyanminecrafter.trinkplus.items.DispelGel;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityAntiMagic;
import nyanminecrafter.trinkplus.traits.abilities.AbilityPotionWarding;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperDispelGelSettings;
import nyanminecrafter.trinkplus.util.interfaces.ISuperTrinket;
import xzeroair.trinkets.api.TrinketHelper;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;

public class SuperDispelGel extends BaseAccessory implements ISuperTrinket {
	
	private SuperDispelGelSettings config = ModConfig.SERVER.S_GEL;

	public SuperDispelGel(String name) {
		super(name);
		this.setUUID("99236058-6733-45be-beab-7f1ac606eec8");
		this.setAttributeConfig(config.attributes);
	}
	
	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilityPotionWarding());
		abilities.add(new AbilityAntiMagic());
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
	            if(s.getItem() instanceof DispelGel) {
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
