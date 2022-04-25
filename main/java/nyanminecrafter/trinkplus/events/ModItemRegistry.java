package nyanminecrafter.trinkplus.events;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.items.GoldenHeart;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import nyanminecrafter.trinkplus.items.base.BasePotion;
import xzeroair.trinkets.init.ModPotionTypes;
import xzeroair.trinkets.items.potions.PotionObject;
import xzeroair.trinkets.util.TrinketsConfig;

@EventBusSubscriber
public class ModItemRegistry {
	
	public static PotionObject myPotion;

	public static void register(){
		myPotion = createPotion("manaregen", 5243135, 0, 0, 3600, ModPotionTypes.TrinketPotionObjects.get("sparkling").getPotionType(), Ingredient.fromItem(xzeroair.trinkets.init.ModItems.foods.mana_candy), 0);
	    myPotion.registerPotion();
	}
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
		if (Loader.isModLoaded("baubles")) {
			event.getRegistry().registerAll(ModItems.Baubles.BAUBLES_ITEMS.toArray(new Item[0]));
		} else {
			event.getRegistry().registerAll(ModItems.TRINKETS_ITEMS.toArray(new Item[0]));
		}
	}
	
	public static PotionObject createPotion(String name, int color, int X, int Y, int duration, PotionType base, Ingredient ingredient, int level) {
		final Potion potion = new BasePotion(name, duration, color, X, Y, level);
		final PotionObject potObj = new PotionObject(potion, base, name, color, duration, ingredient);
		return potObj;
	}
	
	@SubscribeEvent
	public static void onPotionRegister(RegistryEvent.Register<PotionType> event) {
		if (TrinketsConfig.SERVER.Potion.potions_enabled) {
			register();
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
	
	@SubscribeEvent
	public static void onLootTablesLoaded(LootTableLoadEvent event) {
		String eventName = event.getName().toString();
		if (eventName.equals("minecraft:chests/end_city_treasure")) {
			List<LootEntry> entries = new ArrayList<>();
			entries.add(new LootEntryItem(ModItems.magicgarlicbread, 5, 0, new LootFunction[0], new LootCondition[0], ModItems.magicgarlicbread.getRegistryName().toString()));
			LootPool pool = new LootPool(entries.toArray(new LootEntry[0]), new LootCondition[] { new RandomChance((float) 0.35D) }, new RandomValueRange(1), new RandomValueRange(0), Reference.MODID+"_endcity");
			event.getTable().addPool(pool);
		}
	}
}
