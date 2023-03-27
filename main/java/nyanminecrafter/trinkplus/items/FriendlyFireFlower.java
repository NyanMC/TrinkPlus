package nyanminecrafter.trinkplus.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityFriendlyFiring;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.FriendlyFireFlowerSettings;
import nyanminecrafter.trinkplus.util.helpers.AttributeTooltipHelper;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;
import xzeroair.trinkets.util.helpers.TranslationHelper;
import xzeroair.trinkets.util.helpers.TranslationHelper.KeyEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.LangEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.OptionEntry;

public class FriendlyFireFlower extends BaseAccessory {
	
	private FriendlyFireFlowerSettings config = ModConfig.SERVER.FLOWER;

	public FriendlyFireFlower(String name) {
		super(name);
		this.setUUID("8ce6137b-c397-4ada-b153-c1d71aa2d96b");
		this.setAttributeConfig(config.attributes);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected String customItemInformation(ItemStack stack, World world, ITooltipFlag flagIn, int index, String translation) {
		final TranslationHelper helper = TranslationHelper.INSTANCE;
		final KeyEntry perhit = new LangEntry(this.getTranslationKey(stack), "perhit", config.isFlatCost);
		final KeyEntry perdamage = new LangEntry(this.getTranslationKey(stack), "perdamage", !config.isFlatCost);
		final KeyEntry reduction = new OptionEntry("reduction", AttributeTooltipHelper.modifierToPercentage(1-config.fireDamageMultiplier));
		final KeyEntry cost = new OptionEntry("cost", config.manaCost + "MP");
		return helper.formatAddVariables(translation, perhit, perdamage, reduction, cost);
	}
	
	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilityFriendlyFiring());
	}
	
}
