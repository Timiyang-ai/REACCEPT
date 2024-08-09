@VisibleForTesting
  static PolicySelection decideLoadBalancerProvider(
      List<EquivalentAddressGroup> servers, @Nullable Map<String, Object> config,
      ChannelLogger channelLogger) throws PolicyException {
    // Check for balancer addresses
    boolean haveBalancerAddress = false;
    for (EquivalentAddressGroup s : servers) {
      if (s.getAttributes().get(GrpcAttributes.ATTR_LB_ADDR_AUTHORITY) != null) {
        haveBalancerAddress = true;
        break;
      }
    }

    if (haveBalancerAddress) {
      LoadBalancerProvider provider =
          getProviderOrThrow("grpclb", "NameResolver has returned balancer addresses");
      return new PolicySelection(provider, null);
    }

    if (config != null) {
      List<Map<String, Object>> lbConfigs =
          ServiceConfigUtil.getLoadBalancingConfigsFromServiceConfig(config);
      LinkedHashSet<String> policiesTried = new LinkedHashSet<>();
      for (Map<String, Object> lbConfig : lbConfigs) {
        if (lbConfig.size() != 1) {
          throw new PolicyException(
              "There are " + lbConfig.size()
              + " load-balancing configs in a list item. Exactly one is expected. Config="
              + lbConfig);
        }
        Entry<String, Object> entry = lbConfig.entrySet().iterator().next();
        String policy = entry.getKey();
        LoadBalancerProvider provider = registry.getProvider(policy);
        if (provider != null) {
          if (!policiesTried.isEmpty()) {
            channelLogger.log(
                ChannelLogLevel.DEBUG,
                "{0} specified by Service Config are not available", policiesTried);
          }
          return new PolicySelection(provider, (Map) entry.getValue());
        }
        policiesTried.add(policy);
      }
      throw new PolicyException(
          "None of " + policiesTried + " specified by Service Config are available.");
    }
    return new PolicySelection(
        getProviderOrThrow(DEFAULT_POLICY, "using default policy"), null);
  }