public static <T> Supplier<T> memoize(Supplier<T> delegate) {
    if (delegate instanceof NonSerializableMemoizingSupplier
        || delegate instanceof MemoizingSupplier) {
      return delegate;
    }
    return delegate instanceof Serializable
        ? new MemoizingSupplier<T>(delegate)
        : new NonSerializableMemoizingSupplier<T>(delegate);
  }