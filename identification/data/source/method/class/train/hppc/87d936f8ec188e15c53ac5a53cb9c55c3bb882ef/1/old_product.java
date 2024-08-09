@Override
  public int putAll(Iterable<? extends KTypeVTypeCursor<? extends KType, ? extends VType>> iterable){
    final int count = this.assigned;
    for (KTypeVTypeCursor<? extends KType, ? extends VType> c : iterable) {
      put(c.key, c.value);
    }
    return this.assigned - count;
  }