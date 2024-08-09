public static OutStreamOptions defaults() {
    return new Builder(ClientContext.getConf()).build();
  }