public static Runnable runnable(CheckedRunnable runnable) {
    return () -> {
      try {
        runnable.run();
      } catch (Throwable ex) {
        throw propagate(ex);
      }
    };
  }