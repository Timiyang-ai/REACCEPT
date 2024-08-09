@Override
  public void setValueForKey(int groupKey, double newValue) {
    if (_storageMode == StorageMode.ARRAY_STORAGE) {
      _resultArray[groupKey] = newValue;
    } else {
      _resultMap.put(groupKey, newValue);
      _priorityQueue.put(groupKey, newValue);
    }
  }