  @Test
  public void eventuallyThrowException_empty() {
    List<Throwable> li = new ArrayList<Throwable>();
    CacheManagerImpl.eventuallyThrowException(li);
  }