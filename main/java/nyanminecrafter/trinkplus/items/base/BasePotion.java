package nyanminecrafter.trinkplus.items.base;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.util.ResourceLocation;
import nyanminecrafter.trinkplus.Reference;

public class BasePotion extends xzeroair.trinkets.items.base.BasePotion {

	public BasePotion(String name, int duration, int color, boolean isBadEffect) {
		super(Reference.MODID, name, duration, color, isBadEffect, -1, -1, new ResourceLocation(Reference.MODID, "textures/potions/" + name + ".png"));
	}

	public BasePotion(String name, int duration, int color, boolean isBadEffect, int iconX, int iconY, ResourceLocation texture) {
		super(Reference.MODID, name, color, duration, isBadEffect, iconX, iconY, texture);
	}

	public BasePotion(String name, int duration, int color, boolean isBadEffect, IAttribute attribute, String uuid, double amount, int operation) {
		this(name, duration, color, isBadEffect);
		this.registerPotionAttributeModifier(attribute, uuid, amount, operation);
	}

	public BasePotion(String name, int duration, int color, boolean isBadEffect, int iconX, int iconY, ResourceLocation texture, IAttribute attribute, String uuid, double amount, int operation) {
		this(name, duration, color, isBadEffect, iconX, iconY, texture);
		this.registerPotionAttributeModifier(attribute, uuid, amount, operation);
	}
}