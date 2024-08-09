public int removeByField(FieldIndex<T> index, Object value) {
    int removed = 0;
    if (index instanceof UniqueFieldIndex) {
      T toRemove = getByFieldInternalUnique(index, value);
      if (toRemove == null) {
        return 0;
      }
      if (remove(toRemove)) {
        removed++;
      }
    } else {
      ConcurrentHashSet<T> toRemove = getByFieldInternalNonUnique(index, value);
      if (toRemove == null) {
        return 0;
      }
      for (T o : toRemove) {
        if (remove(o)) {
          removed++;
        }
      }
    }

    return removed;
  }