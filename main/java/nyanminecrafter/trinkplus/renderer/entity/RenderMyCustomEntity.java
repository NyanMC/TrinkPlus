package nyanminecrafter.trinkplus.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMyCustomEntity<T extends EntityLiving> extends RenderEnderman {

	public RenderMyCustomEntity(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public float prepareScale(EntityEnderman entitylivingbaseIn, float partialTicks) {
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.preRenderCallback(entitylivingbaseIn, partialTicks);

		float h = entitylivingbaseIn.height;
		float w = entitylivingbaseIn.width;
		float aH = h / 2.9F;
		GlStateManager.scale(aH, aH, aH);
		float f = 0.0625F;
		GlStateManager.translate(0.0F, -1.501F, 0.0F);
		//		float f = super.prepareScale(entitylivingbaseIn, partialTicks);
		return f;
	}

}
