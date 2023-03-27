package nyanminecrafter.trinkplus.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nyanminecrafter.trinkplus.events.EventHandler;
import nyanminecrafter.trinkplus.init.ModBlocks;
import nyanminecrafter.trinkplus.init.ModEntities;
import nyanminecrafter.trinkplus.races.ModRace;
import nyanminecrafter.trinkplus.util.compat.OreDictionaryCompat;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ModRace.registerRaces();
		ModBlocks.registerBlocks();
		ModEntities.registerEntities();
	}

	public void Init(FMLInitializationEvent event) {
		OreDictionaryCompat.registerOres();
		MinecraftForge.EVENT_BUS.register(EventHandler.instance);
	}

	public void postInit(FMLPostInitializationEvent event) {

	}

	public void registerItemRenderer(Item item, int meta, String id) {
	}

}
