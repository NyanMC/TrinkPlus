package nyanminecrafter.trinkplus.util.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import nyanminecrafter.trinkplus.Reference;
import nyanminecrafter.trinkplus.TrinkPlus;
import xzeroair.trinkets.util.config.trinkets.shared.BaubleCompat;

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

			@Config.Comment({ "The potency of the absorption effect given." })
			@Config.RangeInt(min = 0, max = 255)
			public int potency = 0;
			@Config.Comment({ "The duration of the absorption effect given. When this value reaches zero, the effect is applied again." })
			@Config.RangeInt(min = 1)
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
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {
					"Name:generic.maxHealth, Amount:4, Operation:0"
			};
		}

		@Name("Glass Shield")
		public GlassShieldSettings SHIELD = new GlassShieldSettings();

		public class GlassShieldSettings {

			@Config.Comment({ "The duration it takes for the Glass Shield to recharge after blocking a hit, in ticks." })
			@Config.RangeInt(min = 1)
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
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {
					//"Name:potioncore.magicShielding, Amount:1, Operation:0"
			};
		}

		@Name("Dispel Gel")
		public DispelGelSettings GEL = new DispelGelSettings();

		public class DispelGelSettings {

			@Config.Comment({ "The amount which all magic damage the player receives is multiplied by." })
			@Config.RangeDouble(min = 0.0)
			public float multiply_incoming = 0.25F;
			@Config.Comment({ "The amount which all magic damage the player deals is multiplied by." })
			@Config.RangeDouble(min = 0.0)
			public float multiply_outgoing = 0.25F;

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
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}

		@Name("Shadow Treads")
		public ShadowTreadsSettings TREADS = new ShadowTreadsSettings();

		public class ShadowTreadsSettings {

			@Config.Comment({ "The amount in which being in darkness increases your speed." })
			public float darkspeed = 0.2F;
			@Config.Comment({ "The operation for the darkspeed value." })
			@Config.RangeInt(min = 0, max = 2)
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
				public BaubleCompat baubles = new BaubleCompat("trinket");
			}
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {
					//"Name:tconevo.evasionChance, Amount:0.05, Operation:2"
			};
		}

		@Name("Duality Rings")
		public DualityRingsSettings RINGS = new DualityRingsSettings();

		public class DualityRingsSettings {

			@Config.Comment({ "The multiplier given to all projectiles affected by this trinket." })
			@Config.RangeDouble(min = 0.0)
			public float arrowAmplifier = 1.25F;
			@Config.Comment({ "Should the Duality Rings boost all fired projectiles, or only those that deal arrow damage?" })
			public boolean boostAllProjectiles = false;

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
				public BaubleCompat baubles = new BaubleCompat("ring");
			}
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}

		@Name("Friendly Fire Flower")
		public FriendlyFireFlowerSettings FLOWER = new FriendlyFireFlowerSettings();

		public class FriendlyFireFlowerSettings {
			@Config.Comment({ "The conversion rate of damage to mana. The default value of 5 will make every 1 damage cost 5 mana to block." })
			public float manaCost = 5F;
			@Config.Comment({ "If true, the cost to block an attack will be the value of manaCost no matter how much damage was dealt.", "Note: With this setting turned on, having insufficient mana will cause you to take full damage if you cannot meet the mana cost." })
			public boolean isFlatCost = false;
			@Config.Comment({ "Fire damage is multiplied by this value if a Friendly Fire Flower is equipped." })
			public float fireDamageMultiplier = 0.5F;

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
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}

		@Name("Lunar Crystal")
		public LunarCrystalSettings CRYSTAL = new LunarCrystalSettings();

		public class LunarCrystalSettings {
			@Config.Comment({ "1 in X chance to cause an entity to levitate on attack." })
			@Config.RangeInt(min = 2)
			public int levitationChance = 20;
			@Config.Comment({ "The duration of the Levitation effect given, in ticks. Setting this to 0 will effectively disable the levitation effect entirely." })
			@Config.RangeInt(min = 0)
			public int levitationDuration = 40;
			@Config.Comment({ "The level of the Levitation effect given. This is based on internal values, so 0 refers to Levitation I, 1 refers to Levitation II, etc." })
			@Config.RangeInt(min = 0)
			public int levitationAmplifier = 2;
			@Config.Comment({ "Should the Lunar Crystal allow the player to ignore fall damage?" })
			public boolean ignoreFallDamage = true;

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
				public BaubleCompat baubles = new BaubleCompat("charm");
			}
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}

		@Name("Harpy Feather")
		public HarpyFeatherSettings FEATHER = new HarpyFeatherSettings();

		public class HarpyFeatherSettings {
			@Config.Comment({ "The speed in which the player rises and falls. Multiplied by the player's flight speed." })
			public double verticalSpeed = 2.5D;
			@Config.Comment({ "The vertical boost which the player receives on right click. This uses the motionY value, so you may need to tweak this value quite a bit to get the result you want." })
			public double launchForce = 0.42D;
			@Config.Comment({ "The cooldown of the feather's right click ability, in ticks." })
			public int cooldown = 60;
			@Config.Comment({ "Should the right click ability work on mounts? This config option is provided in the event of mod incompatibilities." })
			public boolean worksOnMounts = true;

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
				public BaubleCompat baubles = new BaubleCompat("any");
			}
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}

		@Name("Super Faelis Claws")
		public SuperFaelisClawsSettings S_CLAWS = new SuperFaelisClawsSettings();

		public class SuperFaelisClawsSettings {
			@Config.Comment({ "The amount which all damage the player receives is multiplied by." })
			@Config.RangeDouble(min = 0.0)
			public float multiply_incoming = 1.25F;
			@Config.Comment({ "The amount which all pseudo-melee damage the player deals is multiplied by." })
			@Config.RangeDouble(min = 0.0)
			public float multiply_outgoing = 1.5F;
			@Config.Comment({ "Should the vicious claw's outgoing multiplier apply to all damage, or just pseudo-melee?" })
			public boolean multiply_all_outgoing = false;

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
				public BaubleCompat baubles = new BaubleCompat("any");
			}
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}

		@Name("Super Damage Shield")
		public SuperDamageShieldSettings S_SOH = new SuperDamageShieldSettings();

		public class SuperDamageShieldSettings {
			@Config.Comment({ "The amount in which the thorns damage of the blocked hit is multiplied by. This configuration was made with Scaling Health's damage scaling system in mind." })
			@Config.RangeDouble(min = 0.0)
			public float thorns_multiplier = 1F;

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
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}
		
		@Name("Super Dispel Gel")
		public SuperDispelGelSettings S_GEL = new SuperDispelGelSettings();
		
		public class SuperDispelGelSettings {
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
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}
		
		@Name("Super Teddy Bear")
		public SuperTeddyBearSettings S_TB = new SuperTeddyBearSettings();
		
		public class SuperTeddyBearSettings {
			@Config.Comment({ "The amount of health restored after sleeping, as a percentage of the player's maximum health. Set to a negative value to disable." })
			@Config.RangeDouble(max = 1.0)
			public float healthRestoreAmount = 1.0F;
			
			@Config.Comment({ "The amount of mana restored after sleeping, as a percentage of the player's maximum mana. Set to a negative value to disable." })
			@Config.RangeDouble(max = 1.0)
			public float manaRestoreAmount = 1.0F;
			
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
				public BaubleCompat baubles = new BaubleCompat("head");
			}
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}
		
		@Name("Super Shadow Treads")
		public SuperShadowTreadsSettings S_TREADS = new SuperShadowTreadsSettings();
		
		public class SuperShadowTreadsSettings {
			@Config.Comment({ "The amount in which being in light increases your attack damage." })
			public float lightpower = 0.2F;
			@Config.Comment({ "The operation for the lightpower value." })
			@Config.RangeInt(min = 0, max = 2)
			public int lightpoweroperation = 2;
			
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
				public BaubleCompat baubles = new BaubleCompat("charm");
			}
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {};
		}

		@Name("Solarborn")
		public SolarbornSettings SOLARBORN = new SolarbornSettings();

		public class SolarbornSettings {
			@Config.Comment({ "The minimum size a solarborn can be, as a percentage. Values below 25 are not recommended, but will not crash the game. Going above maxSize could break the game." })
			@Config.RangeInt(min = 1, max = 500)
			public int minSize = 25;
			@Config.Comment({ "The maximum size a solarborn can be, as a percentage. Values above 200 are not recommended, but will not crash the game. Going below minSize could break the game." })
			@Config.RangeInt(min = 1, max = 500)
			public int maxSize = 150;
			@RequiresMcRestart
			@Config.Comment({ "Duration of the Potion of Solarborn Transformation, in ticks. The duration of the extended variant will always be this value, tripled." })
			@Config.RangeInt(min = 1)
			public int potionDuration = 1200;
			
			public double minSpeedCap = -0.2D;
			public double maxSpeedCap = 0.2D;
			public double minDamageCap = -0.5D;
			public double maxDamageCap = 0.5D;
			public double minArmorCap = -1D;
			public double maxArmorCap = 0.25D;
			
			@Config.Comment({ "The duration in which the mana bleed effect is given when a Solarborn takes damage. This is halved when wearing a Friendly Fire Flower." })
			@Config.RangeInt(min = 1)
			public int manaBleedDuration = 100;
			@Config.Comment({ "The amplifier in which the mana bleed effect is given. Set to -1 to disable." })
			@Config.RangeInt(min = -1)
			public int manaBleedLevel = 1;
			/*@Config.RangeInt(min = 0, max = 255)
			@Config.Comment({ "The amplifier in which the fire resistance effect is given. There isn't a reason to set this higher than 0 (level 1), unless using a mod such as Fire Resistance Tiers. Requested by Z-Tunic" })
			public int fireResistanceLevel = 0;*/
			
			@Config.Comment({"These stack with the scaling attributes.", "For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {
					"Name:xat.entityMagic.regen, Amount:1, Operation:0"
			};
		}
		
		@Name("Azalean")
		public AzaleanSettings AZALEAN = new AzaleanSettings();
		
		public class AzaleanSettings {
			@RequiresMcRestart
			@Config.Comment({ "Duration of the Potion of Azalean Transformation, in ticks. The duration of the extended variant will always be this value, tripled." })
			@Config.RangeInt(min = 1)
			public int potionDuration = 1200;
			
			@Config.Comment({ "The duration in which the regeneration effect is given when an Azalean is in sunlight.", "The effect is reapplied every tick, so this value does effectively nothing." })
			@Config.RangeInt(min = 1)
			public int regenerationDuration = 150;
			@Config.Comment({ "The amplifier in which the regeneration effect is given. Set to -1 to disable." })
			@Config.RangeInt(min = -1)
			public int regenerationLevel = 0;
			
			@Config.Comment({"Determines the level of the boosts received by an Azalean when near a flower."})
			public FlowerBoosts flowerBoosts = new FlowerBoosts();
			
			public class FlowerBoosts {
				public double damageModifier = 0.25;
				@Config.RangeInt(min = 0, max = 2)
				public int damageOperation = 2;
				public double armorModifier = 0.25;
				@Config.RangeInt(min = 0, max = 2)
				public int armorOperation = 2;
				public double speedModifier = 0.25;
				@Config.RangeInt(min = 0, max = 2)
				public int speedOperation = 2;
			}
			
			@Config.Comment({"For More Information on Attributes", "https://minecraft.gamepedia.com/Attribute"})
			@Name("Attributes")
			@LangKey(Reference.MODID + ".config.attributes")
			public String[] attributes = {
					"Name:generic.maxHealth, Amount:0.2, Operation:2",
					"Name:generic.attackDamage, Amount:-0.25, Operation:2",
					"Name:generic.armor, Amount:-0.25, Operation:2",
					"Name:generic.luck, Amount:1, Operation:0"
			};
		}

		@Name("Magic Garlic Bread")
		public GarlicBreadSettings BREAD = new GarlicBreadSettings();

		public class GarlicBreadSettings {
			@Config.Comment({ "The duration in which the mana regeneration effect is given." })
			@Config.RangeInt(min = 1)
			public int manaRegenDuration = 6000;
			@Config.Comment({ "The amplifier in which the mana regeneration effect is given. Set to -1 to disable." })
			@Config.RangeInt(min = -1)
			public int manaRegenLevel = 1;
			@Config.Comment({ "The duration in which the health boost effect is given when the player is wearing the Duality Rings." })
			@Config.RangeInt(min = 1)
			public int healthBoostDuration = 2400;
			@Config.Comment({ "The amplifier in which the health boost effect is given when the player is wearing the Duality Rings. Set to -1 to disable." })
			@Config.RangeInt(min = -1)
			public int healthBoostLevel = 2;
			@Config.Comment({ "The duration in which the saturation boost effect is given when the player is wearing the Duality Rings." })
			@Config.RangeInt(min = 1)
			public int saturationDuration = 200;
			@Config.Comment({ "The amplifier in which the saturation effect is given when the player is wearing the Duality Rings. Set to -1 to disable." })
			@Config.RangeInt(min = -1)
			public int saturationLevel = 0;
		}
		
		@Name("Overloaded Candy")
		public OverloadedCandySettings CANDY = new OverloadedCandySettings();
		
		public class OverloadedCandySettings {
			@Config.Comment({ "The minimum required magic affinity for the player to not take damage upon consuming an overloaded candy.",
				"Note that this is not always the same as maximum mana, as this goes by the mana a race has before other factors such as mana crystals are accounted for." })
			@Config.RangeInt(min = 1)
			public int requiredMagicAffinity = 250;
			@Config.Comment({ "The damage dealt when the above requirement is not met. This damage is magic, and bypasses the ward crystal." })
			@Config.RangeInt(min = 0)
			public int damageAmount = 8;
		}

		@RequiresMcRestart
		@Config.Comment({ "Globally toggles whether TrinkPlus trinkets can be equipped in Baubles slots or not." })
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
