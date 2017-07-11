package csokicraft.forge18.magicrings;

import csokicraft.forge18.magicrings.item.ItemMagicRing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerItemModels() {
		ItemModelMesher imm=Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		for(int meta=0;meta<ItemMagicRing.ringsNo();meta++) imm.register(MagicRings.rings, meta, new ModelResourceLocation(MagicRings.MODID+":rings.0", "inventory"));
		ModelBakery.addVariantName(MagicRings.rings, MagicRings.MODID+":rings.0");
	}
}
