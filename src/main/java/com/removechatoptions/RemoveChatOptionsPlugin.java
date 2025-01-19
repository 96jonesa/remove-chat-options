package com.removechatoptions;

import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@PluginDescriptor(
	name = "Remove Chat Options",
	description = "Remove all chatbox menu options",
	tags = {"chat"}
)
public class RemoveChatOptionsPlugin extends Plugin {
	private static final int REPORT_BUTTON_COMPONENT_ID = 10616863;

	private static final Set<Integer> chatboxTabComponentIds = Set.of(
		ComponentID.CHATBOX_TAB_PRIVATE,
		ComponentID.CHATBOX_TAB_ALL,
		ComponentID.CHATBOX_TAB_CHANNEL,
		ComponentID.CHATBOX_TAB_CLAN,
		ComponentID.CHATBOX_TAB_GAME,
		ComponentID.CHATBOX_TAB_TRADE,
		ComponentID.CHATBOX_TAB_PUBLIC,
		REPORT_BUTTON_COMPONENT_ID
	);

	@Inject
	private Client client;

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded entry) {
		MenuEntry[] menuEntries = client.getMenuEntries();

		List<MenuEntry> newMenuEntriesList = new LinkedList<>();

		for (MenuEntry menuEntry : menuEntries) {
			final int componentId = menuEntry.getParam1();
			int groupId = WidgetUtil.componentToInterface(componentId);

			if (groupId != InterfaceID.CHATBOX || chatboxTabComponentIds.contains(componentId)) {
				newMenuEntriesList.add(menuEntry);
			}
		}

		client.setMenuEntries(newMenuEntriesList.toArray(new MenuEntry[0]));
	}
}
