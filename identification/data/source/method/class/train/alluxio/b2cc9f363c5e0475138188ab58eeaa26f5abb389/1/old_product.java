public static String getFqdnHost(NetAddress addr) throws UnknownHostException {
    return resolveHostName(addr.getHost());
  }