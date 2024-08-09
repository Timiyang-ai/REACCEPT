@SafeVarargs
  public static <T> ImmutableList<T> concatToList(Iterable<T>... iterables) {
    return ImmutableList.copyOf(Iterables.concat(iterables));
  }