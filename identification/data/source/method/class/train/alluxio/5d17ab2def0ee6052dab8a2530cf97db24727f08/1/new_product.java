public static String resolveIpAddress(String hostname) throws UnknownHostException {
    Preconditions.checkNotNull(hostname);
    Preconditions.checkArgument(!hostname.isEmpty());
    return InetAddress.getByName(hostname).getHostAddress();
  }