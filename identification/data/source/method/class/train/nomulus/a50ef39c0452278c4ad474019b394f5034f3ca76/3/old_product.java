@Override
  public boolean executeWithLocks(
      final Callable<Void> callable,
      @Nullable String tld,
      Duration leaseLength,
      String... lockNames) {
    DateTime startTime = getClock().nowUtc();
    String sanitizedTld = Strings.emptyToNull(tld);
    try {
      return AppEngineTimeLimiter.create()
          .callWithTimeout(
              new LockingCallable(callable, sanitizedTld, leaseLength, lockNames),
              leaseLength.minus(LOCK_TIMEOUT_FUDGE).getMillis(),
              TimeUnit.MILLISECONDS);
    } catch (ExecutionException | UncheckedExecutionException e) {
      // Unwrap the execution exception and throw its root cause.
      Throwable cause = e.getCause();
      if (cause instanceof TimeoutException) {
        throw new RuntimeException(
            String.format(
                "Execution on locks '%s' for TLD '%s' timed out after %s; started at %s",
                Joiner.on(", ").join(lockNames),
                Optional.ofNullable(sanitizedTld).orElse("(null)"),
                new Duration(startTime, getClock().nowUtc()),
                startTime),
            cause);
      }
      throwIfUnchecked(cause);
      throw new RuntimeException(cause);
    } catch (Exception e) {
      throwIfUnchecked(e);
      throw new RuntimeException(e);
    }
  }