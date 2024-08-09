public void addExtensionPoint( PluginInterface extensionPointPlugin ) {
    for ( String id : extensionPointPlugin.getIds() ) {
      extensionPointPluginMap.put( extensionPointPlugin.getName(), id, createLazyLoader( extensionPointPlugin ) );
    }
  }