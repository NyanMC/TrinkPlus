package nyanminecrafter.trinkplus.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityLevitationAffinity;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.LunarCrystalSettings;
import nyanminecrafter.trinkplus.util.helpers.AttributeTooltipHelper;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;
import xzeroair.trinkets.util.helpers.TranslationHelper;
import xzeroair.trinkets.util.helpers.TranslationHelper.KeyEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.LangEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.OptionEntry;

public class LunarCrystal extends BaseAccessory {
	
	private LunarCrystalSettings config = ModConfig.SERVER.CRYSTAL;
	
	public LunarCrystal(String name) {
		super(name);
		this.setUUID("d574f167-9e3a-433a-9476-6dfa6ab60713");
		this.setAttributeConfig(config.attributes);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected String customItemInformation(ItemStack stack, World world, ITooltipFlag flagIn, int index, String translation) {
		final TranslationHelper helper = TranslationHelper.INSTANCE;
		final KeyEntry fall = new LangEntry(this.getTranslationKey(stack), "fall", config.ignoreFallDamage);
		final KeyEntry chance = new OptionEntry("chance", AttributeTooltipHelper.modifierToPercentage(1F/config.levitationChance));
		final KeyEntry duration = new OptionEntry("duration", config.levitationDuration);
		final KeyEntry amplifier = new OptionEntry("amplifier", config.levitationAmplifier);
		return helper.formatAddVariables(translation, fall, chance, duration, amplifier);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String crafter = "";
		if (this.getTagCompoundSafe(stack).getString("crafter.id").equalsIgnoreCase("854adc0b-ae55-48d6-b7ba-e641a1eebf42")) {
			return "Luna's Crystal";
		}
		return super.getItemStackDisplayName(stack);
	}
	
	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilityLevitationAffinity()); 
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
		if (this.getTagCompoundSafe(stack).getString("crafter.id").isEmpty()) {
			if (entity instanceof EntityPlayer) {
				if (((EntityPlayer) entity).getUniqueID() != null) {
					this.getTagCompoundSafe(stack).setString("crafter.id", ((EntityPlayer) entity).getUniqueID().toString());
				}
			}
		} else {
			final EntityPlayer player = entity.world.getPlayerEntityByUUID(UUID.fromString(this.getTagCompoundSafe(stack).getString("crafter.id")));
			if ((player != null) && this.getTagCompoundSafe(stack).getString("crafter.name").isEmpty()) {
				this.getTagCompoundSafe(stack).setString("crafter.name", player.getDisplayNameString());
			}
		}
	}
	
}
