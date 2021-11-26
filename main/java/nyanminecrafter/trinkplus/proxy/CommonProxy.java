package nyanminecrafter.trinkplus.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nyanminecrafter.trinkplus.events.EventHandler;
import nyanminecrafter.trinkplus.util.compat.OreDictionaryCompat;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {

	}
	
	public void Init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(EventHandler.instance);
		OreDictionaryCompat.registerOres();
	}
	
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	public void registerItemRenderer(Item item, int meta, String id) {
	}

}
