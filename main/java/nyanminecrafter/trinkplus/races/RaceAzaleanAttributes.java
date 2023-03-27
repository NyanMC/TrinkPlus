package nyanminecrafter.trinkplus.races;

import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.AzaleanSettings;
import xzeroair.trinkets.races.RaceAttributesWrapper;

public class RaceAzaleanAttributes extends RaceAttributesWrapper {

	public static final AzaleanSettings config = ModConfig.SERVER.AZALEAN;

	public RaceAzaleanAttributes() {
		attributes = config.attributes;
	}

}
