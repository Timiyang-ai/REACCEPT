public Set<Ip4Prefix> getSubnets(DeviceId deviceId) {
        SegmentRouterInfo srinfo = deviceConfigMap.get(deviceId);
        if (srinfo != null) {
            log.trace("getSubnets for device{} is {}", deviceId,
                      srinfo.subnets.values());
            return ImmutableSet.copyOf(srinfo.subnets.values());
        }
        return null;
    }