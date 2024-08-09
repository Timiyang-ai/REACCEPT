public boolean remove(T object) {
    synchronized (mFields) {
      boolean success = mObjects.remove(object);
      for (FieldIndex<T> field : mIndexMap.keySet()) {
        Object fieldValue = field.getFieldValue(object);
        Map<Object, Set<T>> fieldValueToSet = mSetIndexedByFieldValue.get(mIndexMap.get(field));
        Set<T> set = fieldValueToSet.get(fieldValue);
        if (set != null) {
          success = success && set.remove(object);
          if (set.isEmpty()) {
            fieldValueToSet.remove(fieldValue);
          }
        }
      }
      return success;
    }
  }