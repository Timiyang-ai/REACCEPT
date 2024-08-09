public static <T> Consumer<T> consumer(CheckedConsumer<T> consumer) {
    return (t) -> {
      try {
        consumer.accept(t);
      } catch (IOException ex) {
        throw new UncheckedIOException(ex);
      } catch (Throwable ex) {
        throw Throwables.propagate(ex);
      }
    };
  }