public Set<Ip4Prefix> getSubnets(DeviceId deviceId) {
        SegmentRouterInfo srinfo = deviceConfigMap.get(deviceId);
        if (srinfo != null) {
            log.trace("getSubnets for device{} is {}", deviceId,
                      srinfo.subnets.values());

            ImmutableSet.Builder<Ip4Prefix> builder = ImmutableSet.builder();
            builder.addAll(srinfo.subnets.values());
            if (appConfig != null && deviceId.equals(appConfig.vRouterId())) {
                builder.add(Ip4Prefix.valueOf("0.0.0.0/0"));
            }
            return builder.build();
        }
        return null;
    }