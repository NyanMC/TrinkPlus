package nyanminecrafter.trinkplus.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityAntiMagic;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.DispelGelSettings;
import nyanminecrafter.trinkplus.util.helpers.AttributeTooltipHelper;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;
import xzeroair.trinkets.util.helpers.TranslationHelper;
import xzeroair.trinkets.util.helpers.TranslationHelper.KeyEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.OptionEntry;

public class DispelGel extends BaseAccessory {
	
	private DispelGelSettings config = ModConfig.SERVER.GEL;
	
	public DispelGel(String name) {
		super(name);
		this.setUUID("172316fe-ed83-441f-871a-024f8b395e08");
		this.setAttributeConfig(config.attributes);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected String customItemInformation(ItemStack stack, World world, ITooltipFlag flagIn, int index, String translation) {
		final TranslationHelper helper = TranslationHelper.INSTANCE;
		final KeyEntry multiply_incoming = new OptionEntry("multiply_incoming", AttributeTooltipHelper.modifierToPercentage(1-config.multiply_incoming));
		final KeyEntry multiply_outgoing = new OptionEntry("multiply_outgoing", AttributeTooltipHelper.modifierToPercentage(1-config.multiply_outgoing));
		return helper.formatAddVariables(translation, multiply_incoming, multiply_outgoing);
	}
	
	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilityAntiMagic());
	}
	
}
