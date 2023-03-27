package nyanminecrafter.trinkplus.items.supertrinkets;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityDeadlyStrike;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperFaelisClawsSettings;
import nyanminecrafter.trinkplus.util.helpers.AttributeTooltipHelper;
import nyanminecrafter.trinkplus.util.interfaces.ISuperTrinket;
import xzeroair.trinkets.api.TrinketHelper;
import xzeroair.trinkets.items.trinkets.TrinketFaelisClaws;
import xzeroair.trinkets.traits.abilities.AbilityViciousStrike;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;
import xzeroair.trinkets.util.TrinketsConfig;
import xzeroair.trinkets.util.config.trinkets.ConfigFaelisClaw;
import xzeroair.trinkets.util.helpers.TranslationHelper;
import xzeroair.trinkets.util.helpers.TranslationHelper.KeyEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.LangEntry;
import xzeroair.trinkets.util.helpers.TranslationHelper.OptionEntry;

public class SuperFaelisClaws extends BaseAccessory implements ISuperTrinket {

	private ConfigFaelisClaw normalConfig = TrinketsConfig.SERVER.Items.FAELIS_CLAW;
	private SuperFaelisClawsSettings superConfig = ModConfig.SERVER.S_CLAWS;

	public SuperFaelisClaws(String name) {
		super(name);
		this.setUUID("8a00a6ca-4e33-4d12-a0fe-56bf2f759434");
		this.setAttributeConfig(superConfig.attributes);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected String customItemInformation(ItemStack stack, World world, ITooltipFlag flagIn, int index, String translation) {
		final TranslationHelper helper = TranslationHelper.INSTANCE;
		final KeyEntry all = new LangEntry(this.getTranslationKey(stack), "all", superConfig.multiply_all_outgoing);
		final KeyEntry pml = new LangEntry(this.getTranslationKey(stack), "pml", !superConfig.multiply_all_outgoing);
		final KeyEntry outgoing = new OptionEntry("outgoing", AttributeTooltipHelper.modifierToPercentage(superConfig.multiply_outgoing-1));
		final KeyEntry incoming = new OptionEntry("incoming", AttributeTooltipHelper.modifierToPercentage(superConfig.multiply_incoming-1));
		return helper.formatAddVariables(translation, all, pml, outgoing, incoming);
	}

	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		if (normalConfig.bleed) {
			abilities.add(new AbilityViciousStrike());
		}
		abilities.add(new AbilityDeadlyStrike());
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
	            if(s.getItem() instanceof TrinketFaelisClaws) {
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
