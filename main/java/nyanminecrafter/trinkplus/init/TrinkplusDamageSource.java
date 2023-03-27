package nyanminecrafter.trinkplus.init;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TrinkplusDamageSource extends EntityDamageSource {
	
	public static final TrinkplusDamageSource overload = new TrinkplusDamageSource("trinkplus.overload");
	public static final TrinkplusDamageSource water = new TrinkplusDamageSource("trinkplus.water"); // for a unique death message

	public TrinkplusDamageSource(String damageType) {
		this(damageType, null);
	}

	public TrinkplusDamageSource(String damageType, @Nullable Entity damageSource) {
		super(damageType, damageSource);
		damageSourceEntity = damageSource;
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
		final ITextComponent source = new TextComponentTranslation("damagetype." + damageType);
		final String s = "death.attack." + damageType;
		
		return new TextComponentTranslation(s, new Object[] { entityLivingBaseIn.getDisplayName(), source });
	}

}
