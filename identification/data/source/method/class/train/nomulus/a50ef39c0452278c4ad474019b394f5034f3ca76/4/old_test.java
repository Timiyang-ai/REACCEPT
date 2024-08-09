  private boolean executeWithLocks(Callable<Void> callable, final @Nullable Lock acquiredLock) {
    LockHandlerImpl lockHandler = new LockHandlerImpl(new RequestStatusCheckerImpl(), clock) {
      private static final long serialVersionUID = 0L;
      @Override
      Optional<Lock> acquire(String resourceName, String tld, Duration leaseLength) {
        assertThat(resourceName).isEqualTo("resourceName");
        assertThat(tld).isEqualTo("tld");
        assertThat(leaseLength).isEqualTo(ONE_DAY);
        return Optional.ofNullable(acquiredLock);
      }
    };

    return lockHandler.executeWithLocks(callable, "tld", ONE_DAY, "resourceName");
  }