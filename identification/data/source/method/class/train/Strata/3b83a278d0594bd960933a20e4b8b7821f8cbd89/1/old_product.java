public static RuntimeException propagate(Throwable throwable) {
    if (throwable instanceof IOException) {
      throw new UncheckedIOException((IOException) throwable);
    } else {
      throw Throwables.propagate(throwable);
    }
  }