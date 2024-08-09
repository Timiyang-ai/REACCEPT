public static InetSocketAddress getBindAddress(ServiceType service) {
    int port = getPort(service);
    assertValidPort(port);
    return new InetSocketAddress(getBindHost(service), getPort(service));
  }