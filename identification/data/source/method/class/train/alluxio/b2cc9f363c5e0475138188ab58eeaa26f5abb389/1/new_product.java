public static String getFqdnHost(WorkerNetAddress addr) throws UnknownHostException {
    return resolveHostName(addr.getHost());
  }