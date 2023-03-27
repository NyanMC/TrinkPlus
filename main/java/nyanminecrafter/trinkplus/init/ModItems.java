package nyanminecrafter.trinkplus.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import nyanminecrafter.trinkplus.items.DispelGel;
import nyanminecrafter.trinkplus.items.DualityRings;
import nyanminecrafter.trinkplus.items.FriendlyFireFlower;
import nyanminecrafter.trinkplus.items.GlassShield;
import nyanminecrafter.trinkplus.items.GoldenHeart;
import nyanminecrafter.trinkplus.items.HarpyFeather;
import nyanminecrafter.trinkplus.items.LunarCrystal;
import nyanminecrafter.trinkplus.items.ShadowTreads;
import nyanminecrafter.trinkplus.items.SpicyCoal;
import nyanminecrafter.trinkplus.items.base.BaseItem;
import nyanminecrafter.trinkplus.items.base.BaseTransformationRing;
import nyanminecrafter.trinkplus.items.baubles.BaubleDispelGel;
import nyanminecrafter.trinkplus.items.baubles.BaubleDualityRings;
import nyanminecrafter.trinkplus.items.baubles.BaubleFriendlyFireFlower;
import nyanminecrafter.trinkplus.items.baubles.BaubleGlassShield;
import nyanminecrafter.trinkplus.items.baubles.BaubleGoldenHeart;
import nyanminecrafter.trinkplus.items.baubles.BaubleHarpyFeather;
import nyanminecrafter.trinkplus.items.baubles.BaubleLunarCrystal;
import nyanminecrafter.trinkplus.items.baubles.BaubleShadowTreads;
import nyanminecrafter.trinkplus.items.baubles.BaubleSuperDamageShield;
import nyanminecrafter.trinkplus.items.baubles.BaubleSuperDispelGel;
import nyanminecrafter.trinkplus.items.baubles.BaubleSuperFaelisClaws;
import nyanminecrafter.trinkplus.items.baubles.BaubleSuperShadowTreads;
import nyanminecrafter.trinkplus.items.baubles.BaubleSuperTeddyBear;
import nyanminecrafter.trinkplus.items.baubles.BaubleTransformationRing;
import nyanminecrafter.trinkplus.items.food.EnderCandy;
import nyanminecrafter.trinkplus.items.food.MagicGarlicBread;
import nyanminecrafter.trinkplus.items.supertrinkets.SuperDamageShield;
import nyanminecrafter.trinkplus.items.supertrinkets.SuperDispelGel;
import nyanminecrafter.trinkplus.items.supertrinkets.SuperFaelisClaws;
import nyanminecrafter.trinkplus.items.supertrinkets.SuperShadowTreads;
import nyanminecrafter.trinkplus.items.supertrinkets.SuperTeddyBear;
//import nyanminecrafter.trinkplus.items.supertrinkets.SuperViewerItem;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer;
import xzeroair.trinkets.items.base.RaceFood;

public class ModItems {
	
	private static xServer config = ModConfig.SERVER;

	public static final List<Item> ITEMS = new ArrayList<>();
	public static final List<Item> TRINKETS_ITEMS = new ArrayList<>();

	// crafting
	public static final Item enderpowder = new BaseItem("ender_powder").setMaxDamage(0);
	public static final Item enderingot = new BaseItem("ender_ingot").setMaxDamage(0);
	public static final Item endergem = new BaseItem("ender_gem").setMaxDamage(0);
	
	public static final Item chromashard = new BaseItem("chroma_shard").setMaxDamage(0);
	public static final Item ascensionessence = new BaseItem("ascension_essence").setMaxDamage(0);
	
	public static final Item spicycoal = new SpicyCoal();

	// food
	public static final Item magicgarlicbread = new MagicGarlicBread("magic_garlic_bread");
	public static final Item endercandy = new EnderCandy("ender_candy");

	// trinkets
	public static final Item goldenheart = new GoldenHeart("golden_heart");
	public static final Item glassshield = new GlassShield("glass_shield");
	public static final Item dispelgel = new DispelGel("dispel_gel");
	public static final Item shadowtreads = new ShadowTreads("shadow_treads");
	public static final Item dualityrings = new DualityRings("duality_rings");
	public static final Item friendlyfireflower = new FriendlyFireFlower("friendly_fire_flower");
	public static final Item lunarcrystal = new LunarCrystal("lunar_crystal");
	public static final Item harpyfeather = new HarpyFeather("harpy_feather");

	// super trinkets
	public static final Item superdamageshield = new SuperDamageShield("super_damage_shield");
	public static final Item superfaelisclaws = new SuperFaelisClaws("super_faelis_claw");
	public static final Item superdispelgel = new SuperDispelGel("super_dispel_gel");
	public static final Item superteddybear = new SuperTeddyBear("super_teddy_bear");
	public static final Item supershadowtreads = new SuperShadowTreads("super_shadow_treads");

	// race foods
	public static final Item solarbornstone = new RaceFood("solarborn_stone", 32, EnumAction.EAT, ModRaces.solarborn);
	public static final Item azaleanflower = new RaceFood("azalean_flower", 32, EnumAction.EAT, ModRaces.azalean);

	// race rings
	public static final Item solarbornring = new BaseTransformationRing("solarborn_ring", ModRaces.solarborn, config.SOLARBORN.attributes);
	public static final Item azaleanring = new BaseTransformationRing("azalean_ring", ModRaces.azalean, config.AZALEAN.attributes);

	// debug
	//public static final Item supervieweritem = new SuperViewerItem("super_viewer_item");

	public static class Baubles {
		public static final List<Item> BAUBLES_ITEMS = new ArrayList<>();

		// baubles
		public static final Item baublegoldenheart = new BaubleGoldenHeart("golden_heart");
		public static final Item baubleglassshield = new BaubleGlassShield("glass_shield");
		public static final Item baubledispelgel = new BaubleDispelGel("dispel_gel");
		public static final Item baubleshadowtreads = new BaubleShadowTreads("shadow_treads");
		public static final Item baubledualityrings = new BaubleDualityRings("duality_rings");
		public static final Item baublefriendlyfireflower = new BaubleFriendlyFireFlower("friendly_fire_flower");
		public static final Item baublelunarcrystal = new BaubleLunarCrystal("lunar_crystal");
		public static final Item baubleharpyfeather = new BaubleHarpyFeather("harpy_feather");

		// super baubles
		public static final Item baublesuperdamageshield = new BaubleSuperDamageShield("super_damage_shield");
		public static final Item baublesuperfaelisclaws = new BaubleSuperFaelisClaws("super_faelis_claw");
		public static final Item baublesuperdispelgel = new BaubleSuperDispelGel("super_dispel_gel");
		public static final Item baublesuperteddybear = new BaubleSuperTeddyBear("super_teddy_bear");
		public static final Item baublesupershadowtreads = new BaubleSuperShadowTreads("super_shadow_treads");

		// race rings (baubles)
		public static final Item baublesolarbornring = new BaubleTransformationRing("solarborn_ring", ModRaces.solarborn, config.SOLARBORN.attributes);
		public static final Item baubleazaleanring = new BaubleTransformationRing("azalean_ring", ModRaces.azalean, config.AZALEAN.attributes);
	}
}
