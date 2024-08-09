public boolean removeByField(String fieldName, Object value) {
    Set<T> toRemove = mSetIndexedByFieldValue.get(fieldName).remove(value);
    boolean success = true;
    if (toRemove != null) {
        for (Map<Object, Set<T>> index : mSetIndexedByFieldValue.values()) {
          for (Set<T> set : index.values()) {
            for (T obj : toRemove) {
            success = success && set.remove(obj);
          }
        }
      }
    }
    return success;
  }