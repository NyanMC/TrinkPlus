package nyanminecrafter.trinkplus.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.GoldenHeartSettings;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import xzeroair.trinkets.items.base.AccessoryBase;

public class GoldenHeart extends AccessoryBase implements IsModelLoaded {
	
	private GoldenHeartSettings config = ModConfig.SERVER.HEART;
	
	public GoldenHeart(String name) {
		super(name);
		this.setUUID("952e911a-27d1-457b-9df3-2bdca8f0c80b");
		setItemAttributes(config.Attributes);
		ModItems.TRINKETS_ITEMS.add(this);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public void eventPlayerTick(ItemStack stack, EntityPlayer player) {
		super.eventPlayerTick(stack, player);
		if(player.ticksExisted%config.recharge==0) {
			player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, config.recharge, config.potency, false, false));
//			System.out.println("TRINKPLUS DEBUG: Attempting to reapply player absorption");
		}
	}
}
