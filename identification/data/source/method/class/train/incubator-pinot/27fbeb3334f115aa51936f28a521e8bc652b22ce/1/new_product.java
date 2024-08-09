@Override
  public void ensureCapacity(int capacity) {
    Preconditions.checkArgument(capacity <= _maxCapacity);

    // Nothing to be done for map mode.
    if (_storageMode == StorageMode.MAP_STORAGE) {
      return;
    }

    if (capacity > _trimSize) {
      switchToMapMode(capacity);
      return;
    }

    if (capacity > _resultHolderCapacity) {
      int copyLength = _resultHolderCapacity;
      _resultHolderCapacity = Math.max(_resultHolderCapacity * 2, capacity);

      // Cap the growth to maximum possible number of group keys
      _resultHolderCapacity = Math.min(_resultHolderCapacity, _maxCapacity);

      double[] current = _resultArray;
      _resultArray = new double[_resultHolderCapacity];
      System.arraycopy(current, 0, _resultArray, 0, copyLength);

      if (_defaultValue != 0.0) {
        Arrays.fill(_resultArray, copyLength, _resultHolderCapacity, _defaultValue);
      }
    }
  }