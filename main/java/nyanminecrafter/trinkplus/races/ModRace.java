package nyanminecrafter.trinkplus.races;

import net.minecraft.entity.EntityLivingBase;
import xzeroair.trinkets.races.EntityRace;
import xzeroair.trinkets.races.EntityRacePropertiesHandler;
import xzeroair.trinkets.races.RaceAttributesWrapper;

public class ModRace extends EntityRace {

	public ModRace(String name, String uuid, int color1, int color2) {
		super(name, uuid, color1, color2);
		this.setRegistryName(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public RaceAttributesWrapper getRaceAttributes() {
		final String name = this.getName();
		switch (name) {
		case "Solarborn":
			return new RaceSolarbornAttributes();
		case "Azalean":
			return new RaceAzaleanAttributes();
		default:
			return super.getRaceAttributes();
		}
	}

	@Override
	public EntityRacePropertiesHandler getRaceHandler(EntityLivingBase e) {
		final String name = this.getName();
		switch (name) {
		case "Solarborn":
			return new RaceSolarborn(e);
		case "Azalean":
			return new RaceAzalean(e);
		default:
			return super.getRaceHandler(e);
		}
	}

	public static void registerRaces() {
		registerModRace(new ModRace("Solarborn", "270cba25-b0e4-4233-971d-4c5c951e0296", 16744448, 16776960).setRaceSize(100).setMagicAffinity(500));
		registerModRace(new ModRace("Azalean", "47999fe4-4c90-432d-8be4-1682368663ab", 13464275, 29184).setRaceSize(50).setMagicAffinity(150));
	}
	
	private static void registerModRace(EntityRace entityRace) {
		Registry.register(entityRace);
	}
	
}
