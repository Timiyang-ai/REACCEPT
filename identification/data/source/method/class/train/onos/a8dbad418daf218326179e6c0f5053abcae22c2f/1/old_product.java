public Set<Ip4Prefix> getSubnets(DeviceId deviceId) {
        SegmentRouterInfo srinfo = deviceConfigMap.get(deviceId);
        if (srinfo != null) {
            log.trace("getSubnets for device{} is {}", deviceId,
                      srinfo.subnets.values());

            ImmutableSet.Builder<Ip4Prefix> builder = ImmutableSet.builder();
            return builder.addAll(srinfo.subnets.values()).build();
        }
        return null;
    }