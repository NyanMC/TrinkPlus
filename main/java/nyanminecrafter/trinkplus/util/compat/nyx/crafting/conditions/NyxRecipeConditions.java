package nyanminecrafter.trinkplus.util.compat.nyx.crafting.conditions;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import nyanminecrafter.trinkplus.TrinkPlus;

public class NyxRecipeConditions implements IConditionFactory {
	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) {
		boolean value = JsonUtils.getBoolean(json, "value", false);
		return () -> TrinkPlus.Nyx == value;
	}
}
