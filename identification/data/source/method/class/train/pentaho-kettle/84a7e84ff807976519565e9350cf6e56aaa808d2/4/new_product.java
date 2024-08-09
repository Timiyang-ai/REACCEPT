public void addExtensionPoint( PluginInterface extensionPointPlugin ) {
    final PluginRegistry registry = PluginRegistry.getInstance();
    try {
      ExtensionPointInterface extensionPoint = (ExtensionPointInterface) registry.loadClass( extensionPointPlugin );
      addExtensionPoint( extensionPointPlugin.getName(), extensionPoint );
    } catch ( KettlePluginException e ) {
      log.logError( "Unable to load extension point for name = [" + ( extensionPointPlugin != null ? extensionPointPlugin.getName() : "null" ) + "]", e );
    }
  }