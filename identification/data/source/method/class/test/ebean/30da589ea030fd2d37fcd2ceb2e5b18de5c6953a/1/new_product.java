public static SpiTransaction get(String serverName) {
    return local.get().get(serverName);
  }