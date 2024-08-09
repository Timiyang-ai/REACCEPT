@Test
	public void testGetPluginsOfClass() {
		// create a minimal ImageJ context
		final ImageJ context = ImageJ.createContext(PluginService.class);
		final PluginIndex pluginIndex = context.getPluginIndex();

		// add a plugin to the index
		final PluginInfo<Command> testPlugin =
			new PluginInfo<Command>(FooBar.class.getName(), Command.class);
		pluginIndex.add(testPlugin);

		// retrieve the plugin from the index, by class
		final PluginService pluginService = context.getService(PluginService.class);
		final List<PluginInfo<FooBar>> plugins =
			pluginService.getPluginsOfClass(FooBar.class);

		assertEquals(1, plugins.size());
		assertSame(testPlugin, plugins.get(0));

		final PluginInfo<FooBar> plugin = pluginService.getPlugin(FooBar.class);
		assertSame(testPlugin, plugin);
	}