public int removeByField(UniqueFieldIndex<T> index, Object value) {
    int removed = 0;
    T toRemove = getByFieldInternalUnique(index, value);

    if (toRemove == null) {
      return 0;
    }
    if (remove(toRemove)) {
      removed++;
    }

    return removed;
  }