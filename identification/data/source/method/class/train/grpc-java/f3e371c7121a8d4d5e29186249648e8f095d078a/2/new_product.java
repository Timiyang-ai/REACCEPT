@VisibleForTesting
    PolicySelection decideLoadBalancerProvider(
        List<EquivalentAddressGroup> servers, @Nullable Map<String, Object> config)
        throws PolicyException {
      // Check for balancer addresses
      boolean haveBalancerAddress = false;
      List<EquivalentAddressGroup> backendAddrs = new ArrayList<>();
      for (EquivalentAddressGroup s : servers) {
        if (s.getAttributes().get(GrpcAttributes.ATTR_LB_ADDR_AUTHORITY) != null) {
          haveBalancerAddress = true;
        } else {
          backendAddrs.add(s);
        }
      }

      if (haveBalancerAddress) {
        // This is a special case where the existence of balancer address in the resolved address
        // selects "grpclb" policy regardless of the service config.
        LoadBalancerProvider grpclbProvider = registry.getProvider("grpclb");
        if (grpclbProvider == null) {
          if (backendAddrs.isEmpty()) {
            throw new PolicyException(
                "Received ONLY balancer addresses but grpclb runtime is missing");
          }
          if (!roundRobinDueToGrpclbDepMissing) {
            roundRobinDueToGrpclbDepMissing = true;
            String errorMsg = "Found balancer addresses but grpclb runtime is missing."
                + " Will use round_robin. Please include grpc-grpclb in your runtime depedencies.";
            helper.getChannelLogger().log(ChannelLogLevel.ERROR, errorMsg);
            logger.warning(errorMsg);
          }
          return new PolicySelection(
              getProviderOrThrow(
                  "round_robin", "received balancer addresses but grpclb runtime is missing"),
              backendAddrs, null);
        } else {
          return new PolicySelection(grpclbProvider, servers, null);
        }
      }
      roundRobinDueToGrpclbDepMissing = false;

      List<LbConfig> lbConfigs = null;
      if (config != null) {
        List<Map<String, Object>> rawLbConfigs =
            ServiceConfigUtil.getLoadBalancingConfigsFromServiceConfig(config);
        lbConfigs = ServiceConfigUtil.unwrapLoadBalancingConfigList(rawLbConfigs);
      }
      if (lbConfigs != null && !lbConfigs.isEmpty()) {
        LinkedHashSet<String> policiesTried = new LinkedHashSet<>();
        for (LbConfig lbConfig : lbConfigs) {
          String policy = lbConfig.getPolicyName();
          LoadBalancerProvider provider = registry.getProvider(policy);
          if (provider != null) {
            if (!policiesTried.isEmpty()) {
              helper.getChannelLogger().log(
                  ChannelLogLevel.DEBUG,
                  "{0} specified by Service Config are not available", policiesTried);
            }
            return new PolicySelection(provider, servers, lbConfig.getRawConfigValue());
          }
          policiesTried.add(policy);
        }
        throw new PolicyException(
            "None of " + policiesTried + " specified by Service Config are available.");
      }
      return new PolicySelection(
          getProviderOrThrow(defaultPolicy, "using default policy"), servers, null);
    }