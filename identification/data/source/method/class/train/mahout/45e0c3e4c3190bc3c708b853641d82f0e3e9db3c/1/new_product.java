public double get(int index) {
    if (index >= 0 && index < cardinality())
      return getQuick(index);
    else
      throw new IndexException();
  }