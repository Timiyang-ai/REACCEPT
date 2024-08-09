public static String getFqdnHost(InetSocketAddress addr) {
    Preconditions.checkNotNull(addr.getAddress(), "the address of " + addr + " is invalid.");
    return addr.getAddress().getCanonicalHostName();
  }