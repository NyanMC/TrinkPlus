package nyanminecrafter.trinkplus.items;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.TrinkPlus;
import nyanminecrafter.trinkplus.init.ModItems;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.ShadowTreadsSettings;
import nyanminecrafter.trinkplus.util.interfaces.IsModelLoaded;
import xzeroair.trinkets.attributes.UpdatingAttribute;
import xzeroair.trinkets.items.base.AccessoryBase;

public class ShadowTreads extends AccessoryBase implements IsModelLoaded {
	
	private ShadowTreadsSettings config = ModConfig.SERVER.TREADS;
	
	protected UpdatingAttribute movement;
	
	public ShadowTreads(String name) {
		super(name);
		this.setUUID("45df454d-6722-49fb-a8c3-21393f7774c0");
		setItemAttributes(config.Attributes);
		ModItems.TRINKETS_ITEMS.add(this);
		
		movement = new UpdatingAttribute(UUID.fromString("081b5c32-b64e-4302-8d01-522d669b40af"), SharedMonsterAttributes.MOVEMENT_SPEED);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		TrinkPlus.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public void eventPlayerTick(ItemStack stack, EntityPlayer player) {
		super.eventPlayerTick(stack, player);
		if (player.isEntityAlive() && player.getEntityWorld() != null && !player.world.isRemote) {
			World world = player.world;
			BlockPos playerpos = new BlockPos(player);
			Chunk chunk = world.getChunk(playerpos);
			
			// remember to change the light level to 0 if trinkplus ever comes to 1.18+
			if (world.getLight(playerpos, true) <= 7) {
				movement.addModifier(player, config.darkspeed, config.darkspeedoperation);
			} else {
				movement.removeModifier(player);
			}
//			System.out.println("TRINKPLUS DEBUG: Current light is " + world.getLight(playerpos, true));
		}
	}
	
	@Override
	public void playerUnequipped(ItemStack stack, EntityLivingBase entity) {
		super.playerUnequipped(stack, entity);
		movement.removeModifier(entity);
	}
}
