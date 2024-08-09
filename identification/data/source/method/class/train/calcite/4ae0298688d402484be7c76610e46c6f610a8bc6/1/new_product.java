@Deprecated // to be removed before 2.0
  public static <T> List<T> toList(Iterator<T> iter) {
    List<T> list = new ArrayList<>();
    while (iter.hasNext()) {
      list.add(iter.next());
    }
    return list;
  }