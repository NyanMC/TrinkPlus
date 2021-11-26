package nyanminecrafter.trinkplus.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.DispelGelSettings;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import xzeroair.trinkets.items.base.AccessoryBase;

public class DispelGel extends AccessoryBase implements IsModelLoaded {
	
	private DispelGelSettings config = ModConfig.SERVER.GEL;
	
	public DispelGel(String name) {
		super(name);
		ModItems.TRINKETS_ITEMS.add(this);
		this.setUUID("172316fe-ed83-441f-871a-024f8b395e08");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public void eventPlayerHurt(LivingHurtEvent event, ItemStack stack, EntityLivingBase player) {
		if (!(event.getSource().canHarmInCreative()) && (event.getSource().isMagicDamage())) {
			event.setAmount(event.getAmount() * config.multiply);
		}
	}
	
	@Override
	public void eventLivingHurt(LivingHurtEvent event, ItemStack stack, EntityLivingBase player) {
		if (!(event.getSource().canHarmInCreative()) && (event.getSource().isMagicDamage())) {
			event.setAmount(event.getAmount() * config.multiply);
		}
	}
}
