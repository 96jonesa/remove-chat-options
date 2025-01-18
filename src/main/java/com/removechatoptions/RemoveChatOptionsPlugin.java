package com.removechatoptions;

import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.LinkedList;
import java.util.List;

@PluginDescriptor(
	name = "Remove Chat Options",
	description = "Remove all chatbox menu options",
	tags = {"chat"}
)
public class RemoveChatOptionsPlugin extends Plugin {
	@Inject
	private Client client;

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded menuEntryAdded) {
		MenuEntry[] menuEntries = client.getMenuEntries();

		List<MenuEntry> newMenuEntriesList = new LinkedList<>();

		for (MenuEntry menuEntry : menuEntries) {
			final int componentId = menuEntry.getParam1();
			int groupId = WidgetUtil.componentToInterface(componentId);

			if (groupId != InterfaceID.CHATBOX) {
				newMenuEntriesList.add(menuEntry);
			}
		}

		client.setMenuEntries(newMenuEntriesList.toArray(new MenuEntry[0]));
	}
}
