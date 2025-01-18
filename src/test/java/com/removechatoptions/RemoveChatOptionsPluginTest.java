package com.removechatoptions;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RemoveChatOptionsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RemoveChatOptionsPlugin.class);
		RuneLite.main(args);
	}
}
