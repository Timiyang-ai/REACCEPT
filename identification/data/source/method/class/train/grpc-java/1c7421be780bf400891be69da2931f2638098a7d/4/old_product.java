@Deprecated
  public void handleResolvedAddressGroups(
      List<EquivalentAddressGroup> servers,
      @NameResolver.ResolutionResultAttr Attributes attributes) {
    handleResolvedAddresses(
        ResolvedAddresses.newBuilder().setServers(servers).setAttributes(attributes).build());
  }