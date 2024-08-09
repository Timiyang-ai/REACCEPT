public static <E> Iterable<E> filter(
      final Iterable<?> iterable,
      final Class<E> includeFilter) {
    return new Iterable<E>() {
      public Iterator<E> iterator() {
        return new Filterator<>(iterable.iterator(), includeFilter);
      }
    };
  }