package nyanminecrafter.trinkplus.util.helpers;

public class AttributeTooltipHelper {
	
	public static String modifierToPercentage(float modifier) {
		return (Math.round(modifier * 100) + "%");
	}
	
	public static String modifierToString(float modifier, int operation) {
		if (operation > 0) {
			return modifierToPercentage(modifier);
		} else {
			return "" + modifier;
		}
	}
	
}
