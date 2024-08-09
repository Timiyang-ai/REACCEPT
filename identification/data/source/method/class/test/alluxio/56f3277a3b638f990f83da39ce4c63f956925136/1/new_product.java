public static InetSocketAddress getConnectAddress(ServiceType service) {
    return getConnectAddress(service, Configuration.global());
  }