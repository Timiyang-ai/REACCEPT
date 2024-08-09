public static <T> Supplier<T> memoize(Supplier<T> delegate) {
    return new MemoizingSupplier<T>(Preconditions.checkNotNull(delegate));
  }