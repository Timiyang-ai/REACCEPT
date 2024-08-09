public void localEndpoint(Endpoint localEndpoint) {
    if (localEndpoint == null) throw new NullPointerException("localEndpoint == null");
    this.localEndpoint = localEndpoint;
  }