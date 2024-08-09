public Set<Ip4Prefix> getSubnets(DeviceId deviceId) {
        SegmentRouterInfo srinfo = deviceConfigMap.get(deviceId);
        if (srinfo != null) {
            log.trace("getSubnets for device{} is {}", deviceId,
                      srinfo.subnets.values());

            ImmutableSet.Builder<Ip4Prefix> builder = ImmutableSet.builder();
            builder.addAll(srinfo.subnets.values());
            SegmentRoutingAppConfig appConfig =
                    cfgService.getConfig(appId, SegmentRoutingAppConfig.class);
            if (appConfig != null) {
                if (deviceId.equals(appConfig.vRouterId().orElse(null))) {
                    builder.add(Ip4Prefix.valueOf("0.0.0.0/0"));
                }
            }
            return builder.build();
        }
        return null;
    }