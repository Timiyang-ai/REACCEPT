public static <T> Stream<T> stream(Optional<T> optional) {
    return optional.isPresent() ?
        Stream.of(optional.get()) :
        Stream.empty();
  }