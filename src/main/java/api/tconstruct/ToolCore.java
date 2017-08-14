package tconstruct.library.tools;

import cofh.api.energy.IEnergyContainerItem;
import cofh.core.item.IEqualityOverrideItem;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.InterfaceList;
import net.minecraft.item.*;
import tconstruct.library.modifier.*;

@InterfaceList({@Interface(
		modid = "CoFHAPI|energy",
		iface = "cofh.api.energy.IEnergyContainerItem"
), @Interface(
		modid = "CoFHCore",
		iface = "cofh.core.item.IEqualityOverrideItem"
)})
public abstract class ToolCore extends Item implements IModifyable
{
	public ToolCore(int i){}
	
	public abstract String getIconSuffix(int i);
	
	public abstract String getEffectSuffix();
	
	public abstract String getDefaultFolder();
	
	public abstract String getDefaultTexturePath();
	
	public abstract Item getHeadItem();
	
	public abstract Item getAccessoryItem();
	
	public abstract Item getHandleItem();
	
	@Override
	public String getBaseTagName ()
	{
		return "InfiTool";
	}
	
	@Method(
			modid = "CoFHAPI|energy"
	)
	public int getMaxEnergyStored(ItemStack container)
	{
		return 0;
	}
}