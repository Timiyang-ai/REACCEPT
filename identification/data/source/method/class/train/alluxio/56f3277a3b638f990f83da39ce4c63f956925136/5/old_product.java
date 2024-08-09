public static InetSocketAddress getBindAddress(ServiceType service, TachyonConf conf) {
    String host = conf.get(service.mBindHostKey, "");
    int port = getPort(service, conf);
    TachyonConf.assertValidPort(port, conf);

    if (!host.isEmpty()) {
      return new InetSocketAddress(host, port);
    } else {
      return new InetSocketAddress(getLocalHostName(conf), port);
    }
  }