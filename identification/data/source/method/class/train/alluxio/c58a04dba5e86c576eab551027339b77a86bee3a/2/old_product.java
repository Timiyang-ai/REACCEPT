public boolean remove(T object) {
    synchronized (mLock) {
      boolean success = mObjects.remove(object);
      for (FieldIndex<T> field : mIndexMap.keySet()) {
        Object fieldValue = field.getFieldValue(object);
        Map<Object, Set<T>> fieldValueToSet = mSetIndexedByFieldValue.get(mIndexMap.get(field));
        Set<T> set = fieldValueToSet.get(fieldValue);
        if (set != null) {
          if (!set.remove(object)) {
            LOG.error("Fail to remove object " + object.toString() + " from IndexedSet.");
            success = false;
          }
          if (set.isEmpty()) {
            fieldValueToSet.remove(fieldValue);
          }
        }
      }
      return success;
    }
  }