public static InetSocketAddress getBindAddress(ServiceType service, TachyonConf conf) {
    int port = getPort(service, conf);
    TachyonConf.assertValidPort(port, conf);

    String host;
    if (conf.containsKey(service.mBindHostKey) && !conf.get(service.mBindHostKey).isEmpty()) {
      host = conf.get(service.mBindHostKey);
    } else {
      host = getLocalHostName(conf);
    }
    return new InetSocketAddress(host, port);
  }