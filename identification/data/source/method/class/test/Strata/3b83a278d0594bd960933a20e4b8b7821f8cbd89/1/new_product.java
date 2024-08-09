public static RuntimeException propagate(Throwable throwable) {
    if (throwable instanceof InvocationTargetException) {
      throw propagate(((InvocationTargetException) throwable).getCause());
    } else if (throwable instanceof IOException) {
      throw new UncheckedIOException((IOException) throwable);
    } else if (throwable instanceof ReflectiveOperationException) {
      throw new UncheckedReflectiveOperationException((ReflectiveOperationException) throwable);
    } else {
      Throwables.throwIfUnchecked(throwable);
      throw new RuntimeException(throwable);
    }
  }