@Override
    public void handleResolvedAddressGroups(
        List<EquivalentAddressGroup> servers, Attributes attributes) {
      Map<String, Object> configMap = attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
      if (configMap != null) {
        Factory newlbf = decideLoadBalancerFactory(servers, configMap);
        if (newlbf != null && newlbf != delegateFactory) {
          helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptySubchannelPicker());
          getDelegate().shutdown();
          setDelegateFactory(newlbf);
          setDelegate(getDelegateFactory().newLoadBalancer(helper));
        }
      }
      getDelegate().handleResolvedAddressGroups(servers, attributes);
    }