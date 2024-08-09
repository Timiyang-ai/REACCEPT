public Set<IpPrefix> getSubnets(DeviceId deviceId) {
        SegmentRouterInfo srinfo = deviceConfigMap.get(deviceId);
        if (srinfo != null && srinfo.subnets != null) {
            // Note: ImmutableSet.Builder.addAll calls the iterator of parameter internally,
            //       which is not protected by SynchronizedCollection mutex.
            ImmutableSet.Builder<IpPrefix> builder = ImmutableSet.builder();
            srinfo.subnets.forEach((k, v) -> builder.add(v));
            return builder.build();
        }
        return null;
    }