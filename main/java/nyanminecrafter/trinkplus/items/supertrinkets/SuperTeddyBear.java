package nyanminecrafter.trinkplus.items.supertrinkets;

import java.util.List;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nyanminecrafter.trinkplus.blocks.BlockSuperTeddyBear;
import nyanminecrafter.trinkplus.init.ModBlocks;
import nyanminecrafter.trinkplus.items.base.BaseAccessory;
import nyanminecrafter.trinkplus.traits.abilities.AbilityExquisitelyRested;
import nyanminecrafter.trinkplus.util.config.ModConfig;
import nyanminecrafter.trinkplus.util.config.ModConfig.xServer.SuperTeddyBearSettings;
import nyanminecrafter.trinkplus.util.interfaces.ISuperTrinket;
import xzeroair.trinkets.api.TrinketHelper;
import xzeroair.trinkets.blocks.tileentities.TileEntityTeddyBear;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.client.model.BipedJsonModel;
import xzeroair.trinkets.items.trinkets.TrinketTeddyBear;
import xzeroair.trinkets.traits.abilities.AbilityWellRested;
import xzeroair.trinkets.traits.abilities.interfaces.IAbilityInterface;
import xzeroair.trinkets.util.TrinketsConfig;
import xzeroair.trinkets.util.config.trinkets.ConfigTeddyBear;

public class SuperTeddyBear extends BaseAccessory implements ISuperTrinket {
	
	public static final ConfigTeddyBear serverConfig = TrinketsConfig.SERVER.Items.TEDDY_BEAR;
	private SuperTeddyBearSettings superConfig = ModConfig.SERVER.S_TB;

	public SuperTeddyBear(String name) {
		super(name);
		this.setUUID("200c9a33-29be-4aba-be20-1a44ad8c60a2");
		this.setAttributeConfig(superConfig.attributes);
	}
	
