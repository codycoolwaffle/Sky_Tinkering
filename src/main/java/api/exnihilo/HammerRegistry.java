package exnihilo.registries;

import exnihilo.registries.helpers.Smashable;
import exnihilo.utils.ItemInfo;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class HammerRegistry
{
	public static boolean registered(ItemStack item){return true;}
	public static HashMap<ItemInfo, ArrayList<Smashable>> getRewards(){return new HashMap<ItemInfo, ArrayList<Smashable>>();}
}