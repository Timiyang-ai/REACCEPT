public static InetSocketAddress getConnectAddress(ServiceType service, Configuration conf) {
    return new InetSocketAddress(getConnectHost(service, conf), getPort(service, conf));
  }