package csokicraft.forge18.magicrings;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import csokicraft.forge18.magicrings.item.ItemMagicRing;

@Mod(name=MagicRings.MODID, modid=MagicRings.MODID,version=MagicRings.VERSION)
public class MagicRings {
	public static final String MODID="MagicRings",
							   VERSION="1.0";
	
	public static Item rings;
	
	@SidedProxy(clientSide="csokicraft.forge18.magicrings.ClientProxy", serverSide="csokicraft.forge18.magicrings.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void onInit(FMLInitializationEvent e){
		rings=new ItemMagicRing().setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName(MODID+":rings");
		GameRegistry.registerItem(rings, "rings");
		proxy.registerItemModels();
		registerRecipes();
	}

	private void registerRecipes() {
		ItemStack component[]=new ItemStack[]{
				new ItemStack(Items.feather),
				new ItemStack(Items.emerald),
				new ItemStack(Items.sugar),
				new ItemStack(Items.ghast_tear),
				new ItemStack(Items.blaze_powder),
				new ItemStack(Items.magma_cream),
				new ItemStack(Items.rabbit_foot),
				new ItemStack(Items.golden_carrot),
				new ItemStack(Items.fish, 1, 3),
				new ItemStack(Blocks.obsidian),
				new ItemStack(Items.golden_apple),
				new ItemStack(Items.redstone)
		};
		for(int i=0;i<ItemMagicRing.ringsNo();i++)
			GameRegistry.addRecipe(new ItemStack(rings, 1, i), "cic", "idi", "cic",
					'i', Items.gold_ingot, 'd', Items.diamond, 'c', component[i]);
	}
}
