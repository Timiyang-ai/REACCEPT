public static InetSocketAddress getBindAddress(ServiceType service, TachyonConf conf) {
    String host;
    int port;

    switch (service) {
      case MASTER_RPC:
        host = conf.get(Constants.MASTER_BIND_HOST, "");
        port = conf.getInt(Constants.MASTER_PORT, Constants.DEFAULT_MASTER_PORT);
        break;
      case MASTER_WEB:
        host = conf.get(Constants.MASTER_WEB_BIND_HOST, "");
        port = conf.getInt(Constants.MASTER_WEB_PORT, Constants.DEFAULT_MASTER_WEB_PORT);
        break;
      case WORKER_RPC:
        host = conf.get(Constants.WORKER_BIND_HOST, "");
        port = conf.getInt(Constants.WORKER_PORT, Constants.DEFAULT_WORKER_PORT);
        break;
      case WORKER_DATA:
        host = conf.get(Constants.WORKER_DATA_BIND_HOST, "");
        port = conf.getInt(Constants.WORKER_DATA_PORT, Constants.DEFAULT_WORKER_DATA_PORT);
        break;
      case WORKER_WEB:
        host = conf.get(Constants.WORKER_WEB_BIND_HOST, "");
        port = conf.getInt(Constants.WORKER_WEB_PORT, Constants.DEFAULT_WORKER_WEB_PORT);
        break;
      default:
        throw new IllegalArgumentException("Unknown service type: " + service);
    }

    TachyonConf.assertValidPort(port, conf);
    if (!host.isEmpty()) {
      return new InetSocketAddress(host, port);
    } else {
      return new InetSocketAddress(getLocalHostName(conf), port);
    }
  }