@VisibleForTesting
  static <T> Iterable<T> getCandidatesViaHardCoded(Class<T> klass, Iterable<Class<?>> hardcoded) {
    List<T> list = new ArrayList<>();
    for (Class<?> candidate : hardcoded) {
      list.add(create(klass, candidate));
    }
    return list;
  }