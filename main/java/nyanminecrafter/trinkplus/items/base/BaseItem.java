package nyanminecrafter.trinkplus.items.base;

import java.util.List;

import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;

public class BaseItem extends Item implements IsModelLoaded {

	public BaseItem(String name) {
		this.setTranslationKey(name);
		this.setRegistryName(name);
		this.setCreativeTab(TrinkPlus.trinkplustab);
		ModItems.ITEMS.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, world, tooltip, flagIn);
		for (int i = 1; i < 10; i++) {
			final TextComponentTranslation atooltip = new TextComponentTranslation(this.getTranslationKey().toLowerCase() + ".tooltip" + i);
			if (!(atooltip.getUnformattedComponentText().contentEquals(atooltip.getKey()) || atooltip.getUnformattedText().isEmpty())) {
				tooltip.add(atooltip.getFormattedText());
			}
		}
	}

	@Override
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
