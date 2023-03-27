package nyanminecrafter.trinkplus;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
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
import xzeroair.trinkets.util.config.ConfigHelper;
import xzeroair.trinkets.util.config.ConfigHelper.MPRecoveryItem;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class TrinkPlus {
	
	public static final CreativeTabs trinkplustab = new TrinkPlusTab();

	public static File directory;

	public static final Logger log = LogManager.getLogger(Reference.MODID.toUpperCase());

	@Instance(value = "trinkplus")
	public static TrinkPlus instance;

	public static Configuration config;

	@SidedProxy(clientSide = "nyanminecrafter.trinkplus.proxy.ClientProxy", serverSide = "nyanminecrafter.trinkplus.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static boolean Nyx = false;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		directory = event.getModConfigurationDirectory();
		config = new Configuration(new File(directory.getPath(), Reference.configPath + ".cfg"));
		ModConfig.readConfig();
		
		Nyx = Loader.isModLoaded("nyx");

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.Init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		Map<String, MPRecoveryItem> recoveryMap = ConfigHelper.TrinketConfigStorage.MagicRecoveryItems;
        MPRecoveryItem manaCandyIndex = recoveryMap.get("xat:mana_candy");
        double enderCandyRecovery = 0D;
        if (manaCandyIndex != null) {
        	enderCandyRecovery = manaCandyIndex.getAmount() * 2;
        } else {
        	enderCandyRecovery = 100;
        }
        String key = "trinkplus:ender_candy";
        String entry = key + ";0;" + enderCandyRecovery;
        MPRecoveryItem newEntry = new MPRecoveryItem(entry);
        recoveryMap.putIfAbsent(key, newEntry);
		MinecraftForge.EVENT_BUS.register(ModConfigEvent.instance);
		if (config.hasChanged()) {
			config.save();
		}
	}

}
