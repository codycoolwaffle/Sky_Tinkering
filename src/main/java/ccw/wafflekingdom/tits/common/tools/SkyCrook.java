package ccw.wafflekingdom.tits.common.tools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

import ccw.wafflekingdom.tits.tits;
import ccw.wafflekingdom.tits.common.utils.skyHarvestTool;
import cpw.mods.fml.common.registry.GameData;
import exnihilo.registries.helpers.Smashable;
import exnihilo.utils.CrookUtils;
import iguanaman.iguanatweakstconstruct.leveling.LevelingLogic;
import tconstruct.tools.TinkerTools;

public class SkyCrook extends skyHarvestTool
{
	private boolean breakTick = false;
	
	public SkyCrook(boolean enabled)
	{
		super(0);
		this.enabled = enabled;
	}
	
	protected Material[] getEffectiveMaterials()
	{
		return new Material[]{Material.leaves};
	}
	
	public String getIconSuffix(int i)
	{
		switch(i)
		{
			case 0:
				return "_skycrook_head";
			case 1:
				return "_skycrook_head_broken";
			case 2:
				return "_skycrook_handle";
			case 3:
				return "_skycrook_accessory";
			default:
				return "";
		}
	}
	
	public String getEffectSuffix()
	{
		return "_skycrook_effect";
	}
	
	public String getDefaultFolder()
	{
		return "skycrook";
	}
	
	public String getDefaultTexturePath()
	{
		return "tinker:" + getDefaultFolder();
	}
	
	public Item getHeadItem()
	{
		return TinkerTools.toolRod;
	}
	
	public Item getAccessoryItem()
	{
		return TinkerTools.toolRod;
	}
	
	public Item getHandleItem()
	{
		return TinkerTools.toolRod;
	}
	
	public String getModifyType()
	{
		return "Tool";
	}
	
	public String[] getTraits()
	{
		return new String[]{"tool", "utility", "harvest"};
	}
	
	public float getDigSpeed(ItemStack item, Block block, int meta)
	{
		if(!item.hasTagCompound())
		{
			return 0.0F;
		}
		else
		{
			NBTTagCompound tags = item.getTagCompound();
			if(tags.getCompoundTag("InfiTool").getBoolean("Broken"))
			{
				return 0.1F;
			}
			return isEffective(block, meta) ?
			       item.getTagCompound().getCompoundTag("InfiTool").getInteger("MiningSpeed")
			       * 0.0075F : 1F;
		}
		
	}
	
	public boolean isEffective(Block block, int meta)
	{
		return block.isLeaves(tits.proxy.getWorld(), 0, 0, 0) || this.isEffective(block.getMaterial());
	}
	
	public boolean isEffective(Material material)
	{
		return material == Material.leaves;
	}
	
	public boolean onBlockStartBreak(ItemStack item, int X, int Y, int Z, EntityPlayer player)
	{
		//NBTTagCompound tags = item.getTagCompound().getCompoundTag("InfiTool");
		//tits.logger.info(tags.hasKey("ToolLevel"));
		//tits.logger.info(tags.getInteger("ToolLevel"));
		//tits.logger.info(tags.hasKey("ToolEXP"));
		//tits.logger.info(tags.getInteger("ToolEXP"));
		//tits.logger.info(tags);
		CrookUtils.doCrooking(item, X, Y, Z, player);
		
		if(player.worldObj.getBlock(X, Y, Z).isLeaves(player.worldObj, X, Y, Z))
		{
			if(breakTick)
			{
				LevelingLogic.addXP(item, player, 1);
			}
			breakTick = !breakTick;
		}
		
		return super.onBlockStartBreak(item, X, Y, Z, player);
	}
	
	public boolean onLeftClickEntity(ItemStack item, EntityPlayer player, Entity entity) {
		if (!player.worldObj.isRemote) {
			double distance = Math.sqrt(Math.pow(player.posX - entity.posX, 2.0D) + Math.pow(player.posZ - entity.posZ, 2.0D));
			double scalarX = (player.posX - entity.posX) / distance;
			double scalarZ = (player.posZ - entity.posZ) / distance;
			double velX = 0.0D - scalarX * 1.5D;
			double velZ = 0.0D - scalarZ * 1.5D;
			double velY = 0.0D;
			entity.addVelocity(velX, velY, velZ);
		}
		
		item.damageItem(1, player);
		return true;
	}
	
	public boolean itemInteractionForEntity(ItemStack item, EntityPlayer player, EntityLivingBase entity) {
		double distance = Math.sqrt(Math.pow(player.posX - entity.posX, 2.0D) + Math.pow(player.posZ - entity.posZ, 2.0D));
		double scalarX = (player.posX - entity.posX) / distance;
		double scalarZ = (player.posZ - entity.posZ) / distance;
		double velX = scalarX * 1.5D;
		double velZ = scalarZ * 1.5D;
		double velY = 0.0D;
		entity.addVelocity(velX, velY, velZ);
		item.damageItem(1, player);
		return true;
	}
}