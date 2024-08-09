public void addExtensionPoint( PluginInterface extensionPointPlugin ) {
    final PluginRegistry registry = PluginRegistry.getInstance();
    try {
      ExtensionPointInterface extensionPoint = registry.loadClass( extensionPointPlugin, ExtensionPointInterface.class );
      for ( String id : extensionPointPlugin.getIds() ) {
        extensionPointPluginMap.put( extensionPointPlugin.getName(), id, extensionPoint );
      }
    } catch ( Exception e ) {
      getLog().logError( "Unable to load extension point for name = [" + ( extensionPointPlugin != null ? extensionPointPlugin.getName() : "null" ) + "]", e );
    }
  }