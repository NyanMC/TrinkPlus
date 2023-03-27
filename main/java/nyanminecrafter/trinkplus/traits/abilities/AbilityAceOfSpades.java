package nyanminecrafter.trinkplus.traits.abilities;

import java.util.List;

import nyanminecrafter.trinkplus.Reference;
import xzeroair.trinkets.traits.abilities.Ability;

public class AbilityAceOfSpades extends Ability {
	
	public AbilityAceOfSpades() {
		super(Reference.MODID, "ace_of_spades");
	}
	
	@Override
	public void getDescription(List<String> tooltips) {
		tooltips.add("Magic Garlic Bread grants Health Boost and Saturation alongside Mana Regen when consumed");
	}
	
}
