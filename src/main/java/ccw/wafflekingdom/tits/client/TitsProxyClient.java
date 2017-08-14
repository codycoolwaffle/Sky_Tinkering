package ccw.wafflekingdom.tits.client;

import net.minecraftforge.client.MinecraftForgeClient;

import ccw.wafflekingdom.tits.common.TitsProxyCommon;
import ccw.wafflekingdom.tits.tits;
import tconstruct.client.FlexibleToolRenderer;

@SuppressWarnings({"unused"})
public class TitsProxyClient extends TitsProxyCommon
{
	@Override
	public void registerRenderers()
	{
		MinecraftForgeClient.registerItemRenderer(tits.skyHammer, new FlexibleToolRenderer());
	}
	
	@Override
	public void initialize()
	{
		registerRenderers();
	}
}