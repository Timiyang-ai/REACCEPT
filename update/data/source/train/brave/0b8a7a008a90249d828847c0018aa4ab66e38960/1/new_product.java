public void remoteServiceName(String remoteServiceName) {
    if (remoteServiceName == null || remoteServiceName.isEmpty()) {
      throw new NullPointerException("remoteServiceName is empty");
    }
    this.remoteServiceName = remoteServiceName.toLowerCase(Locale.ROOT);
  }