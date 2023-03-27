package nyanminecrafter.trinkplus.util.interfaces;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import xzeroair.trinkets.api.TrinketHelper;
import xzeroair.trinkets.api.TrinketHelper.SlotInformation;

public interface ISuperTrinket {
	
	default boolean canEquipTrinket(ItemStack stack, EntityLivingBase entity) {
		SlotInformation info = TrinketHelper.getSlotInfoForItemFromAccessory(entity, (comparedStack) ->{
			if(!comparedStack.isEmpty() && comparedStack.getItem() instanceof ISuperTrinket){
	            return true;
	        } else {
	            return false;
	        }
		});
	    if(info != null){
	        return false;
	    }
	   return true;
	}
	
	default EnumRarity getSuperRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}
	
}
