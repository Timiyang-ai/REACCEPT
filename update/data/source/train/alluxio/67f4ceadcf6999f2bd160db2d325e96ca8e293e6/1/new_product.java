public int removeByField(FieldIndex<T> index, Object value) {
    ConcurrentHashSet<T> toRemove = getByFieldInternal(index, value);
    if (toRemove == null) {
      return 0;
    }
    int removed = 0;
    for (T o : toRemove) {
      if (remove(o)) {
        removed++;
      }
    }
    return removed;
  }