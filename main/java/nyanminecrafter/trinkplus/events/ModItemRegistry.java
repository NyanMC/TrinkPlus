package nyanminecrafter.trinkplus.events;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.items.GoldenHeart;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;

@EventBusSubscriber
public class ModItemRegistry {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
		if (Loader.isModLoaded("baubles")) {
			event.getRegistry().registerAll(ModItems.Baubles.BAUBLES_ITEMS.toArray(new Item[0]));
		} else {
			event.getRegistry().registerAll(ModItems.TRINKETS_ITEMS.toArray(new Item[0]));
		}
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (final Item item : ModItems.ITEMS) {
			if (item instanceof IsModelLoaded) {
				((IsModelLoaded) item).registerModels();
			}
		}
		if (Loader.isModLoaded("baubles")) {
			for (final Item item : ModItems.Baubles.BAUBLES_ITEMS) {
				if (item instanceof IsModelLoaded) {
					((IsModelLoaded) item).registerModels();
				}
			}
		} else {
			for (final Item item : ModItems.TRINKETS_ITEMS) {
				if (item instanceof IsModelLoaded) {
					((IsModelLoaded) item).registerModels();
				}
			}
		}
		/*
		for (final Item item : ModItems.ITEMS) {
            if (item instanceof GoldenHeart) {
                ((GoldenHeart) item).registerTextures();
            }
        }
        */
	}
}
