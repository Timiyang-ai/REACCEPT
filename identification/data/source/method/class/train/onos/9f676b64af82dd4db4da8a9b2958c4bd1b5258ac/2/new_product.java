public boolean inSameSubnet(ConnectPoint connectPoint, IpAddress ip) {
        return getPortSubnets(connectPoint.deviceId(), connectPoint.port()).stream()
                .anyMatch(ipPrefix -> ipPrefix.contains(ip));
    }