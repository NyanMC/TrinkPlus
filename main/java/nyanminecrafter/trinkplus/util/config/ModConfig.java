package nyanminecrafter.trinkplus.util.config;

import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.TrinkPlus;
import xzeroair.trinkets.util.config.trinkets.ConfigGlowRing.Compatability;
import xzeroair.trinkets.util.config.trinkets.shared.BaubleCompat;
import xzeroair.trinkets.util.config.trinkets.shared.ConfigAttribs;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;

@Config(name = Reference.configPath, modid = Reference.MODID)
@Config.LangKey(Reference.MODID + ".config.title")
public class ModConfig {
	private final static String cfgPrefix = Reference.MODID + ".config";
	
	@Name("Settings")
	@LangKey(cfgPrefix + ".server.settings")
	public static xServer SERVER = new xServer();
	
	public static class xServer {
		
		@Name("Golden Heart")
		public GoldenHeartSettings HEART = new GoldenHeartSettings();
		
		public class GoldenHeartSettings {
			
			public int potency = 0;
			public int recharge = 400;
			
			@Name("Compatability Settings")
			@LangKey(Reference.MODID + ".config.compatability")
			public Compatability compat = new Compatability();
			public class Compatability {
				@Name("Baubles Compatability")
				@Config.Comment({
					"If the mod Baubles is installed what bauble slot should it use",
					"Available Types:",
					"Trinket, Any, All",
					"Amulet, Necklace, Pendant",
					"Ring, Rings",
					"Belt",
					"Head, Hat",
					"Body, Chest",
					"Charm"
				})
				@LangKey(Reference.MODID + ".config.baubles")
				public BaubleCompat baubles = new BaubleCompat("trinket");
			}
		
		private final boolean 	armor = false;
		private final double 	armorAmount = 0D;
		private final int		armorOperation = 2;
		private final boolean 	attackSpeed = false;
		private final double 	attackSpeedAmount = 0;
		private final int		attackSpeedOperation = 2;
		private final boolean 	damage = false;
		private final double 	damageAmount = 0D;
		private final int		damageOperation = 2;
		private final boolean 	health = true;
		private final double 	healthAmount = 4D;
		private final int		healthOperation = 0;
		private final boolean 	knockback = false;
		private final double 	knockbackAmount = 0;
		private final int		knockbackOperation = 2;
		private final boolean 	speed = false;
		private final double 	speedAmount = 0D;
		private final int		speedOperation = 2;
		private final boolean 	swimSpeed = false;
		private final double 	swimSpeedAmount = 0D;
		private final int		swimSpeedOperation = 2;
		private final boolean 	toughness = false;
		private final double 	toughnessAmount = 0D;
		private final int		toughnessOperation = 2;
		private final boolean	luck = false;
		private final double	luckAmount = 0;
		private final int		luckOperation = 2;
		private final boolean	reach = false;
		private final double	reachAmount = 0D;
		private final int		reachOperation = 2;
		private final boolean	jump = false;
		private final double	jumpAmount = 0;
		private final int		jumpOperation = 2;
		private final boolean	stepHeight = false;
		private final double	stepHeightAmount = 0;
		private final int		stepHeightOperation = 0;


