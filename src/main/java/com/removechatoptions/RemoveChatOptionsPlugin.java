package com.removechatoptions;

import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@PluginDescriptor(
	name = "Remove Chat Options",
	description = "Remove Add friend, Add ignore, Message, and Report menu options",
	tags = {"chat"}
)
public class RemoveChatOptionsPlugin extends Plugin {
	private static final String ADD_FRIEND = "Add friend";
	private static final String ADD_IGNORE = "Add ignore";
	private static final String MESSAGE = "Message";
	private static final String REPORT = "Report";

	private static final Set<String> OPTIONS_TO_REMOVE = Set.of(ADD_FRIEND, ADD_IGNORE, MESSAGE, REPORT);

	@Inject
	private Client client;

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded entry) {
		String option = entry.getOption();

		if (OPTIONS_TO_REMOVE.contains(option)) {
			MenuEntry[] menuEntries = client.getMenuEntries();

			List<MenuEntry> newMenuEntriesList = new LinkedList<>();

			for (MenuEntry menuEntry : menuEntries) {
				if (!menuEntry.getOption().equals(option)) {
					newMenuEntriesList.add(menuEntry);
				}
			}

			client.setMenuEntries(newMenuEntriesList.toArray(new MenuEntry[0]));
		}
	}
}
