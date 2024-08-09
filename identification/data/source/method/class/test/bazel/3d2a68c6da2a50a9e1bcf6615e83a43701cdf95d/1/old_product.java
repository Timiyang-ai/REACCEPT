public static Iterable<String>
      prependEach(final String what, Iterable<String> sequence) {
    Preconditions.checkNotNull(what);
    return Iterables.transform(
        sequence,
        new Function<String, String>() {
          @Override
          public String apply(String input) {
            return what + input;
          }
        });
  }