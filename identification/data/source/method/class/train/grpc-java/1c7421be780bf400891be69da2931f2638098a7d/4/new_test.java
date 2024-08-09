  @Deprecated
  @Test
  public void handleResolvedAddressGroups_delegatesToHandleResolvedAddresses() {
    final AtomicReference<ResolvedAddresses> resultCapture = new AtomicReference<>();

    LoadBalancer balancer = new LoadBalancer() {
        @Override
        public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
          resultCapture.set(resolvedAddresses);
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
    balancer.handleResolvedAddressGroups(servers, attrs);
    assertThat(resultCapture.get()).isEqualTo(
        ResolvedAddresses.newBuilder().setAddresses(servers).setAttributes(attrs).build());
  }