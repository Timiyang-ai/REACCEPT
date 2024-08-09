public static InetSocketAddress getConnectAddress(ServiceType service, TachyonConf conf) {
    switch (service) {
      case MASTER_RPC:
        return new InetSocketAddress(getConnectHost(service, conf), conf.getInt(
            Constants.MASTER_PORT, Constants.DEFAULT_MASTER_PORT));
      case MASTER_WEB:
        return new InetSocketAddress(getConnectHost(service, conf), conf.getInt(
            Constants.MASTER_WEB_PORT, Constants.DEFAULT_MASTER_WEB_PORT));
      case WORKER_RPC:
        return new InetSocketAddress(getConnectHost(service, conf), conf.getInt(
            Constants.WORKER_PORT, Constants.DEFAULT_WORKER_PORT));
      case WORKER_DATA:
        return new InetSocketAddress(getConnectHost(service, conf), conf.getInt(
            Constants.WORKER_DATA_PORT, Constants.DEFAULT_WORKER_DATA_PORT));
      case WORKER_WEB:
        return new InetSocketAddress(getConnectHost(service, conf), conf.getInt(
            Constants.WORKER_WEB_PORT, Constants.DEFAULT_WORKER_WEB_PORT));
      default:
        throw new IllegalArgumentException("Unknown service type: " + service);
    }
  }