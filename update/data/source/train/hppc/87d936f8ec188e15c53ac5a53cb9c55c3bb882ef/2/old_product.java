@Override
  public int putAll(KTypeVTypeAssociativeContainer<? extends KType, ? extends VType> container) {
    final int count = this.assigned;
    for (KTypeVTypeCursor<? extends KType, ? extends VType> c : container) {
      put(c.key, c.value);
    }
    return this.assigned - count;
  }