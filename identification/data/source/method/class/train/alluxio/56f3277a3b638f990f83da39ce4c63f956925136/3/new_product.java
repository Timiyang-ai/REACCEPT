public static InetSocketAddress getConnectAddress(ServiceType service) {
    return new InetSocketAddress(getConnectHost(service), getPort(service));
  }