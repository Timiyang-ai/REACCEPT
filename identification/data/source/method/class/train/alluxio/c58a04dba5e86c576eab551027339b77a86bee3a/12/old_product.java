public boolean remove(String fieldName, Object value) {
    Set<T> toRemove = mSetIndexedByFieldValue.get(fieldName).remove(value);
    boolean success = true;
    if (toRemove != null) {
      for (T obj : toRemove) {
        success = success && mSet.remove(obj);
      }
    }
    return success;
  }