public Set<IpPrefix> getSubnets(DeviceId deviceId) {
        SegmentRouterInfo srinfo = deviceConfigMap.get(deviceId);
        if (srinfo != null) {
            ImmutableSet.Builder<IpPrefix> builder = ImmutableSet.builder();
            return builder.addAll(srinfo.subnets.values()).build();
        }
        return null;
    }