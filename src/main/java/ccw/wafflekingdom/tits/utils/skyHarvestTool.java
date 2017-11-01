package ccw.wafflekingdom.tits.utils;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import tconstruct.library.tools.HarvestTool;

public abstract class skyHarvestTool extends HarvestTool
{
	protected boolean enabled;
	
	public skyHarvestTool(int baseDamage)
	{
		super(baseDamage);
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	protected Material[] getEffectiveMaterials()
	{
		return new Material[0];
	}
	
	protected String getHarvestType()
	{
		return null;
	}
	
	public String getIconSuffix(int i)
	{
		return null;
	}
	
	public String getEffectSuffix()
	{
		return null;
	}
	
	public String getDefaultFolder()
	{
		return null;
	}
	
	public Item getHeadItem()
	{
		return null;
	}
	
	public Item getAccessoryItem()
	{
		return null;
	}
}
