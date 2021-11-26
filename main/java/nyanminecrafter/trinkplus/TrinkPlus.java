package nyanminecrafter.trinkplus;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nyanminecrafter.trinkplus.proxy.CommonProxy;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfigEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class TrinkPlus {
	
	public static File directory;
	
	public static final Logger log = LogManager.getLogger(Reference.MODID.toUpperCase());
	
	@Instance(value = "trinkplus")
	public static TrinkPlus instance;
	
	public static Configuration config;
	
	@SidedProxy(clientSide = "nyanminecrafter.trinkplus.proxy.ClientProxy", serverSide = "nyanminecrafter.trinkplus.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), Reference.configPath + ".cfg"));
        ModConfig.readConfig();
        
        proxy.preInit(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		
		MinecraftForge.EVENT_BUS.register(ModConfigEvent.instance);
		if (config.hasChanged()) {
            config.save();
        }
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.Init(event);
	}

}
