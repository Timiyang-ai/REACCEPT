@Nullable
  public static String resolveIpAddress(String hostname) throws UnknownHostException {
    if (hostname == null || hostname.isEmpty()) {
      return null;
    }

    return InetAddress.getByName(hostname).getHostAddress();
  }