public static void callExtensionPoint(final LogChannelInterface log, final String id, final Object object) throws KettleException {
    PluginRegistry registry = PluginRegistry.getInstance();
    List<PluginInterface> extensionPointPlugins = registry.getPlugins(ExtensionPointPluginType.class);
    for (PluginInterface extensionPointPlugin : extensionPointPlugins) {
      if (id.equals(extensionPointPlugin.getName())) {
        ExtensionPointInterface extensionPoint = (ExtensionPointInterface) registry.loadClass(extensionPointPlugin);
        log.logDetailed("Handling extension point for plugin with id '"+extensionPointPlugin.getIds()[0]+"' and extension point id '"+id+"'");
        extensionPoint.callExtensionPoint(log, object);
      }
    }
  }