package nyanminecrafter.trinkplus.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityAceOfSpades;
import nyanminecrafter.trinkplus.traits.abilities.AbilityAroOfClubs;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.DualityRingsSettings;
import nyanminecrafter.trinkplus.util.helpers.AttributeTooltipHelper;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;
import xzeroair.trinkets.util.helpers.TranslationHelper;
import xzeroair.trinkets.util.helpers.TranslationHelper.KeyEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.LangEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.OptionEntry;

public class DualityRings extends BaseAccessory {
	
	private DualityRingsSettings config = ModConfig.SERVER.RINGS;

	public DualityRings(String name) {
		super(name);
		this.setUUID("5812cc8e-9054-4026-a199-00e67d47da00");
		this.setAttributeConfig(config.attributes);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected String customItemInformation(ItemStack stack, World world, ITooltipFlag flagIn, int index, String translation) {
		final TranslationHelper helper = TranslationHelper.INSTANCE;
		final KeyEntry all = new LangEntry(this.getTranslationKey(stack), "all", config.boostAllProjectiles);
		final KeyEntry arrow = new LangEntry(this.getTranslationKey(stack), "arrow", !config.boostAllProjectiles);
		final KeyEntry amplifier = new OptionEntry("amplifier", AttributeTooltipHelper.modifierToPercentage(config.arrowAmplifier-1));
		return helper.formatAddVariables(translation, all, arrow, amplifier);
	}
	
	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilityAceOfSpades());
		abilities.add(new AbilityAroOfClubs());
	}
	
}
