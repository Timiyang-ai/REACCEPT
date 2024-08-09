private EhcacheEntityResponse configure(ConfigureCacheManager message) {
    if (sharedResourcePools == null) {
      LOGGER.info("Configuring server-side cache manager");
      ServerSideConfiguration configuration = message.getConfiguration();

      this.defaultServerResource = configuration.getDefaultServerResource();
      if (this.defaultServerResource != null) {
        OffHeapResource source = services.getService(OffHeapResourceIdentifier.identifier(this.defaultServerResource));
        if (source == null) {
          return failure(new IllegalArgumentException("Default server resource '" + this.defaultServerResource
              + "' is not defined"));
        }
      }

      try {
        this.sharedResourcePools = createPools(configuration.getResourcePools());
      } catch (RuntimeException e) {
        return failure(e);
      }
      this.stores = new HashMap<String, ServerStoreImpl>();
      return success();
    } else {
      return failure(new IllegalStateException("Clustered Cache Manager already configured"));
    }
  }