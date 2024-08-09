public static InetSocketAddress getConnectAddress(ServiceType service,
      AlluxioConfiguration conf) {
    return new InetSocketAddress(getConnectHost(service, conf), getPort(service, conf));
  }