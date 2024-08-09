public static InetSocketAddress getBindAddress(ServiceType service) {
    int port = getPort(service);
    assertValidPort(port);

    String host;
    if (Configuration.containsKey(service.mBindHostKey) && !Configuration.get(service.mBindHostKey)
        .isEmpty()) {
      host = Configuration.get(service.mBindHostKey);
    } else {
      host = getLocalHostName();
    }
    return new InetSocketAddress(host, port);
  }