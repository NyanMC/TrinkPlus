package nyanminecrafter.trinkplus.util.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nyanminecrafter.trinkplus.Reference;

public class ModConfigEvent {
	
	public static ModConfigEvent instance = new ModConfigEvent();

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Reference.MODID)) {
			ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
		}
	}
	
}
