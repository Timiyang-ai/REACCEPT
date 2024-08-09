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
          if (provider == null) {
            policiesTried.add(policy);
          } else {
            if (!policiesTried.isEmpty()) {
              // Before returning, log all previously tried policies
              helper.getChannelLogger().log(
                  ChannelLogLevel.DEBUG,
                  "{0} specified by Service Config are not available", policiesTried);
            }
            return new PolicySelection(
                provider,
                policy.equals(GRPCLB_POLICY_NAME) ? servers : backendAddrs,
                lbConfig.getRawConfigValue());
          }
        }
        if (!haveBalancerAddress) {
          throw new PolicyException(
            "None of " + policiesTried + " specified by Service Config are available.");
        }
      }

      if (haveBalancerAddress) {
        // This is a special case where the existence of balancer address in the resolved address
        // selects "grpclb" policy if the service config couldn't select a policy
        LoadBalancerProvider grpclbProvider = registry.getProvider(GRPCLB_POLICY_NAME);
        if (grpclbProvider == null) {
          if (backendAddrs.isEmpty()) {
            throw new PolicyException(
                "Received ONLY balancer addresses but grpclb runtime is missing");
          }
          if (!roundRobinDueToGrpclbDepMissing) {
            // We don't log the warning every time we have an update.
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
        }
        return new PolicySelection(grpclbProvider, servers, null);
      }
      // No balancer address this time.  If balancer address shows up later, we want to make sure
      // the warning is logged one more time.
      roundRobinDueToGrpclbDepMissing = false;

      // No config nor balancer address. Use default.
      return new PolicySelection(
          getProviderOrThrow(defaultPolicy, "using default policy"), servers, null);
    }