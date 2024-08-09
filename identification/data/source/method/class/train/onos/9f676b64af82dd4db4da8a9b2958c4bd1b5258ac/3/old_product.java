public boolean inSameSubnet(ConnectPoint connectPoint, IpAddress ip) {
        Ip4Prefix portSubnet = getPortSubnet(connectPoint.deviceId(), connectPoint.port());
        return portSubnet != null && portSubnet.contains(ip);
    }