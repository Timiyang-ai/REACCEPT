  @Deprecated
  @Test
  public void handleResolvedAddresses_delegatesToHandleResolvedAddressGroups() {
    final AtomicReference<List<EquivalentAddressGroup>> serversCapture = new AtomicReference<>();
    final AtomicReference<Attributes> attrsCapture = new AtomicReference<>();

    LoadBalancer balancer = new LoadBalancer() {
        @Override
        public void handleResolvedAddressGroups(
            List<EquivalentAddressGroup> servers, Attributes attrs) {
          serversCapture.set(servers);
          attrsCapture.set(attrs);
        }

        @Override
        public void handleNameResolutionError(Status error) {
        }

        @Override
        public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo state) {
        }

        @Override
        public void shutdown() {
        }
      };

    List<EquivalentAddressGroup> servers = Arrays.asList(
        new EquivalentAddressGroup(new SocketAddress(){}),
        new EquivalentAddressGroup(new SocketAddress(){}));
    balancer.handleResolvedAddresses(
        ResolvedAddresses.newBuilder().setAddresses(servers).setAttributes(attrs).build());
    assertThat(serversCapture.get()).isEqualTo(servers);
    assertThat(attrsCapture.get()).isEqualTo(attrs);
  }