package nyanminecrafter.trinkplus.util.compat;

import net.minecraftforge.oredict.OreDictionary;
import nyanminecrafter.trinkplus.init.ModItems;

public class OreDictionaryCompat {
	public static void registerOres() {
		OreDictionary.registerOre("ingotEnderGlowing", ModItems.enderingot);
	}
}
