@SuppressWarnings("deprecation")
  public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
    handleResolvedAddressGroups(
        resolvedAddresses.getAddresses(), resolvedAddresses.getAttributes());
  }