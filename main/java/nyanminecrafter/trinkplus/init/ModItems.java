package nyanminecrafter.trinkplus.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import nyanminecrafter.trinkplus.items.DispelGel;
import nyanminecrafter.trinkplus.items.EnderIngot;
import nyanminecrafter.trinkplus.items.GlassShield;
import nyanminecrafter.trinkplus.items.GoldenHeart;
import nyanminecrafter.trinkplus.items.baubles.BaubleDispelGel;
import nyanminecrafter.trinkplus.items.baubles.BaubleGlassShield;
import nyanminecrafter.trinkplus.items.baubles.BaubleGoldenHeart;
import nyanminecrafter.trinkplus.items.food.MagicGarlicBread;

public class ModItems {
	
	public static final List<Item> ITEMS = new ArrayList<>();
	public static final List<Item> TRINKETS_ITEMS = new ArrayList<>();
	
	public static final Item enderingot = new EnderIngot("ender_ingot");
	public static final Item magicgarlicbread = new MagicGarlicBread("magic_garlic_bread");
	
	public static final Item goldenheart = new GoldenHeart("golden_heart");
	public static final Item glassshield = new GlassShield("glass_shield");
	public static final Item dispelgel = new DispelGel("dispel_gel");
	
	public static class Baubles {
		public static final List<Item> BAUBLES_ITEMS = new ArrayList<>();
		public static final Item baublegoldenheart = new BaubleGoldenHeart("golden_heart");
		public static final Item baubleglassshield = new BaubleGlassShield("glass_shield");
		public static final Item baubledispelgel = new BaubleDispelGel("dispel_gel");
	}
}
