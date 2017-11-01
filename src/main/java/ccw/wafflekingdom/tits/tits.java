package ccw.wafflekingdom.tits;

import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ccw.wafflekingdom.tits.common.TitsProxyCommon;
import ccw.wafflekingdom.tits.tools.SkyCrook;
import ccw.wafflekingdom.tits.tools.SkyHammer;
import ccw.wafflekingdom.tits.utils.skyHarvestTool;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import exnihilo.data.ModData;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.client.TConstructClientRegistry;
import tconstruct.library.client.ToolGuiElement;
import tconstruct.library.tools.HarvestTool;
import tconstruct.tools.TinkerTools;

@SuppressWarnings({"all"})
@Mod(modid = tits.MODID, name = "TinkersInTheSky", version = tits.VERSION/*, dependencies = "required-after:Forge@[10.13.3.1384,11.14);required-after:TConstruct;required-after:Mantle;required-after:exnihilo"*/)
public class tits
{
	@SuppressWarnings({"all"})
	public static final String VERSION = "1.0";
	@SuppressWarnings({"all"})
	public static final String MODID = "tits";
	public static final Logger logger = LogManager.getLogger(MODID);
	
	@SidedProxy(clientSide = "ccw.wafflekingdom.tits.client.TitsProxyClient",
	            serverSide = "ccw.wafflekingdom.tits.client.TitsProxyCommon")
	@SuppressWarnings({"all"})
	public static TitsProxyCommon proxy;
	
	public static SkyHammer skyHammer;
	public static SkyCrook skyCrook;
	
	@EventHandler
	@SuppressWarnings({"unused"})
	public void preInit(FMLPreInitializationEvent event)
	{
		tits.skyHammer = new SkyHammer(ModData.ALLOW_HAMMERS);
		tits.skyCrook = new SkyCrook(ModData.ALLOW_CROOKS);
		
		Item[] tools = {tits.skyHammer, tits.skyCrook};
		String[] toolStrings = {"skyhammer", "skycrook"};
		
		for(int i = 0; i < tools.length; i++)
		{
			registerTCon((skyHarvestTool) tools[i], toolStrings[i]);
		}
		//logger.info(TConstructRegistry.itemDirectory);
	}
	
	@EventHandler
	@SuppressWarnings({"unused"})
	public void init(FMLInitializationEvent event)
	{
		logger.info("Boobs > Butts");
		TConstructClientRegistry.toolButtons
				.add(new ToolGuiElement(1, 5, 4, new int[]{11, 0, 1, 13}, new int[]{2, 3, 3, 13},
				                        StatCollector
						                        .translateToLocal("gui.toolstation.exhammer.name"),
				                        StatCollector
						                        .translateToLocal("gui.toolstation.exhammer.desc"),
				                        "tinker", "textures/gui/icons.png"));
		TConstructRegistry.addToolRecipe(skyHammer, TinkerTools.hammerHead, TinkerTools.toolRod,
		                                 TinkerTools.binding);
		TConstructClientRegistry.toolButtons
				.add(new ToolGuiElement(0, 6, 4, new int[]{0, 0, 0, 13}, new int[]{3, 3, 3, 13},
				                        StatCollector
						                        .translateToLocal("gui.toolstation.excrook.name"),
				                        StatCollector
						                        .translateToLocal("gui.toolstation.excrook.desc"),
				                        "tinker", "textures/gui/icons.png"));
		TConstructRegistry.addToolRecipe(skyCrook, TinkerTools.toolRod, TinkerTools.toolRod,
		                                 TinkerTools.toolRod);
		//logger.info(TConstructRegistry.getToolMapping());
	}
	
	@EventHandler
	@SuppressWarnings({"unused"})
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.initialize();
		skyHammer.prepSmashables();
		//logger.info("Printing Tier 1 Tool Buttons:");
		for(ToolGuiElement butt : TConstructClientRegistry.toolButtons)
		{
			//logger.info(butt.title);
		}
		//logger.info("Printing Tier 2 Tool Buttons:");
		for(ToolGuiElement butt : TConstructClientRegistry.tierTwoButtons)
		{
			//logger.info(butt.title);
		}
	}
	
	public void registerTCon(skyHarvestTool tool, String toolString)
	{
		if(tool.isEnabled())
		{
			GameRegistry.registerItem(tool, toolString);
		}
		TConstructRegistry.addItemToDirectory(toolString, tool);
	}
}