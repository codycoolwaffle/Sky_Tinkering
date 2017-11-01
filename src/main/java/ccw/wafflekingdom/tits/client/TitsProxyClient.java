package ccw.wafflekingdom.tits.client;

import net.minecraftforge.client.MinecraftForgeClient;

import ccw.wafflekingdom.tits.common.TitsProxyCommon;
import ccw.wafflekingdom.tits.tits;
import tconstruct.client.FlexibleToolRenderer;

@SuppressWarnings({"unused"})
public class TitsProxyClient extends TitsProxyCommon
{
	public void registerRenderers()
	{
		MinecraftForgeClient.registerItemRenderer(tits.skyCrook, new FlexibleToolRenderer());
		MinecraftForgeClient.registerItemRenderer(tits.skyHammer, new FlexibleToolRenderer());
	}
	
	public void initialize()
	{
		registerRenderers();
	}
}