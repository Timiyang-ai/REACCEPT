public boolean remove(T object) {
    boolean success = true;
    for (String field : mIndexMap.keySet()) {
      Object fieldValue = getField(object, field);
      int id = mIndexMap.get(field);
      synchronized (mFields) {
        Set<T> set = mSetIndexedByFieldValue.get(id).remove(fieldValue);
        if (set != null) {
          success = success && set.remove(object);
          if (!set.isEmpty()) {
            mSetIndexedByFieldValue.get(id).put(fieldValue, set);
          }
        }
      }
    }
    return success;
  }