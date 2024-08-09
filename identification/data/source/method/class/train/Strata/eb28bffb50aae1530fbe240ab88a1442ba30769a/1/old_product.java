public static Runnable runnable(CheckedRunnable runnable) {
    return () -> {
      try {
        runnable.run();
      } catch (IOException ex) {
        throw new UncheckedIOException(ex);
      } catch (Throwable ex) {
        throw Throwables.propagate(ex);
      }
    };
  }