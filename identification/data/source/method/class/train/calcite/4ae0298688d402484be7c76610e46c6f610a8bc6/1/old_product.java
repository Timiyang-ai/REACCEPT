public static <T> List<T> toList(Iterator<T> iter) {
    List<T> list = new ArrayList<T>();
    while (iter.hasNext()) {
      list.add(iter.next());
    }
    return list;
  }