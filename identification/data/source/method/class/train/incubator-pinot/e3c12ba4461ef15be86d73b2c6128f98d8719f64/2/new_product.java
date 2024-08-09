@Override
  public void setValueForKey(int groupKey, double newValue) {
    if (groupKey != GroupKeyGenerator.INVALID_ID) {
      _resultArray[groupKey] = newValue;
    }
  }