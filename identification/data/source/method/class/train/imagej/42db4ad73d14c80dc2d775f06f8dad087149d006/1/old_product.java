public static <T extends PluginInfo<?>> void getPluginsOfClass(
		final String className, final List<? extends PluginInfo<?>> srcList,
		final List<T> destList)
	{
		for (final PluginInfo<?> info : srcList) {
			if (info.getClassName().equals(className)) {
				@SuppressWarnings("unchecked")
				final T match = (T) info;
				destList.add(match);
			}
		}
	}