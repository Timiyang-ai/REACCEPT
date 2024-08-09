public void addExtensionPoint( PluginInterface extensionPointPlugin ) {
    lock.writeLock().lock();
    try {
      for ( String id : extensionPointPlugin.getIds() ) {
        extensionPointPluginMap.put( extensionPointPlugin.getName(), id, createLazyLoader( extensionPointPlugin ) );
      }
    } finally {
      lock.writeLock().unlock();
    }
  }