package nyanminecrafter.trinkplus.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.entity.Alphaman;

public class ModEntities {

	public static void registerEntities() {
		registerEntity("Alphaman", Alphaman.class, 0, 50);
	}

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(
				new ResourceLocation(Reference.MODID + ":" + name), entity,
				name, id, TrinkPlus.instance, range, 1, true, color1, color2
		);
	}

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range) {
		EntityRegistry.registerModEntity(
				new ResourceLocation(Reference.MODID + ":" + name), entity,
				name, id, TrinkPlus.instance, range, 1, true
		);
	}
}
