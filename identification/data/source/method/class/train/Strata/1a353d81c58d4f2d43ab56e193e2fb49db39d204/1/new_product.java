@SafeVarargs
  public static <T> ImmutableList<T> concatToList(Iterable<? extends T>... iterables) {
    return ImmutableList.copyOf(Iterables.concat(iterables));
  }