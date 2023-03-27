package nyanminecrafter.trinkplus.util.compat;

import net.minecraftforge.oredict.OreDictionary;
import nyanminecrafter.trinkplus.init.ModItems;

public class OreDictionaryCompat {
	public static void registerOres() {
		OreDictionary.registerOre("ingotEnderGlowing", ModItems.enderingot);
		OreDictionary.registerOre("dustEnderGlowing", ModItems.enderpowder);
		OreDictionary.registerOre("gemEnderGlowing", ModItems.endergem);
		// the below two are to trick a boatload of tech mods into spitting out a recipe where you throw a mana crystal into a grinder to make a mana reagent
		OreDictionary.registerOre("gemTrinketsMana", xzeroair.trinkets.init.ModItems.foods.mana_crystal);
		OreDictionary.registerOre("dustTrinketsMana", xzeroair.trinkets.init.ModItems.foods.mana_reagent);
	}
}
