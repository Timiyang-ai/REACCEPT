public static ArrayByteSource from(CheckedSupplier<InputStream> inputStreamSupplier) {
    return Unchecked.wrap(() -> {
      try (InputStream in = inputStreamSupplier.get()) {
        return from(in);
      }
    });
  }