@Override public V execute() throws IOException {
    try {
      delegate = supplier.get();

      // Make sure we throttle
      Future<V> future = executor.submit(() -> {
        String oldName = setCurrentThreadName(delegate.toString());
        try {
          return delegate.execute();
        } finally {
          setCurrentThreadName(oldName);
        }
      });
      V result = future.get(); // Still block for the response

      limitListener.onSuccess();
      return result;
    } catch (ExecutionException e) {
      Throwable cause = e.getCause();
      if (cause instanceof RejectedExecutionException) {
        // Storage rejected us, throttle back
        limitListener.onDropped();
      } else {
        limitListener.onIgnore();
      }

      if (cause instanceof RuntimeException) {
        throw (RuntimeException) cause;
      } else if (cause instanceof IOException) {
        throw (IOException) cause;
      } else {
        throw new RuntimeException("Issue while executing on a throttled call", cause);
      }
    } catch (InterruptedException e) {
      limitListener.onIgnore();
      throw new RuntimeException("Interrupted while blocking on a throttled call", e);
    } catch (RuntimeException | Error e) {
      propagateIfFatal(e);
      // Ignoring in all cases here because storage itself isn't saying we need to throttle.  Though, we may still be
      // write bound, but a drop in concurrency won't necessarily help.
      limitListener.onIgnore();
      throw e;
    }
  }