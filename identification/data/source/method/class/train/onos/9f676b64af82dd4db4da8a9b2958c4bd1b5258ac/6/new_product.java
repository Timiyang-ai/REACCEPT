public boolean inSameSubnet(DeviceId deviceId, IpAddress hostIp) {
        Set<IpPrefix> subnets = getConfiguredSubnets(deviceId);
        if (subnets == null) {
            return false;
        }

        for (IpPrefix subnet: subnets) {
            // Exclude /0 since it is a special case used for default route
            if (subnet.prefixLength() != 0 && subnet.contains(hostIp)) {
                return true;
            }
        }
        return false;
    }