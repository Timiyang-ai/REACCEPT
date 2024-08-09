@Override
    public void handleResolvedAddressGroups(
        List<EquivalentAddressGroup> servers, Attributes attributes) {
      if (attributes.get(ATTR_LOAD_BALANCING_CONFIG) != null) {
        throw new IllegalArgumentException(
            "Unexpected ATTR_LOAD_BALANCING_CONFIG from upstream: "
            + attributes.get(ATTR_LOAD_BALANCING_CONFIG));
      }
      Map<String, ?> configMap = attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
      PolicySelection selection;
      try {
        selection = decideLoadBalancerProvider(servers, configMap);
      } catch (PolicyException e) {
        Status s = Status.INTERNAL.withDescription(e.getMessage());
        helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new FailingPicker(s));
        delegate.shutdown();
        delegateProvider = null;
        delegate = new NoopLoadBalancer();
        return;
      }

      if (delegateProvider == null
          || !selection.provider.getPolicyName().equals(delegateProvider.getPolicyName())) {
        helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptyPicker());
        delegate.shutdown();
        delegateProvider = selection.provider;
        LoadBalancer old = delegate;
        delegate = delegateProvider.newLoadBalancer(helper);
        helper.getChannelLogger().log(
            ChannelLogLevel.INFO, "Load balancer changed from {0} to {1}",
            old.getClass().getSimpleName(), delegate.getClass().getSimpleName());
      }

      if (selection.config != null) {
        helper.getChannelLogger().log(
            ChannelLogLevel.DEBUG, "Load-balancing config: {0}", selection.config);
        attributes =
            attributes.toBuilder().set(ATTR_LOAD_BALANCING_CONFIG, selection.config).build();
      }

      LoadBalancer delegate = getDelegate();
      if (selection.serverList.isEmpty()
          && !delegate.canHandleEmptyAddressListFromNameResolution()) {
        delegate.handleNameResolutionError(
            Status.UNAVAILABLE.withDescription(
                "Name resolver returned no usable address. addrs="
                + servers + ", attrs=" + attributes));
      } else {
        delegate.handleResolvedAddressGroups(selection.serverList, attributes);
      }
    }