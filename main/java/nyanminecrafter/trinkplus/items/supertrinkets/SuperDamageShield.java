package nyanminecrafter.trinkplus.items.supertrinkets;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityReturnToSender;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperDamageShieldSettings;
import nyanminecrafter.trinkplus.util.interfaces.ISuperTrinket;
import xzeroair.trinkets.api.TrinketHelper;
import xzeroair.trinkets.items.trinkets.TrinketDamageShield;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;

public class SuperDamageShield extends BaseAccessory implements ISuperTrinket {

	private SuperDamageShieldSettings superConfig = ModConfig.SERVER.S_SOH;

	public SuperDamageShield(String name) {
		super(name);
		this.setUUID("7ccc1c74-634f-48fe-8568-c31875b78e9b");
		this.setAttributeConfig(superConfig.attributes);
	}

	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilityReturnToSender());
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
	            if(s.getItem() instanceof TrinketDamageShield) {
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
