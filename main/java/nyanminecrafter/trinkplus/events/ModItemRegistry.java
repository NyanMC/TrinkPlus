package nyanminecrafter.trinkplus.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
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
import net.minecraftforge.oredict.OreDictionary;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModBlocks;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.init.ModRaces;
import nyanminecrafter.trinkplus.items.base.BasePotion;
import nyanminecrafter.trinkplus.items.potions.PotionManaBleed;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import xzeroair.trinkets.attributes.MagicAttributes;
import xzeroair.trinkets.init.ModPotionTypes;
import xzeroair.trinkets.items.potions.PotionObject;
import xzeroair.trinkets.items.potions.TransformationPotion;
import xzeroair.trinkets.races.EntityRace;
import xzeroair.trinkets.util.TrinketsConfig;
import xzeroair.trinkets.util.helpers.StringUtils;

@EventBusSubscriber
public class ModItemRegistry {

	private static xServer config = ModConfig.SERVER;

	public static Map<String, PotionObject> myPotions = new TreeMap<>();

	public static void register() {
		final PotionObject MPRegen = createPotion(
				"manaregen",
				3600,
				5243135,
				false,
				-1, -1,
				new ResourceLocation(Reference.MODID, "textures/potions/manaregen.png"),
				ModPotionTypes.TrinketPotionObjects.get("sparkling").getPotionType(),
				Ingredient.fromItem(xzeroair.trinkets.init.ModItems.foods.mana_candy),
				MagicAttributes.regen,
				"551a6737-9b2a-443d-8622-b3f3de3459e1",
				0.5,
				2
		);
		myPotions.putIfAbsent("manaregen", MPRegen.registerWithPotion());

		//        final PotionObject MPBoost = createPotion(
		//                "manaboost",
		//                255,
		//                1, 0,
		//                3600,
		//                ModPotionTypes.TrinketPotionObjects.get("sparkling").getPotionType(),
		//                Ingredient.fromItem(xzeroair.trinkets.init.ModItems.foods.mana_crystal),
		//                0,
		//                MagicAttributes.affinity,
		//                "81fbfb7a-e88d-48fa-9033-4812536ce20a",
		//                0.25,
		//                2
		//        );
		//        myPotions.putIfAbsent("manaboost", MPBoost.registerWithPotion());

		final Potion manaBleedPotion = new PotionManaBleed("manableed", 600, 3091024);
		final PotionObject MPBleed = createPotionObjectFromPotion(manaBleedPotion, 600, ModPotionTypes.TrinketPotionObjects.get("sparkling").getPotionType(), getCatalyst("minecraft:fermented_spider_eye"), "manableed");
		myPotions.putIfAbsent("manableed", MPBleed.registerWithPotion());

		// new Potions

		final PotionObject SolarbornPotion = createRacePotion(
				ModRaces.solarborn,
				config.SOLARBORN.potionDuration,
				ModPotionTypes.TrinketPotionObjects.get("glowing").getPotionType(),
				getCatalyst("trinkplus:spicy_coal")
		);
		myPotions.putIfAbsent("solarborn", SolarbornPotion.registerWithPotion());

		final PotionObject AzaleanPotion = createRacePotion(
				ModRaces.azalean,
				config.AZALEAN.potionDuration,
				ModPotionTypes.TrinketPotionObjects.get("glittering").getPotionType(),
				getCatalyst("minecraft:red_flower:0") // note: replace this with flowering azalea for modern ports
		);
		myPotions.putIfAbsent("azalean", AzaleanPotion.registerWithPotion());
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
		event.getRegistry().register(ModItems.solarbornstone);
		event.getRegistry().register(ModItems.azaleanflower);
		if (Loader.isModLoaded("baubles") && config.baublesCompat) {
			event.getRegistry().registerAll(ModItems.Baubles.BAUBLES_ITEMS.toArray(new Item[0]));
		} else {
			event.getRegistry().registerAll(ModItems.TRINKETS_ITEMS.toArray(new Item[0]));
		}
	}

	public static PotionObject createPotion(String name, int duration, int color, boolean isBadEffect, PotionType base, Ingredient ingredient) {
		return createPotion(name, duration, color, isBadEffect, base, ingredient, -1, -1, null);
	}

	public static PotionObject createPotion(String name, int duration, int color, boolean isBadEffect, PotionType base, Ingredient ingredient, int iconX, int iconY, ResourceLocation texture) {
		final Potion potion = new BasePotion(name, duration, color, isBadEffect, iconX, iconY, texture);
		final PotionObject potObj = new PotionObject(potion, base, Reference.MODID, name, color, duration, ingredient);
		return potObj;
	}

