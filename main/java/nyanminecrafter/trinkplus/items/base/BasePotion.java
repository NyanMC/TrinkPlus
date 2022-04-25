package nyanminecrafter.trinkplus.items.base;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import nyanminecrafter.trinkplus.Reference;
import xzeroair.trinkets.attributes.MagicAttributes;

public class BasePotion extends Potion {
	
	protected static ResourceLocation ICON = new ResourceLocation(Reference.RESOURCE_PREFIX + "textures/potions/effects.png");
	
	protected String name;
	protected int duration;

	public BasePotion(String name, int duration, int color, int iconX, int iconY, int level) {
		super(false, color);
		this.name = name;
		this.duration = duration;
		this.setIconIndex(iconX, iconY);
		this.setPotionName(Reference.MODID + ".effect." + name);
		this.setRegistryName(new ResourceLocation(Reference.MODID, name));
		this.registerPotionAttributeModifier(MagicAttributes.regen, "551a6737-9b2a-443d-8622-b3f3de3459e1", 0.5, 2);
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return duration <= 1;
	}

	@Override
	public boolean isInstant() {
		return duration <= 0;
	}
	
	@Override
	public void affectEntity(Entity source, Entity indirectSource, EntityLivingBase entity, int amplifier, double health) {
		
	}
	
	@Override
	public boolean hasStatusIcon() {
		Minecraft.getMinecraft().renderEngine.bindTexture(ICON);
		return true;
	}
}
