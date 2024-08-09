  private static <E> List<E> toList(Iterable<E> iterable) {
    final List<E> list = new ArrayList<>();
    for (E e : iterable) {
      list.add(e);
    }
    return list;
  }