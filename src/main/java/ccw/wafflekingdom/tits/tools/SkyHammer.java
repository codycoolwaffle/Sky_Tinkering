package ccw.wafflekingdom.tits.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.Map;

import ccw.wafflekingdom.tits.tits;
import ccw.wafflekingdom.tits.utils.skyHarvestTool;
import exnihilo.items.hammers.IHammer;
import exnihilo.registries.HammerRegistry;
import exnihilo.registries.helpers.Smashable;
import exnihilo.utils.ItemInfo;
import tconstruct.tools.TinkerTools;

public class SkyHammer extends skyHarvestTool implements IHammer
{
	public SkyHammer(boolean enabled)
	{
		super(3);
		this.enabled = enabled;
	}
	
	public String getIconSuffix(int i)
	{
		switch(i)
		{
			case 0:
				return "_skyhammer_head";
			case 1:
				return "_skyhammer_head_broken";
			case 2:
				return "_skyhammer_handle";
			case 3:
				return "_skyhammer_accessory";
			default:
				return "";
		}
	}
	
	public String getEffectSuffix()
	{
		return "_skyhammer_effect";
	}
	
	public String getDefaultFolder()
	{
		return "skyhammer";
	}
	
	public String getDefaultTexturePath()
	{
		return "tinker:" + getDefaultFolder();
	}
	
	public Item getHeadItem()
	{
		return TinkerTools.hammerHead;
	}
	
	public Item getAccessoryItem()
	{
		return TinkerTools.binding;
	}
	
	public Item getHandleItem()
	{
		return TinkerTools.toolRod;
	}
	
	public String[] getTraits()
	{
		return new String[]{"tool", "utility", "melee", "harvest", "weapon"};
	}
	
	protected Material[] getEffectiveMaterials()
	{
		return new Material[0];
	}
	
	protected String getHarvestType()
	{
		return "";
	}
	
	public String getModifyType()
	{
		return "Tool";
	}
	
	public void prepSmashables()
	{
		for(Map.Entry<ItemInfo, ArrayList<Smashable>> itemInfoArrayListEntry : HammerRegistry
				.getRewards().entrySet())
		{
			ArrayList<Smashable> next = itemInfoArrayListEntry.getValue();
			for(Smashable smashingThrashingDashing : next)
			{
				tits.logger.info(smashingThrashingDashing.item.getUnlocalizedName());
				rewards.add(smashingThrashingDashing);
			}
		}
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
			return HammerRegistry.registered(new ItemStack(block, 1, meta)) ?
			       item.getTagCompound().getCompoundTag("InfiTool").getInteger("MiningSpeed")
			       * 0.0075F : 1F;
		}
	}
	
	@SuppressWarnings({"all"})
	private static ArrayList<Smashable> rewards = new ArrayList<Smashable>();
	
	@SuppressWarnings({"unchecked", "rawtypes", "unused"})
	public static Block[] getBlocks()
	{
		ArrayList<Block> blocks = new ArrayList();
		
		for(Smashable reward : rewards)
		{
			if(!blocks.contains(reward.source))
			{
				blocks.add(reward.source);
			}
		}
		
		return blocks.toArray(new Block[blocks.size()]);
	}
	
	public boolean isHammer(ItemStack itemStack)
	{
		return true;
	}
	
	@Override
	public boolean isEffective(Block block, int meta)
	{
		boolean out = false;
		
		for(Smashable reward : rewards)
		{
			if(reward.source.equals(block))
			{
				out = true;
			}
		}
		
		//tits.logger.info(out);
		return out || this.isEffective(block.getMaterial());
	}
	
	@Override
	public boolean isEffective(Material material)
	{
		boolean out = false;
		
		for(Smashable reward : rewards)
		{
			if(reward.source.getMaterial().equals(material))
			{
				out = true;
			}
		}
		
		//tits.logger.info(out);
		return out;
	}
}
