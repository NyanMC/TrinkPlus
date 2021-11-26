package nyanminecrafter.trinkplus.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub
		super.preInit(event);
	}
	
	@Override
	public void Init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		super.Init(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		super.postInit(event);
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName().toString(), id));
	}
	
}
