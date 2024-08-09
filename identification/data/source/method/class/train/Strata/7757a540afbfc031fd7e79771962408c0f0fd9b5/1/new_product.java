public static DoubleArray copyOf(Collection<Double> collection) {
    if (collection.size() == 0) {
      return EMPTY;
    }
    if (collection instanceof ImmList) {
      return ((ImmList) collection).underlying;
    }
    return new DoubleArray(Doubles.toArray(collection));
  }