	public static PotionObject createPotion(String name, int duration, int color, boolean isBadEffect, PotionType base, Ingredient ingredient, IAttribute attribute, String uuid, double amount, int operation) {
		return createPotion(name, duration, color, isBadEffect, -1, -1, null, base, ingredient, attribute, uuid, amount, operation);
	}

	public static PotionObject createPotion(String name, int duration, int color, boolean isBadEffect, int X, int Y, ResourceLocation texture, PotionType base, Ingredient ingredient, IAttribute attribute, String uuid, double amount, int operation) {
		final Potion potion = new BasePotion(name, duration, color, isBadEffect, X, Y, texture, attribute, uuid, amount, operation);
		final PotionObject potObj = new PotionObject(potion, base, Reference.MODID, name, color, duration, ingredient);
		return potObj;
	}

	public static PotionObject createRacePotion(EntityRace race, int duration, PotionType base, Ingredient ingredient) {
		String name = race.getName().toLowerCase();
		final Potion potion = new TransformationPotion(Reference.MODID, name, race.getPrimaryColor(), duration, race.getUUID().toString(), false, -1, -1, new ResourceLocation(Reference.MODID, "textures/potions/" + name + ".png"));
		final PotionObject potObj = new PotionObject(potion, base, Reference.MODID, name, race.getPrimaryColor(), duration, ingredient);
		return potObj;
	}

	public static PotionObject createPotionObjectFromPotion(Potion potion, int duration, PotionType base, Ingredient ingredient, String name) {
		final PotionObject potObj = new PotionObject(potion, base, Reference.MODID, name, potion.getLiquidColor(), duration, ingredient);
		return potObj;
	}

	@SubscribeEvent
	public static void onPotionRegister(RegistryEvent.Register<PotionType> event) {
		if (TrinketsConfig.SERVER.Potion.potions_enabled) {
			register();
		}
	}

	private static Ingredient getCatalyst(String catalyst) {
		final String[] itemConfig = catalyst.replace(";", ":").split(":");
		final String modIDString = StringUtils.getStringFromArray(itemConfig, 0);
		final String itemIDString = StringUtils.getStringFromArray(itemConfig, 1);
		final String metaString = StringUtils.getStringFromArray(itemConfig, 2).replaceAll("[^\\d]", "");
		//		return Item.getByNameOrId(modIDString + ":" + itemIDString);
		int meta = OreDictionary.WILDCARD_VALUE;
		try {
			if (!metaString.isEmpty()) {
				meta = Integer.parseInt(metaString);
			}
		} catch (final Exception e) {
			TrinkPlus.log.warn("Invalid catalyst meta from | " + catalyst);
		}
		return Ingredient.fromStacks(new ItemStack(Item.getByNameOrId(modIDString + ":" + itemIDString), 1, meta));
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (final Block block : ModBlocks.BLOCKS) {
			TrinkPlus.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
		}
		for (final Item item : ModItems.ITEMS) {
			if (item instanceof IsModelLoaded) {
				((IsModelLoaded) item).registerModels();
			}
		}
		TrinkPlus.proxy.registerItemRenderer(ModItems.solarbornstone, 0, "inventory");
		TrinkPlus.proxy.registerItemRenderer(ModItems.azaleanflower, 0, "inventory");
		if (Loader.isModLoaded("baubles") && config.baublesCompat) {
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
	}

	@SubscribeEvent
	public static void onLootTablesLoaded(LootTableLoadEvent event) {
		String eventName = event.getName().toString();
		if (eventName.equals("minecraft:chests/end_city_treasure")) {
			List<LootEntry> entries = new ArrayList<>();
			entries.add(new LootEntryItem(ModItems.magicgarlicbread, 5, 0, new LootFunction[0], new LootCondition[0], ModItems.magicgarlicbread.getRegistryName().toString()));
			LootPool pool = new LootPool(entries.toArray(new LootEntry[0]), new LootCondition[] { new RandomChance((float) 0.35D) }, new RandomValueRange(1), new RandomValueRange(0), Reference.MODID + "_endcity");
			event.getTable().addPool(pool);
		} else if (eventName.equals("minecraft:chests/nether_bridge")) {
			List<LootEntry> entries = new ArrayList<>();
			entries.add(new LootEntryItem(ModItems.spicycoal, 5, 0, new LootFunction[0], new LootCondition[0], ModItems.spicycoal.getRegistryName().toString()));
			LootPool pool = new LootPool(entries.toArray(new LootEntry[0]), new LootCondition[] { new RandomChance((float) 0.35D) }, new RandomValueRange(1), new RandomValueRange(0), Reference.MODID + "_fortress");
			event.getTable().addPool(pool);
		}
	}
}