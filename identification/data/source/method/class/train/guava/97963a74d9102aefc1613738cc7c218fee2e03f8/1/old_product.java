public static <T> Supplier<T> memoize(Supplier<T> delegate) {
    return (delegate instanceof MemoizingSupplier)
        ? delegate
        : new MemoizingSupplier<T>(Preconditions.checkNotNull(delegate));
  }