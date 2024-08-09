private EhcacheEntityResponse configure(ClientDescriptor clientDescriptor, ConfigureStoreManager message) {
    ClientState clientState = this.clientStateMap.get(clientDescriptor);
    if (clientState == null) {
      return responseFactory.failure(new IllegalStateException("Client " + clientDescriptor + " is not connected to the Clustered Store Manager"));
    }
    if (!isConfigured()) {
      LOGGER.info("Configuring server-side clustered store manager");
      ServerSideConfiguration configuration = message.getConfiguration();

      this.defaultServerResource = configuration.getDefaultServerResource();
      if (this.defaultServerResource != null) {
        OffHeapResource source = services.getService(OffHeapResourceIdentifier.identifier(this.defaultServerResource));
        if (source == null) {
          return responseFactory.failure(new IllegalArgumentException("Default server resource '" + this.defaultServerResource
              + "' is not defined"));
        }
      }

      try {
        this.sharedResourcePools = createPools(configuration.getResourcePools());
      } catch (RuntimeException e) {
        return responseFactory.failure(e);
      }
      this.stores = new HashMap<String, ServerStoreImpl>();

      clientState.attach();
      return responseFactory.success();
    } else {
      return responseFactory.failure(new IllegalStateException("Clustered Store Manager already configured"));
    }
  }