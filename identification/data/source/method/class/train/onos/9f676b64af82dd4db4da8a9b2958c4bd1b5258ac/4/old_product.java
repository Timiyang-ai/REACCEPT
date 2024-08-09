public boolean inSameSubnet(DeviceId deviceId, Ip4Address hostIp) {

        List<Ip4Prefix> subnets = getSubnets(deviceId);
        if (subnets == null) {
            return false;
        }

        for (Ip4Prefix subnet: subnets) {
            if (subnet.contains(hostIp)) {
                return true;
            }
        }

        return false;
    }