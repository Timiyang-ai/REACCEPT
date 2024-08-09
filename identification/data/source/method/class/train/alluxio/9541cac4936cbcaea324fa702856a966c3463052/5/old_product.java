public static int getConf(String... args) {
    return getConfImpl(
        () -> new RetryHandlingMetaMasterConfigClient(MasterClientConfig.defaults()), args);
  }