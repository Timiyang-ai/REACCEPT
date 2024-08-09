public static OpenOptions defaults() {
    return new Builder(ClientContext.getConf()).build();
  }