package nyanminecrafter.trinkplus.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityShadowStep;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.ShadowTreadsSettings;
import nyanminecrafter.trinkplus.util.helpers.AttributeTooltipHelper;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;
import xzeroair.trinkets.util.helpers.TranslationHelper;
import xzeroair.trinkets.util.helpers.TranslationHelper.KeyEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.OptionEntry;

public class ShadowTreads extends BaseAccessory {
	
	private ShadowTreadsSettings config = ModConfig.SERVER.TREADS;
	
	public ShadowTreads(String name) {
		super(name);
		this.setUUID("45df454d-6722-49fb-a8c3-21393f7774c0");
		this.setAttributeConfig(config.attributes);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected String customItemInformation(ItemStack stack, World world, ITooltipFlag flagIn, int index, String translation) {
		final TranslationHelper helper = TranslationHelper.INSTANCE;
		final KeyEntry darkspeed = new OptionEntry("darkspeed", AttributeTooltipHelper.modifierToString(config.darkspeed, config.darkspeedoperation));
		return helper.formatAddVariables(translation, darkspeed);
	}
	
	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilityShadowStep());
	} 
	
}
