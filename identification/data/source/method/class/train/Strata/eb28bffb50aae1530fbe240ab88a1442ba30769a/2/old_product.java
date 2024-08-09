public static <R> Supplier<R> supplier(CheckedSupplier<R> supplier) {
    return () -> {
      try {
        return supplier.get();
      } catch (IOException ex) {
        throw new UncheckedIOException(ex);
      } catch (Throwable ex) {
        throw Throwables.propagate(ex);
      }
    };
  }