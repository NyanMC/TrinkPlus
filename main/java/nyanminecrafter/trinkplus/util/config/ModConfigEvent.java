package nyanminecrafter.trinkplus.util.config;

import java.util.Map;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nyanminecrafter.trinkplus.Reference;
import xzeroair.trinkets.util.config.ConfigHelper;
import xzeroair.trinkets.util.config.ConfigHelper.MPRecoveryItem;

public class ModConfigEvent {
	
	public static ModConfigEvent instance = new ModConfigEvent();

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Reference.MODID)) {
			ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
		}
	}
	
	@SubscribeEvent
    public void onTrinketsConfigChange(ConfigChangedEvent.PostConfigChangedEvent event) {
        if (event.getModID().equals("xat")) {
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
        }
    }
	
}
