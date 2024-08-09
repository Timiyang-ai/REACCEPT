@Override
    public void handleResolvedAddressGroups(
        List<EquivalentAddressGroup> servers, Attributes attributes) {
      Map<String, Object> configMap = attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
      LoadBalancerProvider newlbp;
      try {
        newlbp = decideLoadBalancerProvider(servers, configMap);
      } catch (PolicyNotFoundException e) {
        Status s = Status.INTERNAL.withDescription(e.getMessage());
        helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new FailingPicker(s));
        delegate.shutdown();
        delegateProvider = null;
        delegate = new NoopLoadBalancer();
        return;
      }

      if (delegateProvider == null
          || !newlbp.getPolicyName().equals(delegateProvider.getPolicyName())) {
        helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptyPicker());
        delegate.shutdown();
        delegateProvider = newlbp;
        LoadBalancer old = delegate;
        delegate = delegateProvider.newLoadBalancer(helper);
        if (channelTracer != null) {
          channelTracer.reportEvent(new ChannelTrace.Event.Builder()
              .setDescription("Load balancer changed from " + old.getClass().getSimpleName()
                  + " to " + delegate.getClass().getSimpleName())
              .setSeverity(ChannelTrace.Event.Severity.CT_INFO)
              .setTimestampNanos(timeProvider.currentTimeNanos())
              .build());
        }
      }
      getDelegate().handleResolvedAddressGroups(servers, attributes);
    }