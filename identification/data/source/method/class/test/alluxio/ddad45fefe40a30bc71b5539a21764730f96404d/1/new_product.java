public static String resolveIpAddress(String hostname) throws UnknownHostException {
    Preconditions.checkNotNull(hostname, "hostname");
    Preconditions.checkArgument(!hostname.isEmpty(),
            "Cannot resolve IP address for empty hostname");
    return InetAddress.getByName(hostname).getHostAddress();
  }