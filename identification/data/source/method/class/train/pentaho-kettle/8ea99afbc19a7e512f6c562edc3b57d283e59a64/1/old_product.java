public static void callExtensionPoint(final LogChannelInterface log, final String id, final Object object) throws KettleException {
    PluginRegistry registry = PluginRegistry.getInstance();
    List<PluginInterface> extensionPointPlugins = registry.getPlugins(ExtensionPointPluginType.class);
    for (PluginInterface extensionPointPlugin : extensionPointPlugins) {
      if (id.equals(extensionPointPlugin.getName())) {
        ExtensionPointInterface extensionPoint = (ExtensionPointInterface) registry.loadClass(extensionPointPlugin);
        extensionPoint.callExtensionPoint(log, object);
      }
    }
  }