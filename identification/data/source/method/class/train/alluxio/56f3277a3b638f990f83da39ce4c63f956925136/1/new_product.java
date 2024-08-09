public static InetSocketAddress getBindAddress(ServiceType service, AlluxioConfiguration conf) {
    int port = getPort(service, conf);
    assertValidPort(port);
    return new InetSocketAddress(getBindHost(service, conf), getPort(service, conf));
  }