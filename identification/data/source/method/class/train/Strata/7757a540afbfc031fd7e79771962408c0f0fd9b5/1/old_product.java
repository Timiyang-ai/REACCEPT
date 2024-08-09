public static DoubleArray copyOf(List<Double> list) {
    if (list.size() == 0) {
      return EMPTY;
    }
    if (list instanceof ImmList) {
      return ((ImmList) list).underlying;
    }
    return new DoubleArray(Doubles.toArray(list));
  }