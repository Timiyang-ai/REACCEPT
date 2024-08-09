@Override
  public int putAll(KTypeVTypeAssociativeContainer<? extends KType, ? extends VType> container) {
    final int count = size();
    for (KTypeVTypeCursor<? extends KType, ? extends VType> c : container) {
      put(c.key, c.value);
    }
    return size() - count;
  }