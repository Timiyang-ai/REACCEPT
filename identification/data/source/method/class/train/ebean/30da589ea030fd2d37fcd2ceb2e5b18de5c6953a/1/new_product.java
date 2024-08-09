public static SpiTransaction get(String serverName) {
    Map<String, SpiTransaction> map = local.get();
    if (map == null) {
      return null;
    }
    return map.get(serverName);
  }