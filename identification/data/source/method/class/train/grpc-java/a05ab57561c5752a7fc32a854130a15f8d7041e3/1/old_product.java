@SuppressWarnings("deprecation")
  public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
    handleResolvedAddressGroups(resolvedAddresses.getServers(), resolvedAddresses.getAttributes());
  }