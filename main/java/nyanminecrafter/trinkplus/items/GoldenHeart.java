package nyanminecrafter.trinkplus.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityAbsorption;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.GoldenHeartSettings;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;
import xzeroair.trinkets.util.helpers.TranslationHelper;
import xzeroair.trinkets.util.helpers.TranslationHelper.KeyEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.OptionEntry;

public class GoldenHeart extends BaseAccessory {
	
	private GoldenHeartSettings config = ModConfig.SERVER.HEART;
	
	public GoldenHeart(String name) {
		super(name);
		this.setUUID("952e911a-27d1-457b-9df3-2bdca8f0c80b");
		this.setAttributeConfig(config.attributes);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected String customItemInformation(ItemStack stack, World world, ITooltipFlag flagIn, int index, String translation) {
		final TranslationHelper helper = TranslationHelper.INSTANCE;
		final KeyEntry potency = new OptionEntry("potency", config.potency);
		final KeyEntry recharge = new OptionEntry("recharge", config.recharge);
		return helper.formatAddVariables(translation, potency, recharge);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		final ModelResourceLocation normal = new ModelResourceLocation(this.getRegistryName().toString(), "inventory");
		final ModelResourceLocation nyans = new ModelResourceLocation(this.getRegistryName().toString() + "_nyans", "inventory");
		ModelBakery.registerItemVariants(this, normal, nyans);
		ModelLoader.setCustomMeshDefinition(this, stack -> {
			if (GoldenHeart.this.getTagCompoundSafe(stack).getString("crafter.id").equalsIgnoreCase("854adc0b-ae55-48d6-b7ba-e641a1eebf42")) {
				return nyans;
			} else {
				return normal;
			}
		});
	}
	
	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		abilities.add(new AbilityAbsorption());
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
