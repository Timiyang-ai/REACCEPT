public boolean inSameSubnet(DeviceId deviceId, Ip4Address hostIp) {

        Set<Ip4Prefix> subnets = getSubnets(deviceId);
        if (subnets == null) {
            return false;
        }

        for (Ip4Prefix subnet: subnets) {
            // Exclude /0 since it is a special case used for default route
            if (subnet.prefixLength() != 0 && subnet.contains(hostIp)) {
                return true;
            }
        }

        return false;
    }