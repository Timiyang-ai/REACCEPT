public static <T> Stream<T> stream(Optional<T> optional) {
    if (optional.isPresent()) {
      return Stream.of(optional.get());
    } else {
      return Stream.empty();
    }
  }