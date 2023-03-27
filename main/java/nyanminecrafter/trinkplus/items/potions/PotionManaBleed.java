package nyanminecrafter.trinkplus.items.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.items.base.BasePotion;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.capabilities.magic.MagicStats;

public class PotionManaBleed extends BasePotion {
	
	protected static ResourceLocation ICON = new ResourceLocation(Reference.MODID, "textures/potions/manableed.png");

	public PotionManaBleed(String name, int duration, int color) {
		super(name, duration, color, true);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void performEffect(EntityLivingBase entity, int amplifier) {
		MagicStats mana = Capabilities.getMagicStats(entity);
		if (mana != null) {
			if (!mana.spendMana(0.25F * (amplifier + 1))) {
				mana.setMana(0);
			}
		}
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isBadEffect() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean hasStatusIcon() {
		Minecraft.getMinecraft().renderEngine.bindTexture(ICON);
		return true;
	}

}
