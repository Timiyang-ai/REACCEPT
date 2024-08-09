public static InetSocketAddress getConnectAddress(ServiceType service, TachyonConf conf) {
    return new InetSocketAddress(getConnectHost(service, conf), getPort(service, conf));
  }