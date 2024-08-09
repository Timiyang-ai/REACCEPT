@Override
    public void handleResolvedAddressGroups(
        List<EquivalentAddressGroup> servers, Attributes attributes) {
      Map<String, Object> configMap = attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
      Factory newlbf;
      try {
        newlbf = decideLoadBalancerFactory(servers, configMap);
      } catch (RuntimeException e) {
        Status s = Status.INTERNAL
            .withDescription("Failed to pick a load balancer from service config")
            .withCause(e);
        helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new FailingPicker(s));
        delegate.shutdown();
        delegateFactory = null;
        delegate = new NoopLoadBalancer();
        return;
      }

      if (newlbf != null && newlbf != delegateFactory) {
        helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptyPicker());
        delegate.shutdown();
        delegateFactory = newlbf;
        LoadBalancer old = delegate;
        delegate = delegateFactory.newLoadBalancer(helper);
        if (channelTracer != null) {
          channelTracer.reportEvent(new ChannelTrace.Event.Builder()
              .setDescription("Load balancer changed from " + old + " to " + delegate)
              .setSeverity(ChannelTrace.Event.Severity.CT_INFO)
              .setTimestampNanos(timeProvider.currentTimeNanos())
              .build());
        }
      }
      getDelegate().handleResolvedAddressGroups(servers, attributes);
    }