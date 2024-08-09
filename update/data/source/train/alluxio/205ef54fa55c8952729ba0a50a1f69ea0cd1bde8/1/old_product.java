private void getBindAddress(ServiceType service) throws Exception {
    int resolveTimeout = (int) mConfiguration.getMs(PropertyKey.NETWORK_HOST_RESOLUTION_TIMEOUT_MS);
    String localHostName = NetworkAddressUtils.getLocalHostName(resolveTimeout);
    InetSocketAddress workerAddress;

    // all default
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(
        new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, service.getDefaultPort()),
        workerAddress);

    // bind host only
    mConfiguration.set(service.getBindHostKey(), "bind.host");
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", service.getDefaultPort()), workerAddress);

    // connect host and bind host
    mConfiguration.set(service.getHostNameKey(), "connect.host");
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", service.getDefaultPort()), workerAddress);

    // wildcard connect host and bind host
    mConfiguration.set(service.getHostNameKey(), NetworkAddressUtils.WILDCARD_ADDRESS);
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", service.getDefaultPort()), workerAddress);

    // wildcard connect host and wildcard bind host
    mConfiguration.set(service.getBindHostKey(), NetworkAddressUtils.WILDCARD_ADDRESS);
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(
        new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, service.getDefaultPort()),
        workerAddress);

    // connect host and wildcard bind host
    mConfiguration.set(service.getHostNameKey(), "connect.host");
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(
        new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, service.getDefaultPort()),
        workerAddress);

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
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, 20000),
        workerAddress);

    // connect host and bind host with port
    mConfiguration.set(service.getBindHostKey(), "bind.host");
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", 20000), workerAddress);

    // empty connect host and bind host with port
    mConfiguration.unset(service.getHostNameKey());
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress("bind.host", 20000), workerAddress);

    // empty connect host and wildcard bind host with port
    mConfiguration.set(service.getBindHostKey(), NetworkAddressUtils.WILDCARD_ADDRESS);
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, 20000),
        workerAddress);

    // unset connect host and bind host with port
    mConfiguration.unset(service.getBindHostKey());
    workerAddress = NetworkAddressUtils.getBindAddress(service, mConfiguration);
    assertEquals(new InetSocketAddress(NetworkAddressUtils.WILDCARD_ADDRESS, 20000), workerAddress);
  }