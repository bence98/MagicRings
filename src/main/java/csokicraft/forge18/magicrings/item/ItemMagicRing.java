package csokicraft.forge18.magicrings.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import baubles.common.items.ItemRing;

public class ItemMagicRing extends ItemRing{
	public int gatherRange=7;
	public static int specRingsNo=2,
					  potRingsNo=10;
	
	
	public static int ringsNo() {
		return specRingsNo+potRingsNo;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack arg0, World arg1,
			EntityPlayer arg2) {
		onEquipped(arg0, arg2);
		return super.onItemRightClick(arg0, arg1, arg2);
	}
	
	@Override
	public void getSubItems(Item i, CreativeTabs ct, List l){
		for(int meta=0;meta<ringsNo();meta++) l.add(new ItemStack(i, 1, meta));
	}
	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player){
		EntityPlayer p;
		if(player instanceof EntityPlayer)
			p=(EntityPlayer) player;
		else return;
		p.getFoodStats().addExhaustion(.1f);
		if(itemstack.getItemDamage()==1) useGatherRing(p);
		else if(itemstack.getItemDamage()>=2) usePotionRing(p, itemstack.getItemDamage()-2);
		
	}
	
	private void useGatherRing(EntityPlayer p) {
		World w=p.worldObj;
		AxisAlignedBB region=AxisAlignedBB.fromBounds(p.posX+gatherRange, p.posY+gatherRange, p.posZ+gatherRange, p.posX-gatherRange, p.posY-gatherRange, p.posZ-gatherRange);//new AxisAlignedBB(p.getPosition().add(gatherRange, gatherRange, gatherRange), p.getPosition().add(-gatherRange, -gatherRange, -gatherRange));
		List ents=w.getEntitiesWithinAABBExcludingEntity(p, region);
		Iterator it = ents.iterator();
		while(it.hasNext()){
			Entity ent = (Entity) it.next();
			if(ent instanceof EntityItem || ent instanceof EntityXPOrb)
				ent.setPosition(p.posX, p.posY, p.posZ);
		}
	}

	private void usePotionRing(EntityPlayer p, int i) {
		int potionId=0;
		switch(i){
		case 0:
			potionId=Potion.moveSpeed.id;
			break;
		case 1:
			potionId=Potion.regeneration.id;
			break;
		case 2:
			potionId=Potion.damageBoost.id;
			break;
		case 3:
			potionId=Potion.fireResistance.id;
			break;
		case 4:
			potionId=Potion.jump.id;
			break;
		case 5:
			potionId=Potion.nightVision.id;
			break;
		case 6:
			potionId=Potion.waterBreathing.id;
			break;
		case 7:
			potionId=Potion.resistance.id;
			break;
		case 8:
			potionId=Potion.absorption.id;
			break;
		default:
			potionId=Potion.digSpeed.id;
		}
		p.addPotionEffect(new PotionEffect(potionId, 40, 1, true, false));
	}

	public void onEquipped(ItemStack itemstack, EntityLivingBase player){
		EntityPlayer p;
		if(player instanceof EntityPlayer)
			p=(EntityPlayer) player;
		else return;
		switch(itemstack.getItemDamage()){
		case 0:
			setFlight(p, true);
			break;
		}
		super.onEquipped(itemstack, player);
	}
	
	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player){
		EntityPlayer p;
		if(player instanceof EntityPlayer)
			p=(EntityPlayer) player;
		else return;
		switch(itemstack.getItemDamage()){
		case 0:
			setFlight(p, false);
			break;
		}
	}
	
	private void setFlight(EntityPlayer p, boolean f){
		if(p.capabilities.isCreativeMode) return;
		p.capabilities.allowFlying=f;
		if(!f){
			p.capabilities.isFlying=f;
			p.isAirBorne=f;
		}
	}
}
