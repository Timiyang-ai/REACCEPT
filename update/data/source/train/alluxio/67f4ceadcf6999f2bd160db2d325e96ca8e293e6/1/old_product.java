public boolean removeByField(FieldIndex<T> index, Object value) {
    synchronized (mLock) {
      Set<T> toRemove = getByFieldInternal(index, value);
      if (toRemove == null) {
        return false;
      }
      // Copy the set so that no ConcurrentModificationException happens
      toRemove = ImmutableSet.copyOf(toRemove);
      boolean success = true;
      for (T o : toRemove) {
        success = success && remove(o);
      }
      return success;
    }
  }