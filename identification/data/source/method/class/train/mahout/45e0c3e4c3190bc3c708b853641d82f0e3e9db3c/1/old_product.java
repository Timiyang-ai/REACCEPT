public double get(int index) throws IndexException {
    if (index >= 0 && index < cardinality())
      return getQuick(index);
    else
      throw new IndexException();
  }