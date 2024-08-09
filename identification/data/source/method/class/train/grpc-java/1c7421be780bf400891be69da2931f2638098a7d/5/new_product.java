@Deprecated
  public void handleResolvedAddressGroups(
      List<EquivalentAddressGroup> servers,
      @NameResolver.ResolutionResultAttr Attributes attributes) {
    if (recursionCount++ == 0) {
      handleResolvedAddresses(
          ResolvedAddresses.newBuilder().setAddresses(servers).setAttributes(attributes).build());
    }
    recursionCount = 0;
  }