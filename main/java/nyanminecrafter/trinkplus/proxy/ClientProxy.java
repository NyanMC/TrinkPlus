package nyanminecrafter.trinkplus.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nyanminecrafter.trinkplus.entity.Alphaman;
import nyanminecrafter.trinkplus.renderer.entity.MyCustomEntityRenderFactory;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		this.registerEntityRenderers();
	}

	@Override
	public void Init(FMLInitializationEvent event) {
		super.Init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName().toString(), id));
	}

	public void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(Alphaman.class, new MyCustomEntityRenderFactory<Alphaman>());
	}

}
