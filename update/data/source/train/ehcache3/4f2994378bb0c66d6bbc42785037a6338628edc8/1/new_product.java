private void configure(ClientDescriptor clientDescriptor, ConfigureStoreManager message) throws ClusterException {
    ClientState clientState = this.clientStateMap.get(clientDescriptor);
    if (clientState == null) {
      throw new LifecycleException("Client " + clientDescriptor + " is not connected to the Clustered Tier Manager");
    }
    ehcacheStateService.configure(message);
    clientState.attach();
  }