	@Override
	public void initAbilities(ItemStack stack, EntityLivingBase entity, List<IAbilityInterface> abilities) {
		if (serverConfig.sleep_bonus)
			abilities.add(new AbilityWellRested());
		abilities.add(new AbilityExquisitelyRested());
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		//		String crafter = this.getCrafter(stack);
		//		if (!crafter.isEmpty()) {
		//			return crafter + "'s " + super.getItemStackDisplayName(stack);
		//		}
		return Capabilities.getTrinketProperties(stack, super.getItemStackDisplayName(stack), (prop, name) -> {
			final String crafter = prop.getCrafter();
			if (!crafter.isEmpty()) {
				return crafter;
			}
			return name;
		});
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing == EnumFacing.DOWN) {
			return EnumActionResult.FAIL;
		} else {
			if (world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
				facing = EnumFacing.UP;
				pos = pos.down();
			}
			IBlockState iblockstate = world.getBlockState(pos);
			Block block = iblockstate.getBlock();
			boolean flag = block.isReplaceable(world, pos);

			if (!flag) {
				if (!world.getBlockState(pos).getMaterial().isSolid() && !world.isSideSolid(pos, facing, true)) {
					return EnumActionResult.FAIL;
				}
				pos = pos.offset(facing);
			}

			ItemStack itemstack = player.getHeldItem(hand);
			BlockSuperTeddyBear TeddyBlock = ModBlocks.superTeddyBear;
			if (player.canPlayerEdit(pos, facing, itemstack) && TeddyBlock.canPlaceBlockAt(world, pos)) {
				if (world.isRemote) {
					return EnumActionResult.SUCCESS;
				} else {
					IBlockState iblockstate1 = TeddyBlock.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, 0, player, hand);
					if (this.placeBlockAt(itemstack, player, world, pos, facing, hitX, hitY, hitZ, iblockstate1)) {
						int i = 0;
						if (facing == EnumFacing.UP) {
							i = MathHelper.floor(((player.rotationYaw * 16.0F) / 360.0F) + 0.5D) & 15;
						}
						TileEntity tileentity = world.getTileEntity(pos);
						if (tileentity instanceof TileEntityTeddyBear) {
							TileEntityTeddyBear teddy = (TileEntityTeddyBear) tileentity;
							teddy.setRotation(i);
						}
						iblockstate1 = world.getBlockState(pos);
						SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, world, pos, player);
						world.playSound((EntityPlayer) null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
						itemstack.shrink(1);
						if (player instanceof EntityPlayerMP) {
							CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, itemstack);
						}
					}
					return EnumActionResult.SUCCESS;
				}
			} else {
				return EnumActionResult.FAIL;
			}
		}
	}
	
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
		if (!world.setBlockState(pos, newState, 11)) {
			return false;
		}

		IBlockState state = world.getBlockState(pos);
		BlockSuperTeddyBear myBlock = ModBlocks.superTeddyBear;
		if (state.getBlock() == myBlock) {
			ItemBlock.setTileEntityNBT(world, player, pos, stack);
			myBlock.onBlockPlacedBy(world, pos, state, player, stack);

			if (player instanceof EntityPlayerMP) {
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, stack);
			}
		}

		return true;
	}

	@Override
	public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EntityEquipmentSlot.HEAD;//super.getEquipmentSlot(stack);
	}

	@Override
	public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
		boolean alreadyEquipped = (entity instanceof EntityLivingBase) && TrinketHelper.AccessoryCheck((EntityLivingBase) entity, this);
		return !alreadyEquipped && super.isValidArmor(stack, armorType, entity);
	}
	
	@Override
	public boolean canEquipAccessory(ItemStack stack, EntityLivingBase player) {
		boolean alreadyEquipped = false;
		try {
			alreadyEquipped = TrinketHelper.getSlotInfoForItemFromEquipment(player, s -> (s != null) && !s.isEmpty() && s.isItemEqual(stack)) == null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alreadyEquipped && this.canEquipTrinket(stack, player);
	}
	
	private BipedJsonModel blahaj;

	@Override
	@SideOnly(Side.CLIENT)
	public void playerRenderLayer(ItemStack stack, EntityLivingBase player, RenderPlayer renderer, boolean isSlim, float partialTicks, float scale) {
		if (false) { //TODO implement client config and make it allow you to disable this model
			return;
		}
		if (blahaj == null) {
			blahaj = new BipedJsonModel(new ResourceLocation(this.getRegistryName().toString()));
		}
		GlStateManager.pushMatrix();
		BipedJsonModel model = blahaj;
		model.equippedAsAccessory = true;
		model.tranformType = ItemCameraTransforms.TransformType.HEAD;
		double sneakOffset = player.isSneaking() ? 0.2 : 0;
		boolean hasHelmet = player.hasItemInSlot(EntityEquipmentSlot.HEAD);
		float hScale = hasHelmet ? 1.2F : 1F;
		double helmetOffsetY = hasHelmet ? 0.12 : 0;
		double helmetOffsetZ = hasHelmet ? -0.04 : 0;
		GlStateManager.translate(0, sneakOffset, 0);
		renderer.getMainModel().bipedHead.postRender(scale);
		GlStateManager.translate(0.0, 0.07, 0.0);
		GlStateManager.scale(scale * 10, scale * 10, scale * 10);
		GlStateManager.translate(0.0F, helmetOffsetY, helmetOffsetZ);
		GlStateManager.scale(hScale, hScale, hScale);
		model.render(player, player.limbSwing, player.limbSwingAmount, player.ticksExisted, player.rotationYaw, player.rotationPitch, scale);
		GlStateManager.popMatrix();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return this.getSuperRarity(stack);
	}
	
	@Override
	public void eventPlayerTick(ItemStack stack, EntityPlayer player) {
		super.eventPlayerTick(stack, player);
		if (!player.world.isRemote) {
			TrinketHelper.applyToAccessories(player, (s)->{
	            if(s.getItem() instanceof TrinketTeddyBear) {
	                final EntityItem ei = new EntityItem(
	                		player.world,
	                		player.posX, player.posY + player.getEyeHeight(), player.posZ,
	                        s.copy()
	                );
	                ei.setPickupDelay(40);
	                s.setCount(0);
	                player.world.spawnEntity(ei);
	            }
	        });
		}
	}

}
