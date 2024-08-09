public boolean remove(T object) {
    boolean removed = true;
    boolean triedToRemove = false;
    for (String field : mIndexMap.keySet()) {
      Object fieldValue = getField(object, field);
      int id = mIndexMap.get(field);
      synchronized (mFields) {
        Set<T> set = mSetIndexedByFieldValue.get(id).get(fieldValue);
        if (set != null) {
          triedToRemove = true;
          if (!set.remove(object)) {
            removed = false;
          }
          if (set.isEmpty()) {
            mSetIndexedByFieldValue.get(id).remove(fieldValue);
          }
        }
      }
    }
    boolean success = removed && triedToRemove;
    if (success) {
      synchronized (mFields) {
        mSize -= 1;
      }
    }
    return success;
  }