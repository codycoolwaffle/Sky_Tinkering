package ccw.wafflekingdom.tits.utils;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import tconstruct.library.tools.HarvestTool;

public class skyHarvestTool extends HarvestTool
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
	
	@Override
	protected Material[] getEffectiveMaterials()
	{
		return new Material[0];
	}
	
	@Override
	protected String getHarvestType()
	{
		return null;
	}
	
	@Override
	public String getIconSuffix(int i)
	{
		return null;
	}
	
	@Override
	public String getEffectSuffix()
	{
		return null;
	}
	
	@Override
	public String getDefaultFolder()
	{
		return null;
	}
	
	@Override
	public Item getHeadItem()
	{
		return null;
	}
	
	@Override
	public Item getAccessoryItem()
	{
		return null;
	}
}
