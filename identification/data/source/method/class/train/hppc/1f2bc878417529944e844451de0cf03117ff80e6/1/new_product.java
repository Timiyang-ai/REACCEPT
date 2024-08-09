public int addAll(Iterable<? extends KTypeCursor<? extends KType>> iterable) {
    int count = 0;
    for (KTypeCursor<? extends KType> cursor : iterable) {
      if (add(cursor.value)) {
        count++;
      }
    }
    return count;
  }