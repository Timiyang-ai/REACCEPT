public boolean remove(T object) {
    synchronized (mLock) {
      boolean success = mObjects.remove(object);
      for (Map.Entry<FieldIndex<T>, Integer> index : mIndexMap.entrySet()) {
        Object fieldValue = index.getKey().getFieldValue(object);
        Map<Object, Set<T>> fieldValueToSet = mSetIndexedByFieldValue.get(index.getValue());
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