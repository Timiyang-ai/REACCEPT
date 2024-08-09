@Override
    public void handleResolvedAddressGroups(
        List<EquivalentAddressGroup> servers, Attributes attributes) {
      Map<String, Object> configMap = attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
      Factory newlbf = decideLoadBalancerFactory(servers, configMap);
      if (newlbf != null && newlbf != delegateFactory) {
        helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptySubchannelPicker());
        delegate.shutdown();
        delegateFactory = newlbf;
        delegate = delegateFactory.newLoadBalancer(helper);
      }
      getDelegate().handleResolvedAddressGroups(servers, attributes);
    }