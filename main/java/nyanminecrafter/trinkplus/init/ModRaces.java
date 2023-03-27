package nyanminecrafter.trinkplus.init;

import net.minecraft.util.ResourceLocation;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.races.ModRace;

public class ModRaces { 

	public static final ModRace solarborn;
	public static final ModRace azalean;

	private static ModRace getRegisteredRace(String name) {
		final ModRace race = (ModRace) ModRace.Registry.getValue(new ResourceLocation(Reference.MODID, name));

		if (race == null) {
			throw new IllegalStateException("Invalid Race requested: " + name);
		} else {
			return race;
		}
	}

	static {
		solarborn = getRegisteredRace("solarborn");
		azalean = getRegisteredRace("azalean");
	}

}
