public static Iterable<String>
      prependEach(final String what, Iterable<String> sequence) {
    Preconditions.checkNotNull(what);
    return Iterables.transform(sequence, input -> what + input);
  }