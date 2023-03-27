package nyanminecrafter.trinkplus.races;

import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SolarbornSettings;
import xzeroair.trinkets.races.RaceAttributesWrapper;

public class RaceSolarbornAttributes extends RaceAttributesWrapper {

	public static final SolarbornSettings serverConfig = ModConfig.SERVER.SOLARBORN;

	public RaceSolarbornAttributes() {
		//		size = 100;
		//		width = size;
		//		height = size;
		//		color1 = 12514535;
		//		color2 = 962222;
		//		color3 = color1;
		//		opacity = 1f;
		//		trait_opacity = 1F;
		attributes = serverConfig.attributes;
	}
}
