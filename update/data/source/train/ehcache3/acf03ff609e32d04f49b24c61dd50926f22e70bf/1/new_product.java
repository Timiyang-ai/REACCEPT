private void configure(ClientDescriptor clientDescriptor, ConfigureStoreManager message) throws ClusteredEhcacheException {
    ClientState clientState = this.clientStateMap.get(clientDescriptor);
    if (clientState == null) {
      throw new LifecycleException("Client " + clientDescriptor + " is not connected to the Clustered Store Manager");
    }
    if (!isConfigured()) {
      LOGGER.info("Configuring server-side clustered store manager");
      ServerSideConfiguration configuration = message.getConfiguration();

      this.defaultServerResource = configuration.getDefaultServerResource();
      if (this.defaultServerResource != null) {
        if (!offHeapResourceIdentifiers.contains(this.defaultServerResource)) {
          throw new ResourceConfigurationException("Default server resource '" + this.defaultServerResource
              + "' is not defined. Available resources are: " + offHeapResourceIdentifiers);
        }
      }

      this.sharedResourcePools = createPools(resolveResourcePools(configuration));
      this.stores = new HashMap<String, ServerStoreImpl>();

      clientState.attach();
    } else {
      throw new InvalidStoreManagerException("Clustered Store Manager already configured");
    }
  }