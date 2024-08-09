public static Iterable<String> splitGlob(String path)
  {
    return Iterables.transform(splitGlob(new CharStream(path)), Functions.toStringFunction());
  }