package nyanminecrafter.trinkplus.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class MyCustomEntityRenderFactory<T extends EntityLiving> implements IRenderFactory<EntityEnderman> {

	@Override
	public Render<? super EntityEnderman> createRenderFor(RenderManager manager) {
		return new RenderMyCustomEntity(manager);
	}
}
