package ccw.wafflekingdom.tits.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import ccw.wafflekingdom.tits.tits;
import ccw.wafflekingdom.tits.utils.skyHarvestTool;
import exnihilo.utils.CrookUtils;
import tconstruct.tools.TinkerTools;

public class SkyCrook extends skyHarvestTool
{
	public SkyCrook(boolean enabled)
	{
		super(0);
		this.enabled = enabled;
	}
	
	protected Material[] getEffectiveMaterials()
	{
		return new Material[0];
	}
	
	protected String getHarvestType()
	{
		return "";
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
	
	@Override
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
	
	@Override
	public boolean isEffective(Block block, int meta)
	{
		boolean out = block.isLeaves(tits.proxy.getWorld(), 0, 0, 0);
		
		//tits.logger.info(out);
		return out;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack item, int X, int Y, int Z, EntityPlayer player)
	{
		CrookUtils.doCrooking(item, X, Y, Z, player);
		return false;
	}
}
