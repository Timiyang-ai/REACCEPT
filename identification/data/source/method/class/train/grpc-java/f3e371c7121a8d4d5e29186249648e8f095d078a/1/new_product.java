@VisibleForTesting
    static LoadBalancerProvider decideLoadBalancerProvider(
        List<EquivalentAddressGroup> servers, @Nullable Map<String, Object> config)
        throws PolicyNotFoundException {
      // Check for balancer addresses
      boolean haveBalancerAddress = false;
      for (EquivalentAddressGroup s : servers) {
        if (s.getAttributes().get(GrpcAttributes.ATTR_LB_ADDR_AUTHORITY) != null) {
          haveBalancerAddress = true;
          break;
        }
      }

      if (haveBalancerAddress) {
        return getProviderOrThrow("grpclb", "NameResolver has returned balancer addresses");
      }

      String serviceConfigChoiceBalancingPolicy = null;
      if (config != null) {
        serviceConfigChoiceBalancingPolicy =
            ServiceConfigUtil.getLoadBalancingPolicyFromServiceConfig(config);
        if (serviceConfigChoiceBalancingPolicy != null) {
          // Handle ASCII specifically rather than relying on the implicit default locale of the str
          return getProviderOrThrow(
              Ascii.toLowerCase(serviceConfigChoiceBalancingPolicy),
              "service-config specifies load-balancing policy");
        }
      }
      return getProviderOrThrow(DEFAULT_POLICY, "Using default policy");
    }