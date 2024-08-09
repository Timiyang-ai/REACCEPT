private void getBindAddress(ServiceType service) throws Exception {
    InetSocketAddress address;

    // all default
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(
        new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, service.getDefaultPort()),
        address);

    // bind host only
    mConfiguration.set(service.getBindHostKey(), "bind.host");
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", service.getDefaultPort()), address);

    // connect host and bind host
    mConfiguration.set(service.getHostNameKey(), "connect.host");
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", service.getDefaultPort()), address);

    // wildcard connect host and bind host
    mConfiguration.set(service.getHostNameKey(), NetworkAddressUtils.WILDCARD_ADDRESS);
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", service.getDefaultPort()), address);

    // wildcard connect host and wildcard bind host
    mConfiguration.set(service.getBindHostKey(), NetworkAddressUtils.WILDCARD_ADDRESS);
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(
        new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, service.getDefaultPort()),
        address);

    // connect host and wildcard bind host
    mConfiguration.set(service.getHostNameKey(), "connect.host");
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(
        new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, service.getDefaultPort()),
        address);

    // connect host and wildcard bind host with port
    switch (service) {
      case JOB_MASTER_RAFT:
        mConfiguration.set(PropertyKey.JOB_MASTER_EMBEDDED_JOURNAL_PORT, "20000");
        break;
      case MASTER_RAFT:
        mConfiguration.set(PropertyKey.MASTER_EMBEDDED_JOURNAL_PORT, "20000");
        break;
      case JOB_MASTER_RPC:
        mConfiguration.set(PropertyKey.JOB_MASTER_RPC_PORT, "20000");
        break;
      case JOB_MASTER_WEB:
        mConfiguration.set(PropertyKey.JOB_MASTER_WEB_PORT, "20000");
        break;
      case JOB_WORKER_RPC:
        mConfiguration.set(PropertyKey.JOB_WORKER_RPC_PORT, "20000");
        break;
      case JOB_WORKER_WEB:
        mConfiguration.set(PropertyKey.JOB_WORKER_WEB_PORT, "20000");
        break;
      case MASTER_RPC:
        mConfiguration.set(PropertyKey.MASTER_RPC_PORT, "20000");
        break;
      case MASTER_WEB:
        mConfiguration.set(PropertyKey.MASTER_WEB_PORT, "20000");
        break;
      case PROXY_WEB:
        mConfiguration.set(PropertyKey.PROXY_WEB_PORT, "20000");
        break;
      case WORKER_RPC:
        mConfiguration.set(PropertyKey.WORKER_RPC_PORT, "20000");
        break;
      case WORKER_WEB:
        mConfiguration.set(PropertyKey.WORKER_WEB_PORT, "20000");
        break;
      default:
        Assert.fail("Unrecognized service type: " + service.toString());
        break;
    }
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, 20000),
        address);

    // connect host and bind host with port
    mConfiguration.set(service.getBindHostKey(), "bind.host");
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", 20000), address);

    // empty connect host and bind host with port
    mConfiguration.unset(service.getHostNameKey());
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", 20000), address);

    // empty connect host and wildcard bind host with port
    mConfiguration.set(service.getBindHostKey(), NetworkAddressUtils.WILDCARD_ADDRESS);
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, 20000),
        address);

    // unset connect host and bind host with port
    mConfiguration.unset(service.getBindHostKey());
    address = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, 20000), address);
  }