@SuppressWarnings("deprecation")
  public void handleResolvedAddressGroups(
      List<EquivalentAddressGroup> servers, Attributes attributes) {
    ArrayList<ResolvedServerInfoGroup> serverInfoGroups =
        new ArrayList<ResolvedServerInfoGroup>(servers.size());
    for (EquivalentAddressGroup eag : servers) {
      ResolvedServerInfoGroup.Builder serverInfoGroupBuilder =
          ResolvedServerInfoGroup.builder(eag.getAttributes());
      for (SocketAddress addr : eag.getAddresses()) {
        serverInfoGroupBuilder.add(new ResolvedServerInfo(addr));
      }
      serverInfoGroups.add(serverInfoGroupBuilder.build());
    }
    handleResolvedAddresses(serverInfoGroups, attributes);
  }