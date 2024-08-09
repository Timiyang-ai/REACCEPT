@SuppressWarnings("deprecation")
  public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
    if (recursionCount++ == 0) {
      handleResolvedAddressGroups(
          resolvedAddresses.getAddresses(), resolvedAddresses.getAttributes());
    }
    recursionCount = 0;
  }