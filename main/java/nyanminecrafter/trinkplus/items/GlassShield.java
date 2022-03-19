package nyanminecrafter.trinkplus.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.GlassShieldSettings;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import xzeroair.trinkets.items.base.AccessoryBase;
import xzeroair.trinkets.util.handlers.TickHandler;
import xzeroair.trinkets.api.TrinketHelper;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.capabilities.Trinket.TrinketProperties;
import xzeroair.trinkets.events.TrinketEventHandler;

public class GlassShield extends AccessoryBase implements IsModelLoaded {
	
	private GlassShieldSettings config = ModConfig.SERVER.SHIELD;
	
	public GlassShield(String name) {
		super(name);
		this.setUUID("2fd9e7fd-c2d7-458d-af5c-19700b17c953");
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
		if(!player.world.isRemote) {
		final TrinketProperties prop = xzeroair.trinkets.capabilities.Capabilities.getTrinketProperties(stack);
        	if (prop != null) {
        		final TickHandler ticker = prop.getCounter("cooldown", false);
        		if ((ticker != null && ticker.Tick())) {
        			prop.removeCounter("cooldown");
//            		System.out.println("TRINKPLUS DEBUG: Glass shield cooldown finished");
        			player.world.playSound((EntityPlayer)null, player.getPosition(), SoundEvents.BLOCK_GLASS_PLACE, SoundCategory.PLAYERS, 0.5F, 1F);
        			final TextComponentString message = new TextComponentString(TextFormatting.BOLD + "" + TextFormatting.GOLD + "Your Glass Shield has recharged.");
        			player.sendStatusMessage(message, true);
        		}
        	}
		}
	}
	
	@Override
	public void eventPlayerHurt(LivingHurtEvent event, ItemStack stack, EntityLivingBase player) {
		if (event.getSource().canHarmInCreative()) {
			return;
		}
		final TrinketProperties prop = xzeroair.trinkets.capabilities.Capabilities.getTrinketProperties(stack);
        if (prop != null) {
            final TickHandler ticker = prop.getCounter("cooldown", false);
            if ((ticker == null)) {
            	if (event.getAmount() > 0) {
            		prop.getCounter("cooldown", config.cooldown, true, true);
                	event.setAmount(0);
                	event.getEntityLiving().world.playSound((EntityPlayer)null, player.getPosition(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 0.5F, 1F);
            	}
            } else {
//            	System.out.println("TRINKPLUS DEBUG: Glass shield attempted to block a shot but still has a cooldown of " + ticker.getTick());
            }
        }
	}
}
