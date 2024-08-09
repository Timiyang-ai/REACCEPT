public static <T> Consumer<T> consumer(CheckedConsumer<T> consumer) {
    return (t) -> {
      try {
        consumer.accept(t);
      } catch (Throwable ex) {
        throw propagate(ex);
      }
    };
  }