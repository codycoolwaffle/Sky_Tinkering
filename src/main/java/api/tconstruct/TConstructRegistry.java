package tconstruct.library;

import net.minecraft.item.Item;
import tconstruct.library.tools.ToolCore;

import java.util.ArrayList;
import java.util.HashMap;

public class TConstructRegistry
{
	public static HashMap<String, Item> itemDirectory = new HashMap();
	
	public static void addItemToDirectory(String toolString, Item tool)
	{
	}
	
	public static void addToolRecipe(ToolCore skyhammer, Item... parts)
	{
	}
	
	public static ArrayList<ToolCore> getToolMapping()
	{
		return null;
	}
}