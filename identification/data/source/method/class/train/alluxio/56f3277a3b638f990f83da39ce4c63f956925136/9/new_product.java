public static InetSocketAddress getConnectAddress(ServiceType service,
      AlluxioConfiguration conf) {
    return InetSocketAddress.createUnresolved(getConnectHost(service, conf),
        getPort(service, conf));
  }