  private void getConnectAddress(ServiceType service) throws Exception {
    int resolveTimeout = (int) mConfiguration.getMs(PropertyKey.NETWORK_HOST_RESOLUTION_TIMEOUT_MS);
    String localHostName = NetworkAddressUtils.getLocalHostName(resolveTimeout);
    InetSocketAddress masterAddress;

    // all default
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved(localHostName, service.getDefaultPort()),
        masterAddress);

    // bind host only
    mConfiguration.unset(service.getHostNameKey());
    mConfiguration.set(service.getBindHostKey(), "bind.host");
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved("bind.host", service.getDefaultPort()),
        masterAddress);

    // connect host and bind host
    mConfiguration.set(service.getHostNameKey(), "connect.host");
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved("connect.host", service.getDefaultPort()),
        masterAddress);

    // wildcard connect host and bind host
    mConfiguration.set(service.getHostNameKey(), NetworkAddressUtils.WILDCARD_ADDRESS);
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved("bind.host", service.getDefaultPort()),
        masterAddress);

    // wildcard connect host and wildcard bind host
    mConfiguration.set(service.getBindHostKey(), NetworkAddressUtils.WILDCARD_ADDRESS);
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved(localHostName, service.getDefaultPort()),
        masterAddress);

    // connect host and wildcard bind host
    mConfiguration.set(service.getHostNameKey(), "connect.host");
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved("connect.host", service.getDefaultPort()),
        masterAddress);

    // connect host and wildcard bind host with port
    mConfiguration.set(service.getPortKey(), "10000");
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved("connect.host", 10000), masterAddress);

    // connect host and bind host with port
    mConfiguration.set(service.getBindHostKey(), "bind.host");
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved("connect.host", 10000), masterAddress);

    // empty connect host and bind host with port
    mConfiguration.unset(service.getHostNameKey());
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved("bind.host", 10000), masterAddress);

    // empty connect host and wildcard bind host with port
    mConfiguration.set(service.getBindHostKey(), NetworkAddressUtils.WILDCARD_ADDRESS);
    masterAddress = NetworkAddressUtils.getConnectAddress(service, mConfiguration);
    assertEquals(InetSocketAddress.createUnresolved(localHostName, 10000), masterAddress);
  }