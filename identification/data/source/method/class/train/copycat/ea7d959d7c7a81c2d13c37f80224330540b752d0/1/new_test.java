  protected <T extends Entry> T get(long index) throws Throwable {
    return serverContext.getLog().get(index);
  }