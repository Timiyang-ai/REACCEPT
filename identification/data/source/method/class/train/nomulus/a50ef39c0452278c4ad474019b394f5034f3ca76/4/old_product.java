@Override
  public boolean executeWithLocks(
      final Callable<Void> callable,
      @Nullable String tld,
      Duration leaseLength,
      String... lockNames) {
    try {
      return AppEngineTimeLimiter.create().callWithTimeout(
          new LockingCallable(callable, Strings.emptyToNull(tld), leaseLength, lockNames),
          leaseLength.minus(LOCK_TIMEOUT_FUDGE).getMillis(),
          TimeUnit.MILLISECONDS);
    } catch (ExecutionException | UncheckedExecutionException e) {
      // Unwrap the execution exception and throw its root cause.
      Throwable cause = e.getCause();
      throwIfUnchecked(cause);
      throw new RuntimeException(cause);
    } catch (Exception e) {
      throwIfUnchecked(e);
      throw new RuntimeException(e);
    }
  }