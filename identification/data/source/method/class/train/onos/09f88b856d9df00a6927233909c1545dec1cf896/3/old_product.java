public Set<IpPrefix> getPortSubnets(DeviceId deviceId, PortNumber port) {
        ConnectPoint connectPoint = new ConnectPoint(deviceId, port);

        if (isSuppressedPort(connectPoint)) {
            return Collections.emptySet();
        }

        Set<IpPrefix> subnets =
                srManager.interfaceService.getInterfacesByPort(connectPoint).stream()
                        .flatMap(intf -> intf.ipAddressesList().stream())
                        .map(InterfaceIpAddress::subnetAddress)
                        .map(IpPrefix::getIp4Prefix)
                        .collect(Collectors.toSet());

        if (subnets.isEmpty()) {
            log.info(NO_SUBNET, connectPoint);
            return Collections.emptySet();
        } else if (subnets.size() > 2) {
            log.warn(TOO_MANY_SUBNET, connectPoint);
            return Collections.emptySet();
        } else if (verifySubnets(subnets)) {
            return subnets;
        }
        log.warn(MISCONFIGURED, connectPoint);
        return Collections.emptySet();
    }