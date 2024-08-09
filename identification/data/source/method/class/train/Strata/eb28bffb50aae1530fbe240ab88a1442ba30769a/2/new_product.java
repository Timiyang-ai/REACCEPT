public static <R> Supplier<R> supplier(CheckedSupplier<R> supplier) {
    return () -> {
      try {
        return supplier.get();
      } catch (Throwable ex) {
        throw propagate(ex);
      }
    };
  }