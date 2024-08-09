public boolean inSameSubnet(ConnectPoint connectPoint, IpAddress ip) {
        Ip4Prefix ipv4Subnet = getPortIPv4Subnet(connectPoint.deviceId(), connectPoint.port());
        Ip6Prefix ipv6Subnet = getPortIPv6Subnet(connectPoint.deviceId(), connectPoint.port());
        return (ipv4Subnet != null && ipv4Subnet.contains(ip)) ||
                (ipv6Subnet != null && ipv6Subnet.contains(ip));
    }