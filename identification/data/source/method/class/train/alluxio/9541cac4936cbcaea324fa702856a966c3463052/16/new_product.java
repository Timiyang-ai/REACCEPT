public static int getConf(String... args) {
    return getConfImpl(
        () -> new RetryHandlingMetaMasterClient(MasterClientConfig.defaults()), args);
  }