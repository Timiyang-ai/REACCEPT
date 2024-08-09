public boolean remove(T object) {
    boolean success = mSet.remove(object);

    for (String field : mFields.keySet()) {
      Object fieldValue = getField(object, field);
      Set<T> set = mSetIndexedByFieldValue.get(field).remove(fieldValue);
      if (set != null) {
        success = success && set.remove(object);
        if (!set.isEmpty()) {
          mSetIndexedByFieldValue.get(field).put(fieldValue, set);
        }
      }
    }

    return success;
  }