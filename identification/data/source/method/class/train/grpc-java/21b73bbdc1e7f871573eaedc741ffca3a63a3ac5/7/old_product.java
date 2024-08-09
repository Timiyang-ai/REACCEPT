@Override
    public void handleResolvedAddressGroups(
        List<EquivalentAddressGroup> servers, Attributes attributes) {
      if (attributes.keys().contains(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG)) {
        Factory newlbf = decideLoadBalancerFactory(
            servers, attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG));
        if (newlbf != null && newlbf != delegateFactory) {
          helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptySubchannelPicker());
          getDelegate().shutdown();
          setDelegateFactory(newlbf);
          setDelegate(getDelegateFactory().newLoadBalancer(helper));
        }
      }
      getDelegate().handleResolvedAddressGroups(servers, attributes);
    }