		@Config.Comment({"For Mor Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
		@Name("Attributes")
		@LangKey(Reference.MODID + ".config.attributes")
		public ConfigAttribs Attributes = new ConfigAttribs(
				armor, 			armorAmount, 		armorOperation,
				attackSpeed, 	attackSpeedAmount, 	attackSpeedOperation,
				damage, 		damageAmount, 		damageOperation,
				health, 		healthAmount, 		healthOperation,
				knockback, 		knockbackAmount, 	knockbackOperation,
				speed, 			speedAmount, 		speedOperation,
				swimSpeed, 		swimSpeedAmount, 	swimSpeedOperation,
				toughness, 		toughnessAmount, 	toughnessOperation,
				luck,			luckAmount,			luckOperation,
				reach,			reachAmount,		reachOperation,
				jump, 			jumpAmount, 		jumpOperation,
				stepHeight,		stepHeightAmount, 	stepHeightOperation
				);
		}
		
		@Name("Glass Shield")
		public GlassShieldSettings SHIELD = new GlassShieldSettings();
		
		public class GlassShieldSettings {
			
			public int cooldown = 400;
			
			@Name("Compatability Settings")
			@LangKey(Reference.MODID + ".config.compatability")
			public Compatability compat = new Compatability();
			public class Compatability {
				@Name("Baubles Compatability")
				@Config.Comment({
					"If the mod Baubles is installed what bauble slot should it use",
					"Available Types:",
					"Trinket, Any, All",
					"Amulet, Necklace, Pendant",
					"Ring, Rings",
					"Belt",
					"Head, Hat",
					"Body, Chest",
					"Charm"
				})
				@LangKey(Reference.MODID + ".config.baubles")
				public BaubleCompat baubles = new BaubleCompat("body");
			}
		
		private final boolean 	armor = false;
		private final double 	armorAmount = 0D;
		private final int		armorOperation = 0;
		private final boolean 	attackSpeed = false;
		private final double 	attackSpeedAmount = 0;
		private final int		attackSpeedOperation = 2;
		private final boolean 	damage = false;
		private final double 	damageAmount = 0D;
		private final int		damageOperation = 2;
		private final boolean 	health = false;
		private final double 	healthAmount = 0D;
		private final int		healthOperation = 0;
		private final boolean 	knockback = false;
		private final double 	knockbackAmount = 0;
		private final int		knockbackOperation = 2;
		private final boolean 	speed = false;
		private final double 	speedAmount = 0D;
		private final int		speedOperation = 2;
		private final boolean 	swimSpeed = false;
		private final double 	swimSpeedAmount = 0D;
		private final int		swimSpeedOperation = 2;
		private final boolean 	toughness = false;
		private final double 	toughnessAmount = 0D;
		private final int		toughnessOperation = 2;
		private final boolean	luck = false;
		private final double	luckAmount = 0;
		private final int		luckOperation = 2;
		private final boolean	reach = false;
		private final double	reachAmount = 0D;
		private final int		reachOperation = 2;
		private final boolean	jump = false;
		private final double	jumpAmount = 0;
		private final int		jumpOperation = 2;
		private final boolean	stepHeight = false;
		private final double	stepHeightAmount = 0;
		private final int		stepHeightOperation = 0;


		@Config.Comment({"For Mor Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
		@Name("Attributes")
		@LangKey(Reference.MODID + ".config.attributes")
		public ConfigAttribs Attributes = new ConfigAttribs(
				armor, 			armorAmount, 		armorOperation,
				attackSpeed, 	attackSpeedAmount, 	attackSpeedOperation,
				damage, 		damageAmount, 		damageOperation,
				health, 		healthAmount, 		healthOperation,
				knockback, 		knockbackAmount, 	knockbackOperation,
				speed, 			speedAmount, 		speedOperation,
				swimSpeed, 		swimSpeedAmount, 	swimSpeedOperation,
				toughness, 		toughnessAmount, 	toughnessOperation,
				luck,			luckAmount,			luckOperation,
				reach,			reachAmount,		reachOperation,
				jump, 			jumpAmount, 		jumpOperation,
				stepHeight,		stepHeightAmount, 	stepHeightOperation
				);
		}
		
		@Name("Dispel Gel")
		public DispelGelSettings GEL = new DispelGelSettings();
		
		public class DispelGelSettings {
			
			public float multiply = 0.25F;
			
			@Name("Compatability Settings")
			@LangKey(Reference.MODID + ".config.compatability")
			public Compatability compat = new Compatability();
			public class Compatability {
				@Name("Baubles Compatability")
				@Config.Comment({
					"If the mod Baubles is installed what bauble slot should it use",
					"Available Types:",
					"Trinket, Any, All",
					"Amulet, Necklace, Pendant",
					"Ring, Rings",
					"Belt",
					"Head, Hat",
					"Body, Chest",
					"Charm"
				})
				@LangKey(Reference.MODID + ".config.baubles")
				public BaubleCompat baubles = new BaubleCompat("trinket");
			}
		
		private final boolean 	armor = false;
		private final double 	armorAmount = 0D;
		private final int		armorOperation = 2;
		private final boolean 	attackSpeed = false;
		private final double 	attackSpeedAmount = 0;
		private final int		attackSpeedOperation = 2;
		private final boolean 	damage = false;
		private final double 	damageAmount = 0D;
		private final int		damageOperation = 2;
		private final boolean 	health = false;
		private final double 	healthAmount = 0;
		private final int		healthOperation = 0;
		private final boolean 	knockback = false;
		private final double 	knockbackAmount = 0;
		private final int		knockbackOperation = 2;
		private final boolean 	speed = false;
		private final double 	speedAmount = 0D;
		private final int		speedOperation = 2;
		private final boolean 	swimSpeed = false;
		private final double 	swimSpeedAmount = 0D;
		private final int		swimSpeedOperation = 2;
		private final boolean 	toughness = false;
		private final double 	toughnessAmount = 0D;
		private final int		toughnessOperation = 2;
		private final boolean	luck = false;
		private final double	luckAmount = 0;
		private final int		luckOperation = 2;
		private final boolean	reach = false;
		private final double	reachAmount = 0D;
		private final int		reachOperation = 2;
		private final boolean	jump = false;
		private final double	jumpAmount = 0;
		private final int		jumpOperation = 2;
		private final boolean	stepHeight = false;
		private final double	stepHeightAmount = 0;
		private final int		stepHeightOperation = 0;


		@Config.Comment({"For Mor Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
		@Name("Attributes")
		@LangKey(Reference.MODID + ".config.attributes")
		public ConfigAttribs Attributes = new ConfigAttribs(
				armor, 			armorAmount, 		armorOperation,
				attackSpeed, 	attackSpeedAmount, 	attackSpeedOperation,
				damage, 		damageAmount, 		damageOperation,
				health, 		healthAmount, 		healthOperation,
				knockback, 		knockbackAmount, 	knockbackOperation,
				speed, 			speedAmount, 		speedOperation,
				swimSpeed, 		swimSpeedAmount, 	swimSpeedOperation,
				toughness, 		toughnessAmount, 	toughnessOperation,
				luck,			luckAmount,			luckOperation,
				reach,			reachAmount,		reachOperation,
				jump, 			jumpAmount, 		jumpOperation,
				stepHeight,		stepHeightAmount, 	stepHeightOperation
				);
		}
		
		@Name("Shadow Treads")
		public ShadowTreadsSettings TREADS = new ShadowTreadsSettings();
		
		public class ShadowTreadsSettings {
			
			public float darkspeed = 0.2F;
			public int darkspeedoperation = 2;
			
			@Name("Compatability Settings")
			@LangKey(Reference.MODID + ".config.compatability")
			public Compatability compat = new Compatability();
			public class Compatability {
				@Name("Baubles Compatability")
				@Config.Comment({
					"If the mod Baubles is installed what bauble slot should it use",
					"Available Types:",
					"Trinket, Any, All",
					"Amulet, Necklace, Pendant",
					"Ring, Rings",
					"Belt",
					"Head, Hat",
					"Body, Chest",
					"Charm"
				})
				@LangKey(Reference.MODID + ".config.baubles")
				public BaubleCompat baubles = new BaubleCompat("trinket"); // note: in 1.16+ trinkplus whenever that may be there's a more fitting slot to use
			}
		
		private final boolean 	armor = false;
		private final double 	armorAmount = 0D;
		private final int		armorOperation = 2;
		private final boolean 	attackSpeed = false;
		private final double 	attackSpeedAmount = 0;
		private final int		attackSpeedOperation = 2;
		private final boolean 	damage = false;
		private final double 	damageAmount = 0D;
		private final int		damageOperation = 2;
		private final boolean 	health = false;
		private final double 	healthAmount = 0;
		private final int		healthOperation = 0;
		private final boolean 	knockback = false;
		private final double 	knockbackAmount = 0;
		private final int		knockbackOperation = 2;
		private final boolean 	speed = false;
		private final double 	speedAmount = 0D;
		private final int		speedOperation = 2;
		private final boolean 	swimSpeed = false;
		private final double 	swimSpeedAmount = 0D;
		private final int		swimSpeedOperation = 2;
		private final boolean 	toughness = false;
		private final double 	toughnessAmount = 0D;
		private final int		toughnessOperation = 2;
		private final boolean	luck = false;
		private final double	luckAmount = 0;
		private final int		luckOperation = 2;
		private final boolean	reach = false;
		private final double	reachAmount = 0D;
		private final int		reachOperation = 2;
		private final boolean	jump = false;
		private final double	jumpAmount = 0;
		private final int		jumpOperation = 2;
		private final boolean	stepHeight = false;
		private final double	stepHeightAmount = 0;
		private final int		stepHeightOperation = 0;


		@Config.Comment({"For Mor Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
		@Name("Attributes")
		@LangKey(Reference.MODID + ".config.attributes")
		public ConfigAttribs Attributes = new ConfigAttribs(
				armor, 			armorAmount, 		armorOperation,
				attackSpeed, 	attackSpeedAmount, 	attackSpeedOperation,
				damage, 		damageAmount, 		damageOperation,
				health, 		healthAmount, 		healthOperation,
				knockback, 		knockbackAmount, 	knockbackOperation,
				speed, 			speedAmount, 		speedOperation,
				swimSpeed, 		swimSpeedAmount, 	swimSpeedOperation,
				toughness, 		toughnessAmount, 	toughnessOperation,
				luck,			luckAmount,			luckOperation,
				reach,			reachAmount,		reachOperation,
				jump, 			jumpAmount, 		jumpOperation,
				stepHeight,		stepHeightAmount, 	stepHeightOperation
				);
		}
		
		@RequiresMcRestart
		@Config.Comment({"Globally toggles whether TrinkPlus trinkets can be equipped in Baubles slots or not."})
		@Name("Baubles Compatibility")
		public boolean baublesCompat = true;
	}
	
	public static void Save() {
		ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
	}

	public static void readConfig() {
		final Configuration cfg = TrinkPlus.config;
		try {
			cfg.load();
		} catch (final Exception e1) {
			TrinkPlus.log.error(Reference.MODID + " had a problem loading its configuration");
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}
}
