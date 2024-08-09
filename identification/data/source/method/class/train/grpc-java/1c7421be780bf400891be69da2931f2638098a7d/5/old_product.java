@Deprecated
  public void handleResolvedAddressGroups(
      List<EquivalentAddressGroup> servers,
      @NameResolver.ResolutionResultAttr Attributes attributes) {
    handleResolvedAddresses(
        ResolvedAddresses.newBuilder().setAddresses(servers).setAttributes(attributes).build());
  }