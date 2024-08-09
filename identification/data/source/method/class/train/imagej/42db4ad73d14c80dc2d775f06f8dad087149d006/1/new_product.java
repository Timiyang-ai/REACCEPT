@Override
	public <PT extends ImageJPlugin, P extends PT> List<PluginInfo<PT>>
		getPluginsOfClass(final Class<P> pluginClass, final Class<PT> type)
	{
		final ArrayList<PluginInfo<PT>> result =
			new ArrayList<PluginInfo<PT>>();
		final String className = pluginClass.getName();
		findPluginsOfClass(className, getPluginsOfType(type), result);
		return result;
	}